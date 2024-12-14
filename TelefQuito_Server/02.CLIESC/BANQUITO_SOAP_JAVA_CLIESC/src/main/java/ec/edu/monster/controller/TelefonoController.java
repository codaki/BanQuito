package ec.edu.monster.controller;

import ec.edu.monster.service.TelefonoService;
import ec.edu.monster.view.CatalogoView;
import ec.edu.monster.view.VentaView;
import ec.edu.monster.ws.Telefonos;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelefonoController {

    private TelefonoService telefonoService;

    public TelefonoController() {
        this.telefonoService = new TelefonoService();
    }

    /**
     * Método para cargar y mostrar los telefonos en la vista proporcionada.
     *
     * @param catalogoView
     */
    public void cargarTelefonos(CatalogoView catalogoView) {
        try {
            List<Telefonos> telefonos = telefonoService.obtenerTelefonos();

            JPanel panelResultados = new JPanel();
            panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
            panelResultados.setBackground(Color.WHITE);

            if (telefonos == null || telefonos.isEmpty()) {
                JLabel noResultados = new JLabel("No se encontraron teléfonos registrados: ");
                noResultados.setFont(new Font("Arial", Font.BOLD, 16));
                noResultados.setForeground(Color.RED);
                panelResultados.add(noResultados);
            } else {
                // Crear celdas para cada movimiento
                for (Telefonos telf : telefonos) {
                    JPanel celda = catalogoView.crearCelda(String.valueOf(telf.getCodTelefono()),
                            "Marca: " + telf.getMarca(),
                            "Modelo: " + telf.getNombre(),
                            String.valueOf(telf.getDisponible()),
                            "Precio: $" + String.format("%.2f", telf.getPrecio())
                    );
                    panelResultados.add(celda);
                }

            }

            // Reemplazar el contenido del JScrollPane con los nuevos resultados
            catalogoView.getjScrollPane1().setViewportView(panelResultados);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(catalogoView,
                    "Error al obtener los movimientos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void activarpantallaVenta(String codigo){
        Telefonos telefono = telefonoService.obtenerTelefonoPorId(Integer.parseInt(codigo));;

        VentaView venta = new VentaView(telefono);
        venta.lblMarca.setText(telefono.getMarca());
        venta.lblModelo.setText(telefono.getNombre());
        venta.lblPrecio.setText(Double.toString(telefono.getPrecio()));

        venta.setVisible(true);
    }
    
}
