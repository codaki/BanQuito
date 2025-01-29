
package ec.edu.monster.service;

import ec.edu.monster.ws.WSImage;
import ec.edu.monster.ws.WSImageService;
import jakarta.activation.DataHandler;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.soap.MTOM;

@MTOM(enabled = true, threshold = 1024)
@WebService
public class ImagenService {
    public String upload(String filename, DataHandler imageData){
        WSImageService service = new WSImageService();
        WSImage port = service.getWSImagePort();
        
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/TELFQUITO_SOAP_JAVA/WSImageService");

        return port.uploadImage(filename, imageData);
    }
      
      public DataHandler getImg(String filename){
        WSImageService service = new WSImageService();
        WSImage port = service.getWSImagePort();
        return port.downloadImage(filename);
    } 
}
