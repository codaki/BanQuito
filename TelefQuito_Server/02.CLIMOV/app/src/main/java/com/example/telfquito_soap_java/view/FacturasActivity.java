package com.example.telfquito_soap_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.telfquito_soap_java.R;
import com.example.telfquito_soap_java.controller.CompraController;
import com.example.telfquito_soap_java.models.FacturaModel;
import com.example.telfquito_soap_java.service.CompraService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacturasActivity extends AppCompatActivity {

    private EditText etCedulaCliente;
    private Button btnBuscar;
    private LinearLayout tablaContainer;
    private ScrollView scrollView;
    private CompraController compraController;

    private static final String TAG = "FacturasActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas);

        etCedulaCliente = findViewById(R.id.etCedulaCliente);
        btnBuscar = findViewById(R.id.btnBuscar);
        tablaContainer = findViewById(R.id.tablaContainer);
        scrollView = findViewById(R.id.scrollView);

        compraController = new CompraController();

        btnBuscar.setOnClickListener(v -> searchFacturas());
    }

    private void searchFacturas() {
        String cedula = etCedulaCliente.getText().toString().trim();

        if (cedula.isEmpty()) {
            Toast.makeText(this, "Ingrese una cédula válida", Toast.LENGTH_SHORT).show();
            return;
        }

        compraController.obtenerFactura(cedula, new CompraService.SoapCallback<List<FacturaModel>>() {
            @Override
            public void onSuccess(List<FacturaModel> facturas) {
                if (facturas.isEmpty()) {
                    Toast.makeText(FacturasActivity.this, "No se encontraron facturas", Toast.LENGTH_SHORT).show();
                    return;
                }

                displayFacturasGrouped(facturas, cedula);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(FacturasActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error fetching facturas: " + errorMessage);
            }
        });
    }

    private void displayFacturasGrouped(List<FacturaModel> facturas, String cedula) {
        tablaContainer.removeAllViews();

        Map<Integer, List<FacturaModel>> groupedFacturas = new HashMap<>();
        String clientName = facturas.get(0).getNombreCliente(); // Get any factura's client name

        for (FacturaModel factura : facturas) {
            int grupoId = factura.getGrupoId();
            groupedFacturas.putIfAbsent(grupoId, new ArrayList<>());
            groupedFacturas.get(grupoId).add(factura);
        }

        TextView tvClientName = new TextView(this);
        tvClientName.setText("Cliente: " + clientName);
        tvClientName.setTextSize(18);
        tvClientName.setPadding(20, 10, 20, 20);
        tablaContainer.addView(tvClientName);

        for (Map.Entry<Integer, List<FacturaModel>> entry : groupedFacturas.entrySet()) {
            int grupoId = entry.getKey();
            List<FacturaModel> groupFacturas = entry.getValue();

            // Calculate total for this group
            double total = 0;
            for (FacturaModel factura : groupFacturas) {
                total += factura.getPreciofinal();
            }

            // Get first factura's details
            FacturaModel firstFactura = groupFacturas.get(0);
            String fecha = firstFactura.getFecha();
            String tipoPago = firstFactura.getFormaPago();

            // Create a container for each factura group
            LinearLayout facturaLayout = new LinearLayout(this);
            facturaLayout.setOrientation(LinearLayout.VERTICAL);
            facturaLayout.setPadding(20, 20, 20, 20);

            // Add TextViews for each field
            TextView tvFecha = new TextView(this);
            tvFecha.setText("Fecha: " + fecha);
            facturaLayout.addView(tvFecha);

            TextView tvGrupoId = new TextView(this);
            tvGrupoId.setText("Grupo ID: " + grupoId);
            facturaLayout.addView(tvGrupoId);

            TextView tvTipoPago = new TextView(this);
            tvTipoPago.setText("Tipo de Pago: " + tipoPago);
            facturaLayout.addView(tvTipoPago);

            TextView tvTotal = new TextView(this);
            tvTotal.setText("Total: $" + String.format("%.2f", total));
            facturaLayout.addView(tvTotal);

            // Add a "View" button
            Button btnVerFactura = new Button(this);
            btnVerFactura.setText("Ver Detalles");
            btnVerFactura.setOnClickListener(v -> openFacturaView(cedula, grupoId));
            facturaLayout.addView(btnVerFactura);

            // Add the factura layout to the container
            tablaContainer.addView(facturaLayout);
        }
    }

    private void openFacturaView(String cedula, int grupoId) {
        Intent intent = new Intent(this, FacturaActivity.class);
        intent.putExtra("cedula", cedula);
        intent.putExtra("grupoId", grupoId);
        startActivity(intent);
    }
}