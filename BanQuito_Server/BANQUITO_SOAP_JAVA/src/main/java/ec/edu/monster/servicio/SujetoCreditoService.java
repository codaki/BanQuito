
package ec.edu.monster.servicio;

import ec.edu.monster.dao.ClienteDAO;
import ec.edu.monster.dao.CreditoDAO;
import ec.edu.monster.dao.MovimientoDAO;
import java.sql.SQLException;

public class SujetoCreditoService {
   ClienteDAO clienteDAO = new ClienteDAO();
        MovimientoDAO movimientoDAO = new MovimientoDAO();
        CreditoDAO creditoDAO = new CreditoDAO();

        
    public boolean verificar( String cedula ){
         try {
            if (!clienteDAO.esCliente(cedula)) {
                System.out.println("El solicitante no es cliente del banco.");
                return false;
            }

            if (!movimientoDAO.tieneDepositoReciente(cedula)) {
                System.out.println("El cliente no tiene depósitos en el último mes.");
                return false;
            }

            if (!clienteDAO.cumpleRequisitoEdad(cedula)) {
                System.out.println("El cliente no cumple con el requisito de edad.");
                return false;
            }

            if (creditoDAO.tieneCreditoActivo(cedula)) {
                System.out.println("El cliente tiene un crédito activo.");
                return false;
            }
            

            System.out.println("El cliente cumple con todos los requisitos.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
