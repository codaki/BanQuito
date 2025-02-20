package ec.edu.monster.models;

public class Compras {
    private int codCompra;
    private String formaPago;
    private String fecha;
    private int codTelefono;
    private int codcCliente;
    private Double descuento;
    private double preciofinal;
    private int grupoId;
    private String vendedor;

    public Compras() {
    }

    public Compras(int codCompra, String formaPago, String fecha, int codTelefono, int codcCliente, double preciofinal, int grupoId, String vendedor) {
        this.codCompra = codCompra;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.codTelefono = codTelefono;
        this.codcCliente = codcCliente;
        this.preciofinal = preciofinal;
        this.grupoId = grupoId;
        this.vendedor = vendedor;
    }

    public Compras(int codCompra, String formaPago, String fecha, int codTelefono, int codcCliente, Double descuento, double preciofinal, int grupoId, String vendedor) {
        this.codCompra = codCompra;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.codTelefono = codTelefono;
        this.codcCliente = codcCliente;
        this.descuento = descuento;
        this.preciofinal = preciofinal;
        this.grupoId = grupoId;
        this.vendedor = vendedor;
    }

    public double getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }

    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCodTelefono() {
        return codTelefono;
    }

    public void setCodTelefono(int codTelefono) {
        this.codTelefono = codTelefono;
    }

    public int getCodcCliente() {
        return codcCliente;
    }

    public void setCodcCliente(int codcCliente) {
        this.codcCliente = codcCliente;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }   

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
}