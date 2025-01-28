package com.example.telfquito_soap_java.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telfquito_soap_java.R;
import com.example.telfquito_soap_java.controller.TelefonoController;
import com.example.telfquito_soap_java.models.TelefonoModel;
import com.example.telfquito_soap_java.service.ImagenService;
import com.example.telfquito_soap_java.service.TelefonoService;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEditTelefonoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etMarca, etNombre, etPrecio;
    private Button btnSave, btnSelectImage;
    private TextView tvTitle;
    private ImageView imgTelefono;
    private TelefonoController telefonoController;
    private TelefonoModel telefono;
    private ImagenService imagenService;
    private byte[] selectedImageData;
    private String nombreImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_telefono);

        etMarca = findViewById(R.id.etMarca);
        etNombre = findViewById(R.id.etNombre);
        etPrecio = findViewById(R.id.etPrecio);
        btnSave = findViewById(R.id.btnSave);
        tvTitle = findViewById(R.id.tvTitle);
        imgTelefono = findViewById(R.id.imgLogo);
        btnSelectImage = findViewById(R.id.btnSelectImage);

        telefonoController = new TelefonoController();
        imagenService = new ImagenService();

        telefono = (TelefonoModel) getIntent().getSerializableExtra("telefono");
        if (telefono != null) {
            etMarca.setText(telefono.getMarca());
            etNombre.setText(telefono.getNombre());
            etPrecio.setText(telefono.getPrecio());

            // Load image if available
            if (telefono.getImgUrl() != null && !telefono.getImgUrl().isEmpty()) {
                imagenService.downloadImage(telefono.getImgUrl(), new ImagenService.DownloadSoapCallback() {
                    @Override
                    public void onDownloadSuccess(byte[] imageData) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                        imgTelefono.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        imgTelefono.setImageResource(R.drawable.estrella_triste2);
                    }
                });
            } else {
                imgTelefono.setImageResource(R.drawable.estrella_triste2);
            }
        }

        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        btnSave.setOnClickListener(v -> saveTelefono());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                String fileName = getFileName(data.getData());
                if(fileName != null) {
                    nombreImagen = fileName;
                }
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgTelefono.setImageBitmap(bitmap);

                // Convert image to byte array
                inputStream = getContentResolver().openInputStream(data.getData());
                selectedImageData = inputStream.readAllBytes();
            } catch (Exception e) {
                Toast.makeText(this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getFileName(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String fileName = null;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        fileName = cursor.getString(nameIndex);
                    }
                }
            } finally {
                cursor.close();
            }
        }
        return fileName;
    }

    private void saveTelefono() {
        if (telefono == null) {
            telefono = new TelefonoModel();
        }

        String marca = etMarca.getText().toString();
        String nombre = etNombre.getText().toString();
        String precio = etPrecio.getText().toString();

        if (marca.isEmpty() || nombre.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        telefono.setMarca(marca);
        telefono.setNombre(nombre);
        telefono.setPrecio(precio);
        telefono.setDisponible(1); // Set as available

        // Generate imgUrl
        String currentDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String imageName = nombre.substring(0, Math.min(3, nombre.length())).toUpperCase() + "_" + currentDate + nombreImagen;
        telefono.setImgUrl(imageName);

        if (selectedImageData != null) {
            // Upload image first
            imagenService.uploadImage(imageName, selectedImageData, new ImagenService.SoapCallback() {
                @Override
                public void onSuccess(String response) {
                    saveTelefonoModel();
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(AddEditTelefonoActivity.this, "Error al subir la imagen: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            saveTelefonoModel();
        }
    }

    private void saveTelefonoModel() {
        if (telefono.getCodTelefono() == 0) {
            telefonoController.insertTelefono(telefono, new TelefonoService.SoapCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Toast.makeText(AddEditTelefonoActivity.this, "Teléfono agregado con éxito", Toast.LENGTH_SHORT).show();
                    refreshTelefonosList();
                    finish();
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(AddEditTelefonoActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            telefonoController.updateTelefono(telefono, new TelefonoService.SoapCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Toast.makeText(AddEditTelefonoActivity.this, "Teléfono actualizado con éxito", Toast.LENGTH_SHORT).show();
                    refreshTelefonosList();
                    finish();
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(AddEditTelefonoActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void refreshTelefonosList() {
        Intent intent = new Intent(this, TelefonosActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
