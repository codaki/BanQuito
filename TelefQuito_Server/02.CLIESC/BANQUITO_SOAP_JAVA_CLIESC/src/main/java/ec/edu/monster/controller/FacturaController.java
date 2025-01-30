package ec.edu.monster.controller;

import ec.edu.monster.service.CompraService;
import ec.edu.monster.view.ConstultaFactView;
import ec.edu.monster.view.FacturaView;
import ec.edu.monster.ws.Factura;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FacturaController {

    private CompraService service;
    
    public FacturaController() {
        service = new CompraService();
    }

    public void mostrarFactura(String cedula, int facturaId) {
        FacturaView facturaView = new FacturaView();
        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
        panelResultados.setBackground(Color.WHITE);
        // Obtener teléfonos de la factura
        List<Factura> facturas = service.obtenerFacturaEspecifica(cedula, facturaId);

        // Mapa para agrupar teléfonos por nombre y sumar cantidades
        Map<String, Factura> facturaMap = new LinkedHashMap<>();
        Map<String, Integer> cantidadMap = new HashMap<>();

        // Variables para calcular totales
        double totalDescuento = 0.0;
        double totalPrecioFinal = 0.0;

        // Recorrer la lista y agrupar por nombre de teléfono
        for (Factura fact : facturas) {
            String clave = fact.getNombreTelefono(); // Clave para agrupar

            if (facturaMap.containsKey(clave)) {
                // Si el teléfono ya existe, solo sumamos la cantidad
                cantidadMap.put(clave, cantidadMap.get(clave) + 1);
            } else {
                // Si no existe, lo agregamos al mapa con cantidad 1
                facturaMap.put(clave, fact);
                cantidadMap.put(clave, 1);
            }

            // Sumar valores totales
            totalDescuento += fact.getDescuento();
            totalPrecioFinal += fact.getPreciofinal();
        }

        // Recorrer el mapa y crear las celdas de la factura
        for (String clave : facturaMap.keySet()) {
            Factura fact = facturaMap.get(clave);
            int cantidadTotal = cantidadMap.get(clave); // Obtener la cantidad acumulada

            // Calcular el subtotal correctamente
            double subtotal = (fact.getPreciofinal() - fact.getDescuento()) * cantidadTotal;

            JPanel celda = facturaView.crearCeldaFactura(
                    fact.getMarcaTelefono(),
                    fact.getNombreTelefono(),
                    cantidadTotal,
                    fact.getPreciofinal()*cantidadTotal,
                    fact.getDescuento()*cantidadTotal,
                    subtotal
            );
            panelResultados.add(celda);
        }
        facturaView.getjScrollPane1().setViewportView(panelResultados);
        facturaView.setVisible(true);

        // Establecer información general de la factura
        Factura ultimaFactura = facturas.get(facturas.size() - 1);
        facturaView.lblNomCliente.setText(ultimaFactura.getNombreCliente());
        facturaView.lblCedula.setText(cedula);
        facturaView.lblTipoPago.setText(ultimaFactura.getFormaPago());
        facturaView.lblNomEmpl1.setText(ultimaFactura.getVendedor());
        facturaView.lblFecha.setText(ultimaFactura.getFecha());
        facturaView.lblFacturaId.setText(Integer.toString(facturaId));

        // Calcular el precio final
        double precioFinal = totalPrecioFinal - totalDescuento;

        // Mostrar valores finales en la factura
        facturaView.lblDescuento.setText(String.format("%.2f", totalDescuento));
        facturaView.lblTotal.setText(String.format("%.2f", precioFinal));

        // Mostrar la vista de la factura
        facturaView.setVisible(true);
    }

    public void facturasPorCedula(String cedula, ConstultaFactView consultaFactView) {
        // Crear el panel donde se agregarán las celdas de las facturas
        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
        panelResultados.setBackground(Color.WHITE);

        // Obtener la lista de facturas del servicio
        List<Factura> facturas = service.obtenerFactura(cedula);

        if (facturas == null || facturas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay facturas disponibles para esta cédula.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Mapa para agrupar por grupoId
        Map<Integer, Double> totalPorGrupo = new HashMap<>();
        Map<Integer, Factura> grupoReferencia = new HashMap<>();

        // Recorrer la lista y agrupar por grupoId
        for (Factura fact : facturas) {
            int grupoId = fact.getGrupoId(); // Clave para agrupar

            // Sumar el precio final al total del grupo
            totalPorGrupo.put(grupoId, totalPorGrupo.getOrDefault(grupoId, 0.0) + fact.getPreciofinal());

            // Guardar la primera factura de cada grupo como referencia (fecha y forma de pago son iguales en el grupo)
            if (!grupoReferencia.containsKey(grupoId)) {
                grupoReferencia.put(grupoId, fact);
            }
        }

        // Recorrer los grupos y crear las celdas
        for (Integer grupoId : totalPorGrupo.keySet()) {
            Factura referencia = grupoReferencia.get(grupoId); // Obtener la factura de referencia del grupo
            String fecha = referencia.getFecha();
            String formaPago = referencia.getFormaPago();
            double total = totalPorGrupo.get(grupoId);

            // Crear celda para el grupo
            JPanel celda = consultaFactView.crearCelda(cedula, String.valueOf(grupoId), fecha, formaPago, total);
            panelResultados.add(celda);
        }

        // Mostrar la vista de las facturas agrupadas
        Factura ultimaFactura = facturas.get(facturas.size() - 1);
        consultaFactView.lblNombre.setText(ultimaFactura.getNombreCliente());
        consultaFactView.getjScrollPane1().setViewportView(panelResultados);
        consultaFactView.setVisible(true);
    }
}
