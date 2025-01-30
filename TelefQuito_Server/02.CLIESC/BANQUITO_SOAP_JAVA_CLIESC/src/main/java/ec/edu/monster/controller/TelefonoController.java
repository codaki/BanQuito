package ec.edu.monster.controller;

import static ec.edu.monster.ImageToBase64.imgToDataHandler;
import ec.edu.monster.service.ImagenService;
import ec.edu.monster.service.TelefonoService;
import ec.edu.monster.view.CatalogoView;
import ec.edu.monster.view.VentaView;
import ec.edu.monster.ws.Carrito;
import ec.edu.monster.ws.TelefonoCarrito;
import ec.edu.monster.ws.Telefonos;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;
import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelefonoController {

    private TelefonoService telefonoService;
    private ImagenService imagenService;
    private Carrito carrito;

    public TelefonoController() {
        this.telefonoService = new TelefonoService();
        this.imagenService = new ImagenService();
        this.carrito = new Carrito();
    }

    public String convertirDataHandlerAString(DataHandler dataHandler) {
        if (dataHandler == null) {
            return null;
        }

        try (InputStream inputStream = dataHandler.getInputStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] dataBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(dataBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Telefonos> obtenerTelefonos() {
        List<Telefonos> telefonos = telefonoService.obtenerTelefonos();
        return telefonos;
    }

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

                for (Telefonos telf : telefonos) {
                    DataHandler base64 = imagenService.getImg(telf.getImgUrl());
                    if (base64 != null) {
                        String fotoBase64 = convertirDataHandlerAString(base64);
                        JPanel celda = catalogoView.crearCelda(String.valueOf(telf.getCodTelefono()),
                                fotoBase64,
                                telf.getMarca(),
                                telf.getNombre(),
                                String.valueOf(telf.getDisponible()),
                                String.format("%.2f", telf.getPrecio())
                        );
                        panelResultados.add(celda);
                    } else {
                        base64 = imagenService.getImg("estrellaFeliz.png");
                        String fotoBase64 = convertirDataHandlerAString(base64);
                        JPanel celda = catalogoView.crearCelda(String.valueOf(telf.getCodTelefono()),
                                fotoBase64,
                                telf.getMarca(),
                                telf.getNombre(),
                                String.valueOf(telf.getDisponible()),
                                String.format("%.2f", telf.getPrecio())
                        );
                        panelResultados.add(celda);
                    }
                }
            }

            catalogoView.getjScrollPane1().setViewportView(panelResultados);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(catalogoView,
                    "Error al obtener los movimientos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarCarrito(Carrito carrito) {
        VentaView venta = new VentaView(carrito);

        if (carrito == null || carrito.getTelefonoCarrito() == null) {
            return;
        }
        JPanel contenedorCeldas = new JPanel();
        contenedorCeldas.setLayout(new BoxLayout(contenedorCeldas, BoxLayout.Y_AXIS));
        contenedorCeldas.setBackground(Color.WHITE);

        for (TelefonoCarrito telCarrito : carrito.getTelefonoCarrito()) {
            int codigo = telCarrito.getTelefonoId();
            Telefonos telefono = telefonoService.obtenerTelefonoPorId(codigo);

            if (telefono != null) {
                JPanel celdaVenta = venta.crearCeldaVenta(
                        codigo,
                        telefono.getNombre(),
                        telefono.getMarca(),
                        telefono.getPrecio(),
                        telCarrito.getCantidad()
                );

                // Agregar la celda al contenedor
                contenedorCeldas.add(celdaVenta);
            }
            venta.getjScrollPane1().setViewportView(contenedorCeldas);
            venta.setVisible(true);
        }
    }

    public void recargarCarrito(Carrito carrito, VentaView venta) {
        if (carrito == null || carrito.getTelefonoCarrito() == null) {
            return;
        }

        JPanel contenedorCeldas = new JPanel();
        contenedorCeldas.setLayout(new BoxLayout(contenedorCeldas, BoxLayout.Y_AXIS));
        contenedorCeldas.setBackground(Color.WHITE);

        for (TelefonoCarrito telCarrito : carrito.getTelefonoCarrito()) {
            int codigo = telCarrito.getTelefonoId();
            Telefonos telefono = telefonoService.obtenerTelefonoPorId(codigo);

            if (telefono != null) {
                JPanel celdaVenta = venta.crearCeldaVenta(
                        codigo,
                        telefono.getNombre(),
                        telefono.getMarca(),
                        telefono.getPrecio(),
                        telCarrito.getCantidad()
                );

                contenedorCeldas.add(celdaVenta);
            }
        }

        venta.getjScrollPane1().setViewportView(contenedorCeldas);
        venta.setVisible(true);
    }

    public void agregarCarrito(int codigo, Carrito carrito) {
    // Buscar si el producto ya existe en el carrito
    for (TelefonoCarrito telCarrito : carrito.getTelefonoCarrito()) {
        if (telCarrito.getTelefonoId() == codigo) {
            telCarrito.setCantidad(telCarrito.getCantidad() + 1);
            System.out.println("Cantidad actualizada en el carrito: " + telCarrito.getCantidad());
            return; // Salir de la función porque ya se actualizó
        }
    }

    // Si no existe, agregarlo con cantidad 1
    TelefonoCarrito nuevoTelCarrito = new TelefonoCarrito();
    nuevoTelCarrito.setTelefonoId(codigo);
    nuevoTelCarrito.setCantidad(1);
    carrito.getTelefonoCarrito().add(nuevoTelCarrito);

    System.out.println("Nuevo producto agregado al carrito: " + codigo);
}


    public String insertarTelefono(Telefonos telefono, String ruta) {
        String imagenName = generateImageName(telefono.getNombre(), new File(ruta).getName());
        DataHandler imageDataHandler = imgToDataHandler(ruta);

        imagenService.upload(imagenName, imageDataHandler);
        telefono.setImgUrl(imagenName);

        return telefonoService.insertarTelefono(telefono);
    }

    public String actualizarTelefono(Telefonos telefono, String ruta) {
        String imagenName = generateImageName(telefono.getNombre(), new File(ruta).getName());
        DataHandler imageDataHandler = imgToDataHandler(ruta);

        imagenService.upload(imagenName, imageDataHandler);
        telefono.setImgUrl(imagenName);

        return telefonoService.actualizarTelefono(telefono);
    }

    public String generateImageName(String nombreTel, String imagenName) {
        String prefix = nombreTel.length() >= 3 ? nombreTel.substring(0, 3).toUpperCase() : nombreTel.toUpperCase();
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return prefix + "_" + currentDate + "_" + imagenName;
    }

    public static DataHandler imgToDataHandler(String ruta) {
        try (FileInputStream fis = new FileInputStream(new File(ruta))) {
            byte[] bytes = fis.readAllBytes();
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/octet-stream");
            return new DataHandler(dataSource);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al convertir la imagen a DataHandler", e);
        }
    }

    public Telefonos obtenerPorId(int codigo) {
        return telefonoService.obtenerTelefonoPorId(codigo);
    }

}
