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
/**
 *
 * @author danie
 */
@WebService(serviceName = "WSMontoMáximo")
public class WSMontoMáximo {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "montoMaximo")
    public double hello(@WebParam(name = "codigo") int codigo) throws SQLException {
        MontoMaximoService monto = new MontoMaximoService();
        return monto.calcularMontoMaximoCredito(codigo);
    }
}
