package com.example.telfquito_soap_java.view;

import static android.graphics.Color.LTGRAY;
import static android.graphics.Color.WHITE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telfquito_soap_java.R;
import com.example.telfquito_soap_java.models.Carrito;
import com.example.telfquito_soap_java.models.CarritoSingleton;
import com.example.telfquito_soap_java.models.TelefonoModel;
import com.example.telfquito_soap_java.service.ImagenService;

import java.io.Serializable;
import java.util.List;

public class TelefonosAdapter extends RecyclerView.Adapter<TelefonosAdapter.TelefonoViewHolder> {

    private List<TelefonoModel> telefonos;
    private Context context;
    private ImagenService imagenService;

    public TelefonosAdapter(List<TelefonoModel> telefonos, Context context) {
        this.telefonos = telefonos;
        this.context = context;
        this.imagenService = new ImagenService();
    }

    @NonNull
    @Override
    public TelefonoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_telefono, parent, false);
        return new TelefonoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TelefonoViewHolder holder, int position) {
        TelefonoModel telefono = telefonos.get(position);
        holder.tvMarca.setText(telefono.getMarca());
        holder.tvNombre.setText(telefono.getNombre());
        holder.tvPrecio.setText(String.valueOf(telefono.getPrecio()));

        // Load image using ImagenService
        imagenService.downloadImage(telefono.getImgUrl(), new ImagenService.DownloadSoapCallback() {
            @Override
            public void onDownloadSuccess(byte[] imageData) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                holder.imgTelefono.post(() -> holder.imgTelefono.setImageBitmap(bitmap));
            }

            @Override
            public void onError(String errorMessage) {
                holder.imgTelefono.post(() -> holder.imgTelefono.setImageResource(R.drawable.estrella_triste2));
            }
        });

        updateButton(holder, telefono);

        holder.btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditTelefonoActivity.class);
            intent.putExtra("telefono", (Serializable) telefono);
            context.startActivity(intent);
        });

        holder.btnDesactivar.setText(telefono.getDisponible() == 1 ? "Desactivar" : "Activar");
        holder.btnDesactivar.setOnClickListener(v -> {
            ((TelefonosActivity) context).toggleDisponibilidad(telefono);
            holder.btnDesactivar.setText(telefono.getDisponible() == 1 ? "Desactivar" : "Activar");
            holder.itemView.setBackgroundColor(telefono.getDisponible() == 1 ? WHITE : LTGRAY);
        });

        holder.itemView.setBackgroundColor(telefono.getDisponible() == 1 ? WHITE : LTGRAY);
    }

    private void updateButton(@NonNull TelefonoViewHolder holder, TelefonoModel telefono) {
        Carrito carrito = CarritoSingleton.getInstance();

        boolean isInCart = carrito.getTelefonos().stream()
                .anyMatch(t -> t.getTelefonoId() == telefono.getCodTelefono());

        if (isInCart) {
            holder.btnAdd.setText("Ver Carrito");
            holder.btnAdd.setOnClickListener(v -> {
                Intent intent = new Intent(context, CarritoActivity.class);
                context.startActivity(intent);
            });
        } else {
            holder.btnAdd.setText("🛒");
            holder.btnAdd.setOnClickListener(v -> {
                carrito.agregarTelefono(telefono.getCodTelefono(), 1); // Add to cart
                Toast.makeText(context, "Añadido al carrito", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged(); // Refresh the RecyclerView after adding
            });
        }
    }

    @Override
    public int getItemCount() {
        return telefonos.size();
    }

    public static class TelefonoViewHolder extends RecyclerView.ViewHolder {
        TextView tvMarca, tvNombre, tvPrecio;
        ImageView imgTelefono;
        Button btnEditar, btnDesactivar, btnAdd;

        public TelefonoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            imgTelefono = itemView.findViewById(R.id.imgTelefono);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnDesactivar = itemView.findViewById(R.id.btnDesactivar);
            btnAdd = itemView.findViewById(R.id.btnVender);
        }
    }
}
