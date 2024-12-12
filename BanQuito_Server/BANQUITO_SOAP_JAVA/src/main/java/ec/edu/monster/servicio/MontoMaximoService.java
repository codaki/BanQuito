
package ec.edu.monster.servicio;

import ec.edu.monster.dao.MovimientoDAO;
import java.sql.SQLException;

public class MontoMaximoService {
    MovimientoDAO movimientoDAO = new MovimientoDAO();
    
    public double calcularMontoMaximoCredito(int cod) throws SQLException {
        double promedioDepositos = movimientoDAO.obtenerPromedioDepositos(cod);
        double promedioRetiros = movimientoDAO.obtenerPromedioRetiros(cod);

        // Fórmula para calcular el monto máximo del crédito
        double diferenciaPromedios = promedioDepositos - promedioRetiros;
        double montoMaximoCredito = (diferenciaPromedios * 0.35) * 6;

        // Si la diferencia es negativa, el monto máximo del crédito será 0
        return Math.max(0, montoMaximoCredito);
    }
}
