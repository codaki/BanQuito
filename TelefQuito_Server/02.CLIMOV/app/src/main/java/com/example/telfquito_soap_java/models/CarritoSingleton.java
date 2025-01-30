package com.example.telfquito_soap_java.models;

public class CarritoSingleton {
    private static Carrito carritoInstance;

    public static Carrito getInstance() {
        if (carritoInstance == null) {
            carritoInstance = new Carrito();
        }
        return carritoInstance;
    }

    public static void clearCarrito() {
        if (carritoInstance != null) {
            carritoInstance.vaciarCarrito();
        }
    }

    public static void deleteItem(int id) {
        if (carritoInstance != null) {
            carritoInstance.removerTelefono(id);
        }
    }
}
