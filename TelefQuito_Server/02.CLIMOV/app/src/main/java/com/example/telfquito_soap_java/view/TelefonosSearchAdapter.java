package com.example.telfquito_soap_java.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telfquito_soap_java.R;
import com.example.telfquito_soap_java.models.TelefonoModel;

import java.util.List;

public class TelefonosSearchAdapter extends RecyclerView.Adapter<TelefonosSearchAdapter.ViewHolder> {

    private Context context;
    private OnItemClickListener listener;
    private List<TelefonoModel> telefonos;

    public interface OnItemClickListener {
        void onItemClick(TelefonoModel telefono);
    }

    public TelefonosSearchAdapter(Context context, OnItemClickListener listener, List<TelefonoModel> telefonos) {
        this.context = context;
        this.listener = listener;
        this.telefonos = telefonos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_telefono, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TelefonoModel telefono = telefonos.get(position);
        holder.tvNombreTelefono.setText(telefono.getNombre());

        holder.btnAgregar.setOnClickListener(v -> listener.onItemClick(telefono));
    }

    @Override
    public int getItemCount() {
        return telefonos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreTelefono;
        Button btnAgregar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombreTelefono = itemView.findViewById(R.id.tvNombreTelefono);
            btnAgregar = itemView.findViewById(R.id.btnAgregar);
        }
    }
}
