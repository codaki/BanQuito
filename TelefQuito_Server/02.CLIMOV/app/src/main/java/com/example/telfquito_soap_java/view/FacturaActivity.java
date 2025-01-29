package com.example.telfquito_soap_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.telfquito_soap_java.R;
import com.example.telfquito_soap_java.controller.CompraController;
import com.example.telfquito_soap_java.models.FacturaModel;
import com.example.telfquito_soap_java.service.CompraService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacturaActivity extends AppCompatActivity {

    private TextView tvNombreCliente, tvTotal, tvFecha, tvFormaPago, tvBanco, tvCedula, tvDescuento;
    private LinearLayout facturaItemsContainer;
    private Button btnAceptar;
    private CompraController compraController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // UI Components
        tvNombreCliente = findViewById(R.id.tvNombreCliente);
        tvTotal = findViewById(R.id.tvPreciofinal);
        tvFecha = findViewById(R.id.tvFecha);
        tvFormaPago = findViewById(R.id.tvFormaPago);
        tvBanco = findViewById(R.id.tvBancoV);
        btnAceptar = findViewById(R.id.btnAceptar);
        tvCedula = findViewById(R.id.tvCedulaCliente);
        tvDescuento = findViewById(R.id.tvDescuento);
        facturaItemsContainer = findViewById(R.id.facturaItemsContainer);

        compraController = new CompraController();

        String cedula = getIntent().getStringExtra("cedula");
        int grupoId = getIntent().getIntExtra("grupoId", -1);

        if (cedula != null && grupoId != -1) {
            compraController.obtenerFacturaEspecifica(cedula, grupoId, new CompraService.SoapCallback<List<FacturaModel>>() {
                @Override
                public void onSuccess(List<FacturaModel> facturas) {
                    if (!facturas.isEmpty()) {
                        displayFacturaDetails(facturas, cedula);
                    } else {
                        Toast.makeText(FacturaActivity.this, "No se encontraron facturas", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(FacturaActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnAceptar.setOnClickListener(v -> {
            Intent intent = new Intent(FacturaActivity.this, TelefonosActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    private void displayFacturaDetails(List<FacturaModel> facturas, String cedula) {
        // Group invoices by brand and model
        Map<String, Integer> quantityMap = new HashMap<>();
        double total = 0;
        double totalDiscount = 0;
        String cliente = facturas.get(0).getNombreCliente();
        String fecha = facturas.get(0).getFecha();
        String formaPago = facturas.get(0).getFormaPago();

        for (FacturaModel factura : facturas) {
            String key = factura.getMarcaTelefono() + " " + factura.getNombreTelefono();
            quantityMap.put(key, quantityMap.getOrDefault(key, 0) + 1);
            total += factura.getPreciofinal();
            totalDiscount += factura.getDescuento();
        }

        // Set common details
        tvNombreCliente.setText(cliente);
        tvCedula.setText(cedula);
        tvFecha.setText(fecha);
        tvFormaPago.setText(formaPago);
        tvDescuento.setText(String.format("Descuento total: $%.2f", totalDiscount));
        tvTotal.setText(String.format("Total a pagar: $%.2f", total));

        if (formaPago.equals("Efectivo")) {
            tvBanco.setVisibility(View.GONE);
        } else {
            tvBanco.setText("Banco BanQuito");
            tvBanco.setVisibility(View.VISIBLE);
        }

        // Clear previous items
        facturaItemsContainer.removeAllViews();

        // Display each unique phone model & its quantity
        for (Map.Entry<String, Integer> entry : quantityMap.entrySet()) {
            String phoneDetails = entry.getKey();
            int quantity = entry.getValue();

            TextView itemView = new TextView(this);
            itemView.setText(String.format("%s  |  Cantidad: %d", phoneDetails, quantity));
            itemView.setTextSize(16);
            itemView.setPadding(8, 8, 8, 8);

            facturaItemsContainer.addView(itemView);
        }
    }
}
