package ec.edu.monster.controller;

import ec.edu.monster.service.CompraService;
import ec.edu.monster.view.FacturaView;
import ec.edu.monster.view.VentaView;
import ec.edu.monster.ws.Carrito;

public class VentaController {

    private CompraService compraService;

    public VentaController() {
        this.compraService = new CompraService();
    }

    public void ventaCredito(Carrito carro, double total, String cedula, String plazo, VentaView view) {
        int plazoInt = Integer.parseInt(plazo);
        String mensaje = compraService.comprarCredito(carro, cedula, plazoInt);
        view.lblMensaje.setText(mensaje);
        if ("Compra exitosa a crédito!!".equals(mensaje)) {
            FacturaController factController = new FacturaController();
            FacturaView factura = new FacturaView();
            factController.mostrarFactura(carro, total, cedula,factura);
            view.dispose();
        } else {
            view.lblMensaje.setText(mensaje);
        }
    }
    
    public void ventaEfectivo(Carrito carro, double total, String cedula, VentaView view){
        String mensaje = compraService.comprarEfectivo(carro, cedula);
        view.lblMensaje.setText(mensaje);
        
        if ("Compra en efectivo exitosa!!".equals(mensaje)) {
            FacturaController factController = new FacturaController();
            FacturaView factura = new FacturaView();
            factController.mostrarFactura(carro, total, cedula, factura);
            view.dispose();
        } else {
            view.lblMensaje.setText(mensaje);
        }
    }
}