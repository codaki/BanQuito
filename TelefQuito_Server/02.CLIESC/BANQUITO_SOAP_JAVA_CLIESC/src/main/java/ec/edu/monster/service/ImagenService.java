
package ec.edu.monster.service;

import ec.edu.monster.ws.WSImage;
import ec.edu.monster.ws.WSImageService;
import jakarta.activation.DataHandler;

public class ImagenService {
    public String upload(String filename, DataHandler imageData){
        WSImageService service = new WSImageService();
        WSImage port = service.getWSImagePort();
        return port.uploadImage(filename, imageData);
    }
      
      public DataHandler getImg(String filename){
        WSImageService service = new WSImageService();
        WSImage port = service.getWSImagePort();
        return port.downloadImage(filename);
    } 
}
