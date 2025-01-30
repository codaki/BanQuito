package com.example.telfquito_soap_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telfquito_soap_java.R;
import com.example.telfquito_soap_java.models.Carrito;
import com.example.telfquito_soap_java.models.CarritoSingleton;
import com.example.telfquito_soap_java.models.TelefonoCarrito;
import com.example.telfquito_soap_java.models.TelefonoModel;
import com.example.telfquito_soap_java.models.TelefonosSingleton;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCarrito, recyclerViewSearch;
    private CarritoAdapter carritoAdapter;
    private TelefonosSearchAdapter searchAdapter;
    private EditText etSearchTelefono;
    private List<TelefonoModel> allTelefonos;
    private List<TelefonoModel> filteredTelefonos = new ArrayList<>();
    private Carrito carrito;
    private static final String TAG = "CarritoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Retrieve the passed list of phones
        carrito = CarritoSingleton.getInstance();

        allTelefonos = new ArrayList<>(TelefonosSingleton.getInstance());

        // Initialize UI elements
        etSearchTelefono = findViewById(R.id.etSearchTelefono);
        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        Button btnProceed = findViewById(R.id.btnProceed);
        Button btnBackToTelefonos = findViewById(R.id.btnBackToTelefonos);

        // Set initial visibility of search RecyclerView
        recyclerViewSearch.setVisibility(View.GONE);

        // Set up RecyclerView for the cart
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(this));
        carritoAdapter = new CarritoAdapter(carrito.getTelefonos(), this, (position, newQuantity) -> {
            carrito.getTelefonos().get(position).setCantidad(newQuantity);
            carritoAdapter.notifyDataSetChanged();
        });
        recyclerViewCarrito.setAdapter(carritoAdapter);

        // Set up RecyclerView for searching phones
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new TelefonosSearchAdapter(this, this::addToCart, filteredTelefonos);
        recyclerViewSearch.setAdapter(searchAdapter);

        // Set visibility logic for search RecyclerView
        etSearchTelefono.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                recyclerViewSearch.setVisibility(View.VISIBLE);
            } else {
                recyclerViewSearch.setVisibility(View.GONE);
            }
        });

        // Search functionality
        etSearchTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    filterTelefonos(s.toString());
                    recyclerViewSearch.setVisibility(View.VISIBLE);
                } else {
                    recyclerViewSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Button listeners
        btnBackToTelefonos.setOnClickListener(v -> finish());
        btnProceed.setOnClickListener(v -> {
            Intent intent = new Intent(CarritoActivity.this, VenderActivity.class);
            startActivity(intent);
        });
    }

    private void filterTelefonos(String query) {
        filteredTelefonos.clear();
        for (TelefonoModel telefono : allTelefonos) {
            if (telefono.getNombre().toLowerCase().contains(query.toLowerCase())) {
                filteredTelefonos.add(telefono);
            }
        }
        searchAdapter.notifyDataSetChanged();
    }

    private void addToCart(TelefonoModel telefono) {
        boolean itemExists = false;
        int position = -1;

        for (int i = 0; i < carrito.getTelefonos().size(); i++) {
            TelefonoCarrito item = carrito.getTelefonos().get(i);
            if (item.getTelefonoId() == telefono.getCodTelefono()) {
                item.setCantidad(item.getCantidad() + 1);
                itemExists = true;
                position = i;
                break;
            }
        }

        if (!itemExists) {
            carrito.agregarTelefono(telefono.getCodTelefono(), 1);
            position = carrito.getTelefonos().size() - 1;
            carritoAdapter.notifyItemInserted(position);
            restartActivity();
        } else {
            carritoAdapter.notifyItemChanged(position);
        }

        Toast.makeText(this, itemExists ? "Cantidad aumentada en carrito" : "Añadido al carrito", Toast.LENGTH_SHORT).show();
    }

    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
