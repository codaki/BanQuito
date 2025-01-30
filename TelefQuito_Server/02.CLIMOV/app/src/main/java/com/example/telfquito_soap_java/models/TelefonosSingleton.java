package com.example.telfquito_soap_java.models;

import java.util.ArrayList;
import java.util.List;

public class TelefonosSingleton {
    private static List<TelefonoModel> telefonosInstance;

    public static List<TelefonoModel> getInstance() {
        if (telefonosInstance == null) {
            telefonosInstance = new ArrayList<>();
        }
        return telefonosInstance;
    }

    public static void clearTelefonos() {
        if (telefonosInstance != null) {
            telefonosInstance.clear();
        }
    }

    public static void setTelefonosInstance(List<TelefonoModel> telefonos) {
        telefonosInstance = telefonos;
    }
}
