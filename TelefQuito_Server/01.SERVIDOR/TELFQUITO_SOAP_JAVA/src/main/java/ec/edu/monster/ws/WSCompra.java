/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ec.edu.monster.ws;

import ec.edu.monster.DAO.ClienteComercializadoraDAO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import ec.edu.monster.DAO.CompraDAO;
import ec.edu.monster.DAO.TelefonoDAO;
import ec.edu.monster.banquito.SujetoCreditoService;
import ec.edu.monster.banquito.MontoMaximoService;
import ec.edu.monster.banquito.GenerarTablaService;
import ec.edu.monster.servicio.CompraService;
import ec.edu.monster.bdd.DBConnection;
import ec.edu.monster.models.Factura;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danie
 */

@WebService(serviceName = "WSCompra")
public class WSCompra {

    private CompraService compraService;

    public WSCompra() {
        try {
            Connection connection = DBConnection.getConnection();
            CompraDAO compraDAO = new CompraDAO(connection);
            TelefonoDAO telefonoDAO = new TelefonoDAO(connection);
            ClienteComercializadoraDAO clientecDAO = new ClienteComercializadoraDAO(connection);
            SujetoCreditoService sujetoCreditoService = new SujetoCreditoService();
            MontoMaximoService montoMaximoService = new MontoMaximoService();
            GenerarTablaService generarTablaService = new GenerarTablaService();

            this.compraService = new CompraService(compraDAO, telefonoDAO, clientecDAO, sujetoCreditoService,
                    montoMaximoService, generarTablaService);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
     * Web service operation to verify a customer
     */
    @WebMethod(operationName = "comprarEfectivo")
    public String comprarEfectivo(@WebParam(name = "codTelefono") int codTelefono,
            @WebParam(name = "codcCedula") String codcCedula) {
        try {
            return compraService.realizarCompraEfectivo(codTelefono, codcCedula, "Efectivo");
        } catch (Exception e) {
            return "Error al realizar la compra en efectivo: " + e.getMessage();
        }
    }
/**
     * Web service operation to verify a customer
     */
    @WebMethod(operationName = "comprarCredito")
    public String comprarCredito(@WebParam(name = "codTelefono") int codTelefono,
            @WebParam(name = "cedula") String cedula, @WebParam(name = "plazoMeses") int plazoMeses) {
        try {
            return compraService.realizarCompraCredito(codTelefono, cedula, "Crédito Directo", plazoMeses);
        } catch (Exception e) {
            return "Error al realizar la compra con crédito: " + e.getMessage();
        }
    }
    /**
     * Web service operation factura
     * @param cedula
     * @return 
     */
     @WebMethod(operationName = "obtenerFactura")
    public List<Factura> obtenerFactura(@WebParam(name = "cedula") String cedula) {
        return compraService.obtenerFactura(cedula);
    }
}