
package ec.edu.monster.test;


import ec.edu.monster.dao.ClienteDAO;
import ec.edu.monster.model.Cliente;

import java.sql.Date;

public class TestCRUD {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();

        // Crear un nuevo cliente
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setCedula("1234567890");
        nuevoCliente.setNombre("Luis Martínez");
        nuevoCliente.setGenero("M");
        nuevoCliente.setFechaNacimiento(Date.valueOf("1995-06-15"));

        try {
            clienteDAO.createCliente(nuevoCliente);

            // Leer todos los clientes
            for (Cliente cliente : clienteDAO.getAllClientes()) {
                System.out.println("Cliente: " + cliente.getNombre());
            }

            // Actualizar un cliente
            Cliente clienteActualizado = clienteDAO.getClienteById(1);
            if (clienteActualizado != null) {
                clienteActualizado.setNombre("Juan Carlos Pérez");
                clienteDAO.updateCliente(clienteActualizado);
            }

            // Eliminar un cliente
            clienteDAO.deleteCliente(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}