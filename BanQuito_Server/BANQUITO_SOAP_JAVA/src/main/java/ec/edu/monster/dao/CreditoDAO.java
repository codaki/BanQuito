
package ec.edu.monster.dao;

import ec.edu.monster.bdd.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


    public class CreditoDAO {
    public boolean tieneCreditoActivo(String cedula) throws SQLException {
        String sql = " SELECT COUNT(*) FROM credito cr INNER JOIN cliente cl ON cr.COD_CLIENTE = cl.COD_CLIENTE WHERE cl.CEDULA = ? AND NOT EXISTS ( SELECT 1 FROM amortizacion WHERE COD_CREDITO = cr.COD_CREDITO AND SALDO > 0) ";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cedula);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        }
    }
}


