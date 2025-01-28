package ec.edu.monster.controller;

import ec.edu.monster.service.CompraService;
import ec.edu.monster.view.FacturaView;
import ec.edu.monster.view.VentaView;
import ec.edu.monster.ws.Carrito;
import ec.edu.monster.ws.Telefonos;

public class VentaController {

    private CompraService compraService;

    public VentaController() {
        this.compraService = new CompraService();
    }

    public void ventaCredito(Telefonos telf, String cedula, String plazo, VentaView view) {
        int plazoInt = Integer.parseInt(plazo);
        Carrito carro = new Carrito();
        String mensaje = compraService.comprarCredito(carro, cedula, plazoInt);
        view.lblMensaje.setText(mensaje);
        if ("Compra exitosa a crédito!!".equals(mensaje)) {
            FacturaController factController = new FacturaController();
            FacturaView factura = new FacturaView();
            factController.mostrarFactura(telf.getPrecio(), cedula,factura);
            view.dispose();
        } else {
            view.lblMensaje.setText(mensaje);
        }
    }
    
    public void ventaEfectivo(Telefonos telf, String cedula, VentaView view){
        Carrito carro = new Carrito();
        String mensaje = compraService.comprarEfectivo(carro, cedula);
        view.lblMensaje.setText(mensaje);
        
        if ("Compra en efectivo exitosa!!".equals(mensaje)) {
            FacturaController factController = new FacturaController();
            FacturaView factura = new FacturaView();
            factController.mostrarFactura(telf.getPrecio(), cedula, factura);
            view.dispose();
        } else {
            view.lblMensaje.setText(mensaje);
        }
    }
}