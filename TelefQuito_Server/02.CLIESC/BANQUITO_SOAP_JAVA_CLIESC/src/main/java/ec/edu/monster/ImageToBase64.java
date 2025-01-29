package ec.edu.monster;

import ec.edu.monster.ws.WSImage;
import ec.edu.monster.ws.WSImageService;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.xml.ws.BindingProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import jakarta.mail.util.ByteArrayDataSource;

public class ImageToBase64 {

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

    public static void main(String[] args) {
        String ruta = "C:/Users/JOSE/payara-images/pixel8.jpeg"; // Reemplaza con la ruta de tu imagen

        // Obtén la imagen como DataHandler
        DataHandler imageDataHandler = imgToDataHandler(ruta);

        WSImageService service = new WSImageService();
        WSImage port = service.getWSImagePort();

        // Configurar el cliente si es necesario (por ejemplo, autenticación)
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/TELFQUITO_SOAP_JAVA/WSImageService");

        // Llamar al método SOAP con DataHandler
        String fileName = "imagen.png";
        String resultado = port.uploadImage(fileName, imageDataHandler);

        System.out.println("Resultado de la carga: " + resultado);
    }

}
