package ec.edu.monster.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.ws.soap.MTOM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebService(serviceName = "WSImageService")
@MTOM
public class WSImage {

    // Define the image directory relative to the Payara server's domain or an absolute path
    private static final String IMAGE_DIR = System.getProperty("user.home") + File.separator + "payara-images" + File.separator;

    @WebMethod(operationName = "uploadImage")
    public String uploadImage(@WebParam(name = "fileName") String fileName, @WebParam(name = "imageData") @XmlMimeType("application/octet-stream") byte[] imageData) {
        try {
            // Ensure the directory exists
            File dir = new File(IMAGE_DIR);
            if (!dir.exists() && !dir.mkdirs()) {
                return "Failed to create image directory: " + IMAGE_DIR;
            }

            // Create the image file
            File file = new File(IMAGE_DIR + fileName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(imageData);
            }
            return "Image uploaded successfully to " + file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading image: " + e.getMessage();
        }
    }

    @WebMethod(operationName = "downloadImage")
    @XmlMimeType("application/octet-stream")
    public byte[] downloadImage(@WebParam(name = "fileName") String fileName) {
        try {
            File file = new File(IMAGE_DIR + fileName);
            if (!file.exists()) {
                throw new IOException("File not found: " + file.getAbsolutePath());
            }
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
