package com.example.telfquito_soap_java.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class Carrito implements KvmSerializable, Serializable {
    private Vector<TelefonoCarrito> telefonos;

    public Carrito() {
        this.telefonos = new Vector<>();
    }

    public Carrito(List<TelefonoCarrito> telefonos) {
        this.telefonos = new Vector<>(telefonos);
    }

    public List<TelefonoCarrito> getTelefonos() {
        return new ArrayList<>(telefonos);
    }

    public void setTelefonos(List<TelefonoCarrito> telefonos) {
        this.telefonos = new Vector<>(telefonos);
    }

    public void agregarTelefono(int telefonoId, int cantidad) {
        for (TelefonoCarrito telefono : telefonos) {
            if (telefono.getTelefonoId() == telefonoId) {
                telefono.setCantidad(telefono.getCantidad() + cantidad);
                return;
            }
        }
        this.telefonos.add(new TelefonoCarrito(telefonoId, cantidad));
    }

    public void removerTelefono(int telefonoId) {
        telefonos.removeIf(telefono -> telefono.getTelefonoId() == telefonoId);
    }

    public void vaciarCarrito() {
        this.telefonos.clear();
    }

    @Override
    public Object getProperty(int index) {
        if (index < telefonos.size()) {
            return telefonos.get(index); // Return each TelefonoCarrito directly
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return telefonos.size(); // Each TelefonoCarrito is treated as a separate property
    }

    @Override
    public void setProperty(int index, Object value) {
        if (value instanceof SoapObject) {
            SoapObject soapObj = (SoapObject) value;
            TelefonoCarrito telefono = new TelefonoCarrito();
            telefono.setTelefonoId(Integer.parseInt(soapObj.getProperty("telefonoId").toString()));
            telefono.setCantidad(Integer.parseInt(soapObj.getProperty("cantidad").toString()));
            telefonos.add(telefono);
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        if (index < telefonos.size()) {
            info.name = "telefonoCarrito"; // Ensure each property is named "telefonoCarrito"
            info.type = TelefonoCarrito.class; // Use TelefonoCarrito as the type
        }
    }
}


