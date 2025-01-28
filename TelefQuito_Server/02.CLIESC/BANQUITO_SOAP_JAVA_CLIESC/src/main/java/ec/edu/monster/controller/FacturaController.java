package ec.edu.monster.controller;

import ec.edu.monster.service.CompraService;
import ec.edu.monster.view.FacturaView;
import ec.edu.monster.ws.Carrito;
import ec.edu.monster.ws.Factura;
import ec.edu.monster.ws.TelefonoCarrito;
import ec.edu.monster.ws.Telefonos;
import java.util.List;

public class FacturaController {

    private CompraService service;

    FacturaController() {
        service = new CompraService();
    }

    public void mostrarFactura(Carrito carrito, double precioTotal, String cedula, FacturaView facturaView) {
    // Obtener la última factura del cliente
    List<Factura> facturas = service.obtenerFactura(cedula);
    Factura ultimaFactura = facturas.get(facturas.size() - 1);

    // Establecer información general de la factura
    facturaView.lblNomCliente.setText(ultimaFactura.getNombreCliente());
    facturaView.lblCedula.setText(cedula);
    facturaView.lblTipoPago.setText(ultimaFactura.getFormaPago());

    // Crear un StringBuilder para concatenar los conceptos de la factura
    StringBuilder conceptos = new StringBuilder();
    StringBuilder marcas = new StringBuilder();
    double subtotal = 0;

    TelefonoController controller = new TelefonoController();
    // Recorrer el carrito para obtener los detalles de cada producto
    for (TelefonoCarrito telCarrito : carrito.getTelefonoCarrito()) {
        Telefonos telefono = controller.obtenerPorId(telCarrito.getTelefonoId());

        if (telefono != null) {
            conceptos.append(telefono.getNombre()).append("\n");
            marcas.append(telefono.getMarca()).append("\n");

            double precioProducto = telefono.getPrecio() * telCarrito.getCantidad();
            subtotal += precioProducto;
        }
    }

    // Establecer los valores en la vista de la factura
    facturaView.lblConcepto.setText("<html>" + conceptos.toString().replace("\n", "<br>") + "</html>");
    facturaView.lblMarca.setText("<html>" + marcas.toString().replace("\n", "<br>") + "</html>");
    facturaView.lblPrecio.setText(String.format("%.2f", subtotal));
    facturaView.lblSubtotal.setText(String.format("%.2f", subtotal));

    // Calcular descuento y precio total basado en el tipo de pago
    double descuento = 0;
    if ("Efectivo".equals(ultimaFactura.getFormaPago())) {
        descuento = subtotal * 0.42; // 42% de descuento para pago en efectivo
        facturaView.lblTitDescuento.setText("Descuento 42%");
    } else {
        facturaView.lblTitDescuento.setText("Descuento 0%");
    }

    double precioFinal = subtotal - descuento;

    // Establecer los valores finales en la factura
    facturaView.lblDescuento.setText(String.format("%.2f", descuento));
    facturaView.lblTotal.setText(String.format("%.2f", precioFinal));

    // Ocultar el campo de banco si la forma de pago es en efectivo
    if ("Efectivo".equals(ultimaFactura.getFormaPago())) {
        facturaView.lblBanco.setVisible(false);
    }

    // Mostrar la vista de la factura
    facturaView.setVisible(true);
}


}