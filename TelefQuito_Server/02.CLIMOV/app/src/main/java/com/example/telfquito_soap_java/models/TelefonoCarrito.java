package com.example.telfquito_soap_java.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class TelefonoCarrito implements KvmSerializable, Serializable {
    private int telefonoId;
    private int cantidad;

    public TelefonoCarrito() {}

    public TelefonoCarrito(int telefonoId, int cantidad) {
        this.telefonoId = telefonoId;
        this.cantidad = cantidad;
    }

    public int getTelefonoId() {
        return telefonoId;
    }

    public void setTelefonoId(int telefonoId) {
        this.telefonoId = telefonoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public Object getProperty(int index) {
        switch (index) {
            case 0: return telefonoId;
            case 1: return cantidad;
            default: return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 2; // Two properties: telefonoId and cantidad
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0: telefonoId = Integer.parseInt(value.toString()); break;
            case 1: cantidad = Integer.parseInt(value.toString()); break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "telefonoId";
                break;
            case 1:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "cantidad";
                break;
        }
    }
}


