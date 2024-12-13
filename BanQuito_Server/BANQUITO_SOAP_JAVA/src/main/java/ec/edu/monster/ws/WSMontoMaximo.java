/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ec.edu.monster.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import ec.edu.monster.servicio.MontoMaximoService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author danie
 */
@WebService(serviceName = "WSMontoMaximo")
public class WSMontoMaximo {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "montoMaximo")
    public double hello(@WebParam(name = "codigo") int codigo)  {
        MontoMaximoService monto = new MontoMaximoService();
        try {
            return monto.calcularMontoMaximoCredito(codigo);
        } catch (SQLException ex) {
            Logger.getLogger(WSMontoMaximo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
