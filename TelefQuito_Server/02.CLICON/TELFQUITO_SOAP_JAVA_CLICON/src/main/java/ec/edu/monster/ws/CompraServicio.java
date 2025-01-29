package ec.edu.monster.ws;

import java.util.List;
import ec.edu.monster.models.CarritoE;
public class CompraServicio {
    WSCompra_Service servicio = new WSCompra_Service();
    WSCompra port = servicio.getWSCompraPort();
    
    public String comprarEfectivo(CarritoE carrito,
             String codcCedula) {
        return port.comprarEfectivo(carrito, codcCedula);
    }
     public String comprarCredito( CarritoE carrito,
            String cedula, int plazoMeses) {
         return port.comprarCredito(carrito, cedula, plazoMeses);
         
     }
      public List<Factura> obtenerFactura(String cedula) {
          return port.obtenerFactura(cedula);
      }
       public List<Tabla> consultarTablaAmortizacion(String cedula) {
        return port.consultarTablaAmortizacion(cedula);
    }
}