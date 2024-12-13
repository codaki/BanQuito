
package ec.edu.monster.models;

public class Telefonos {
     private int codTelefono;
    private String nombre;
    private double precio;

    public Telefonos() {
    }

    public Telefonos(int codTelefono, String nombre, double precio) {
        this.codTelefono = codTelefono;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCodTelefono() {
        return codTelefono;
    }

    public void setCodTelefono(int codTelefono) {
        this.codTelefono = codTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
