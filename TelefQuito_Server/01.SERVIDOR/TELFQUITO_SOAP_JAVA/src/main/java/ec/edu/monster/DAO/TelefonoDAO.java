
package ec.edu.monster.DAO;

import ec.edu.monster.models.Telefonos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TelefonoDAO {
    private Connection connection;

    public TelefonoDAO(Connection connection) {
        this.connection = connection;
    }

    public Telefonos getTelefonoById(int id) throws SQLException {
        String query = "SELECT * FROM telefonos WHERE cod_telefono = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Telefonos telefono = new Telefonos();
                telefono.setCodTelefono(rs.getInt("cod_telefono"));
                telefono.setNombre(rs.getString("nombre"));
                telefono.setPrecio(rs.getDouble("precio"));
                return telefono;
            }
        }
        return null;
    }
     public void insertTelefono(Telefonos telefono) throws SQLException {
        String query = "INSERT INTO telefonos (nombre, precio) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, telefono.getNombre());
            stmt.setDouble(2, telefono.getPrecio());
            stmt.executeUpdate();
        }
    }

    public void updateTelefono(Telefonos telefono) throws SQLException {
        String query = "UPDATE telefonos SET nombre = ?, precio = ? WHERE cod_telefono = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, telefono.getNombre());
            stmt.setDouble(2, telefono.getPrecio());
            stmt.setInt(3, telefono.getCodTelefono());
            stmt.executeUpdate();
        }
    }
    public List<Telefonos> getAllTelefonos() throws SQLException {
        String query = "SELECT * FROM telefonos";
        List<Telefonos> telefonosList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Telefonos telefono = new Telefonos();
                telefono.setCodTelefono(rs.getInt("cod_telefono"));
                telefono.setNombre(rs.getString("nombre"));
                telefono.setPrecio(rs.getDouble("precio"));
                telefonosList.add(telefono);
            }
        }
        return telefonosList;
    }

}
