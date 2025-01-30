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

    public void ventaEfectivo(Carrito carro, String cedula, VentaView view) {
    // Verificar que el carrito no esté vacío antes de llamar al servicio
    if (carro.getTelefonoCarrito().isEmpty()) {
        view.lblMensaje.setText("El carrito está vacío.");
        return;
    }

    String mensaje = compraService.comprarEfectivo(carro, cedula);
    System.out.println("Respuesta del servicio (Efectivo): " + mensaje); // Depuración

    // Validar la respuesta del servicio
    if (mensaje == null || mensaje.isEmpty()) {
        view.lblMensaje.setText("Error: El servicio no respondió correctamente.");
        return;
    }

    // Dividir el mensaje correctamente
    String[] partes = mensaje.split("!", 2);
    String estado = partes[0];
    String facturaId = (partes.length > 1) ? partes[1] : "";

    view.lblMensaje.setText(estado);

    // Si la compra fue exitosa y tiene una factura ID válida
    if (estado.contains("Compra en efectivo exitosa") && !facturaId.isEmpty()) {
        FacturaController factController = new FacturaController();
        factController.mostrarFactura(cedula, Integer.parseInt(facturaId));
        view.dispose();
    } else {
        view.lblMensaje.setText(mensaje); // Mostrar mensaje de error detallado
    }
}

public void ventaCredito(Carrito carro, String cedula, String plazo, VentaView view) {
    // Verificar que el carrito no esté vacío
    if (carro.getTelefonoCarrito().isEmpty()) {
        view.lblMensaje.setText("El carrito está vacío.");
        return;
    }

    // Verificar que el plazo sea un número válido
    int plazoInt;
    try {
        plazoInt = Integer.parseInt(plazo);
        if (plazoInt < 3 || plazoInt > 18) {
            view.lblMensaje.setText("Seleccione un plazo válido (3-18 meses).");
            return;
        }
    } catch (NumberFormatException e) {
        view.lblMensaje.setText("Plazo inválido. Debe ser un número.");
        return;
    }

    String mensaje = compraService.comprarCredito(carro, cedula, plazoInt);
    System.out.println("Respuesta del servicio (Crédito): " + mensaje); // Depuración

    if (mensaje == null || mensaje.isEmpty()) {
        view.lblMensaje.setText("Error: El servicio no respondió correctamente.");
        return;
    }

    // Dividir el mensaje correctamente
    String[] partes = mensaje.split("!", 2);
    String estado = partes[0];
    String facturaId = (partes.length > 1) ? partes[1] : "";

    view.lblMensaje.setText(estado);

    if (estado.contains("Compra a crédito exitosa") && !facturaId.isEmpty()) {
        FacturaController factController = new FacturaController();
        factController.mostrarFactura(cedula, Integer.parseInt(facturaId));
        view.dispose();
    } else {
        view.lblMensaje.setText(mensaje);
    }
}

}
