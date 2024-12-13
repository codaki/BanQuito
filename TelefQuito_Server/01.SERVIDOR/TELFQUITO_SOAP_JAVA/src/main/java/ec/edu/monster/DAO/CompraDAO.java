
package ec.edu.monster.DAO;

import ec.edu.monster.models.Compras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompraDAO {
    private Connection connection;

    public CompraDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createCompraEfectivo(Compras compra) {
        String query = "INSERT INTO compras (forma_pago, fecha, cod_telefono, codc_cliente, descuento, precio_final) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, compra.getFormaPago());
            stmt.setString(2, compra.getFecha());
            stmt.setInt(3, compra.getCodTelefono());
            stmt.setInt(4, compra.getCodcCliente());
            stmt.setDouble(5, compra.getDescuento());
            stmt.setDouble(6, compra.getPreciofinal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean createCompraCredito(Compras compra) {
        String query = "INSERT INTO compras (forma_pago, fecha, cod_telefono, codc_cliente, precio_final) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, compra.getFormaPago());
            stmt.setString(2, compra.getFecha());
            stmt.setInt(3, compra.getCodTelefono());
            stmt.setInt(4, compra.getCodcCliente());
            stmt.setDouble(5, compra.getPreciofinal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
