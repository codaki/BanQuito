package ec.edu.monster.test;

import ec.edu.monster.DAO.ClienteComercializadoraDAO;
import ec.edu.monster.DAO.CompraDAO;
import ec.edu.monster.DAO.TelefonoDAO;
import ec.edu.monster.servicio.CompraService;
import ec.edu.monster.banquito.SujetoCreditoService;
import ec.edu.monster.banquito.MontoMaximoService;
import ec.edu.monster.banquito.GenerarTablaService;
import ec.edu.monster.bdd.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestCompra {
    public static void main(String[] args) {
  

        try (Connection connection = DBConnection.getConnection()) {
            // Crear instancias de DAO y servicios
            CompraDAO comprasDAO = new CompraDAO(connection);
            TelefonoDAO telefonoDAO = new TelefonoDAO(connection);
            ClienteComercializadoraDAO clientecDAO = new  ClienteComercializadoraDAO(connection);
            SujetoCreditoService sujetoCreditoService = new SujetoCreditoService();
            MontoMaximoService montoMaximoService = new MontoMaximoService();
            GenerarTablaService generarTablaService = new GenerarTablaService();

            // Crear instancia de CompraService
            CompraService compraService = new CompraService(comprasDAO, telefonoDAO,clientecDAO, sujetoCreditoService, montoMaximoService, generarTablaService);

            // Datos quemados para probar
            int codTelefono = 5; // Asegúrate de que este ID exista en la tabla `telefonos`
            String codcCliente = "0506070809"; // Asegúrate de que este ID exista en la tabla `cliente_comercializadora`
            String formaPagoEfectivo = "Efectivo";
            String formaPagoCredito = "Crédito Directo";
            int plazoMeses = 12;

            // Probar compra en efectivo
//            try {
//                compraService.realizarCompraEfectivo(codTelefono, codcCliente, formaPagoEfectivo);
//                System.out.println("Compra en efectivo realizada con éxito.");
//            } catch (Exception e) {
//                System.err.println("Error al realizar compra en efectivo: " + e.getMessage());
//            }

            // Probar compra con crédito directo
            try {
                compraService.realizarCompraCredito(codTelefono, codcCliente, formaPagoCredito, plazoMeses);
                System.out.println("Compra con crédito directo realizada con éxito.");
            } catch (Exception e) {
                System.err.println("Error al realizar compra con crédito directo: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}