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

    public void ventaCredito(Carrito carro, String cedula, String plazo, VentaView view) {
        int plazoInt = Integer.parseInt(plazo);
        String mensaje = compraService.comprarCredito(carro, cedula, plazoInt);

        String[] partes = mensaje.split("!", 2);
        String estado = partes[0];
        String facturaId = partes[1];

        view.lblMensaje.setText(estado);
        if ("Compra a crédito exitosa".equals(estado)) {
            FacturaController factController = new FacturaController();
            factController.mostrarFactura(cedula, Integer.parseInt(facturaId));
            view.dispose();
        } else {
            view.lblMensaje.setText(mensaje);
        }
    }

    public void ventaEfectivo(Carrito carro, String cedula, VentaView view) {
        String mensaje = compraService.comprarEfectivo(carro, cedula);
        
        String[] partes = mensaje.split("!", 2);
        String estado = partes[0];
        String facturaId = partes[1];
        
        view.lblMensaje.setText(estado);

        if ("Compra en efectivo exitosa".equals(mensaje)) {
            FacturaController factController = new FacturaController();
            factController.mostrarFactura(cedula,Integer.parseInt(facturaId));
            view.dispose();
        } else {
            view.lblMensaje.setText(mensaje);
        }
    }
}
