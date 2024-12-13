package ec.edu.monster.servicio;


import ec.edu.monster.DAO.ClienteComercializadoraDAO;
import ec.edu.monster.DAO.CompraDAO;
import ec.edu.monster.models.Compras;
import ec.edu.monster.banquito.SujetoCreditoService;
import ec.edu.monster.banquito.MontoMaximoService;
import ec.edu.monster.banquito.GenerarTablaService;
import ec.edu.monster.models.Telefonos;
import ec.edu.monster.DAO.TelefonoDAO;

import java.time.LocalDate;

public class CompraService {
    private CompraDAO compraDAO;
    private TelefonoDAO telefonoDAO;
    private ClienteComercializadoraDAO clientecDAO;
    private SujetoCreditoService sujetoCreditoService;
    private MontoMaximoService montoMaximoService;
    private GenerarTablaService generarTablaService;

    public CompraService(CompraDAO compraDAO, TelefonoDAO telefonoDAO, ClienteComercializadoraDAO
            clientecDAO, SujetoCreditoService sujetoCreditoService, MontoMaximoService montoMaximoService, GenerarTablaService generarTablaService) {
        this.compraDAO = compraDAO;
        this.telefonoDAO = telefonoDAO;
        this.clientecDAO = clientecDAO;
        this.sujetoCreditoService = sujetoCreditoService;
        this.montoMaximoService = montoMaximoService;
        this.generarTablaService = generarTablaService;
    }



    public String realizarCompraEfectivo(int codTelefono, int codcCliente, String formaPago) throws Exception {
        Telefonos telefono = telefonoDAO.getTelefonoById(codTelefono);
        double precioOriginal = telefono.getPrecio();
        double descuento = precioOriginal * 0.42;
        double precioFinal = precioOriginal - descuento;

        Compras compra = new Compras();
        compra.setCodTelefono(codTelefono);
        compra.setCodcCliente(codcCliente);
        compra.setFormaPago(formaPago);
        compra.setFecha(LocalDate.now().toString());
        compra.setDescuento(descuento);
        compra.setPreciofinal(precioFinal);

        if(compraDAO.createCompraEfectivo(compra)){
             return("Compra en efectivo exitosa!!");
        }else{
            return("Compra en efectivo fallida");
        }
       
    }

    public String realizarCompraCredito(int codTelefono, String cedula, String formaPago, int plazoMeses)
            throws Exception {
        Telefonos telefono = telefonoDAO.getTelefonoById(codTelefono);
        double precioTelefono = telefono.getPrecio();

        // Verificar si el cliente es sujeto de crédito
        int esSujetoCredito = sujetoCreditoService.verifica(cedula);
        if (esSujetoCredito==0) {
            return("El cliente no es sujeto de crédito.");
        }
   
//        valor = sujetoCreditoService.obtenerCodClientePorCedula(cedula);
        // Obtener el monto máximo de crédito aprobado
        double montoMaximo = montoMaximoService.montoMaximo(esSujetoCredito);
        System.out.println(montoMaximo);
        if (precioTelefono > montoMaximo) {
            return("El precio del teléfono excede el monto máximo de crédito aprobado.");
        }
       
        // Crear la tabla de amortización
        String resultado = generarTablaService.crearCreditoYTablaAmortizacion(esSujetoCredito, precioTelefono, plazoMeses);
        if (!resultado.equals("Crédito y tabla de amortización creados exitosamente.")) {
            return("Error al crear la tabla de amortización.");
        }

        // Realizar la compra
        Compras compra = new Compras();
        compra.setCodTelefono(codTelefono);
        compra.setCodcCliente(clientecDAO.getCodClienteByCedula(cedula));
        compra.setFormaPago(formaPago);
        compra.setFecha(LocalDate.now().toString());
        compra.setPreciofinal(precioTelefono);

        if(compraDAO.createCompraCredito(compra)){
             return("Compra exitosa a credito!!");
        }else{
            return ("Compra fallida!!");
        }
       
    }
}