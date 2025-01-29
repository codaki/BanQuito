
package ec.edu.monster.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"telefonos"})
public class CarritoE extends ec.edu.monster.ws.Carrito {
    private List<ec.edu.monster.ws.TelefonoCarrito> telefonos;

    public CarritoE() {
        this.telefonos = new ArrayList<>();
    }
public List<ec.edu.monster.ws.TelefonoCarrito> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<ec.edu.monster.ws.TelefonoCarrito> telefonos) {
        this.telefonos = telefonos;
    }

  public void agregarTelefono(int telefonoId, int cantidad) {
        ec.edu.monster.ws.TelefonoCarrito telefonoCarrito1 = new ec.edu.monster.ws.TelefonoCarrito();
        telefonoCarrito1.setTelefonoId(telefonoId);
        telefonoCarrito1.setCantidad(cantidad);
        this.getTelefonos().add(telefonoCarrito1);
        System.out.println("Agregar");
        System.out.println(telefonoCarrito1.getTelefonoId());
        System.out.println(telefonoCarrito1.getCantidad());
    }

    public void vaciarCarrito() {
        this.getTelefonos().clear();
    }
}