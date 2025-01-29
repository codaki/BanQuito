package ec.edu.monster.view;

import ec.edu.monster.models.CarritoE;
import ec.edu.monster.ws.TelefonoCarrito;
import ec.edu.monster.ws.*;
import java.util.List;
import java.util.Scanner;

public class MainView {

  private static LoginService loginService = new LoginService();
  private static CompraServicio compraServicio = new CompraServicio();
  private static TelefonoServicio telefonoServicio = new TelefonoServicio();
  private static CarritoE carrito = new CarritoE();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Login
    System.out.println("Ingrese su nombre de usuario:");
    String username = scanner.nextLine();
    System.out.println("Ingrese su contraseña:");
    String password = scanner.nextLine();

    if (loginService.auth(username, password)) {
      System.out.println("Login exitoso!");
      mainMenu(scanner);
    } else {
      System.out.println("Login fallido!");
    }
  }

  private static void mainMenu(Scanner scanner) {
    while (true) {
      System.out.println("Seleccione una opción:");
      System.out.println("1. Consultar tabla de amortizaciones");
      System.out.println("2. Gestionar teléfonos");
      System.out.println("3. Obtener facturas");
      System.out.println("4. Carrito de compras");
      System.out.println("5. Salir");

      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline

      switch (choice) {
        case 1:
          consultarTablaAmortizacion(scanner);
          break;
        case 2:
          gestionarTelefonos(scanner);
          break;
        case 3:
          obtenerFacturas(scanner);
          break;
        case 4:
          gestionarCarrito(scanner);
          break;
        case 5:
          System.out.println("Saliendo...");
          return;
        default:
          System.out.println("Opción no válida.");
      }
    }
  }

  private static void gestionarCarrito(Scanner scanner) {
    while (true) {
      System.out.println("Seleccione una opción:");
      System.out.println("1. Agregar teléfono al carrito");
      System.out.println("2. Mostrar contenido del carrito");
      System.out.println("3. Pagar elementos del carrito");
      System.out.println("4. Vaciar carrito");
      System.out.println("5. Regresar");

      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline

      switch (choice) {
        case 1:
          agregarTelefonoAlCarrito(scanner);
          break;
        case 2:
          mostrarContenidoCarrito();
          break;
        case 3:
          pagarElementosCarrito(scanner);
          break;
        case 4:
          carrito.vaciarCarrito();
          System.out.println("Carrito vaciado.");
          break;
        case 5:
          return;
        default:
          System.out.println("Opción no válida.");
      }
    }
  }

  private static void agregarTelefonoAlCarrito(Scanner scanner) {
    List<Telefonos> telefonos = telefonoServicio.getAllTelefonos();
    if (telefonos == null || telefonos.isEmpty()) {
      System.out.println("No hay teléfonos disponibles.");
      return;
    }

    StringBuilder resultado = new StringBuilder();
    resultado.append("\n===== LISTA DE TELÉFONOS =====\n");
    resultado.append(String.format("%-15s %-20s %-10s %-15s %-10s%n",
        "CodTelefono", "Nombre", "Precio", "Marca", "Disponible"));
    resultado.append("-------------------------------------------------------------\n");

    for (Telefonos telefono : telefonos) {
      resultado.append(String.format("%-15d %-20s %-10.2f %-15s %-10d%n",
          telefono.getCodTelefono(), telefono.getNombre(), telefono.getPrecio(), telefono.getMarca(),
          telefono.getDisponible()));
    }

    System.out.println(resultado.toString());
    System.out.println("Ingrese el código del teléfono:");
    int telefonoId = scanner.nextInt();
    scanner.nextLine(); // consume newline
    System.out.println("Ingrese la cantidad:");
    int cantidad = scanner.nextInt();
    scanner.nextLine(); // consume newline

    carrito.agregarTelefono(telefonoId, cantidad);
    System.out.println("Teléfono agregado al carrito.");
  }

  private static void mostrarContenidoCarrito() {
    List<TelefonoCarrito> telefonosCarrito = carrito.getTelefonos();
    if (telefonosCarrito.isEmpty()) {
      System.out.println("El carrito está vacío.");
      return;
    }

    StringBuilder resultado = new StringBuilder();
    resultado.append("\n===== CONTENIDO DEL CARRITO =====\n");
    resultado.append(String.format("%-15s %-10s%n", "TelefonoId", "Cantidad"));
    resultado.append("-----------------------------------\n");

    for (TelefonoCarrito telefono : telefonosCarrito) {
      resultado.append(String.format("%-15d %-10d%n", telefono.getTelefonoId(), telefono.getCantidad()));
    }

    System.out.println(resultado.toString());
  }

  private static void pagarElementosCarrito(Scanner scanner) {
    if (carrito.getTelefonos().isEmpty()) {
      System.out.println("El carrito está vacío. No se puede proceder con la compra.");
      return;
    }

    System.out.println("Seleccione el método de pago:");
    System.out.println("1. Efectivo");
    System.out.println("2. Crédito");
    int metodoPago = scanner.nextInt();
    scanner.nextLine(); // consume newline

    System.out.println("Ingrese su cédula:");
    String cedula = scanner.nextLine();

    switch (metodoPago) {
      case 1:
//        String resultadoEfectivo = compraServicio.comprarEfectivo(carrito, cedula);
//          System.out.println("carrito");
//          System.out.println(carrito.getTelefonos());
      //System.out.println(resultadoEfectivo);
        break;
      case 2:
        System.out.println("Ingrese el plazo en meses:");
        int plazoMeses = scanner.nextInt();
        scanner.nextLine(); // consume newline
//       String resultadoCredito = compraServicio.comprarCredito(carrito, cedula, plazoMeses);
//        System.out.println(resultadoCredito);
        break;
      default:
        System.out.println("Método de pago no válido.");
        return;
    }

    carrito.vaciarCarrito(); // Vaciar el carrito después de la compra
    System.out.println("Compra realizada exitosamente.");
  }

  private static void mostrarTelefonos(List<Telefonos> telefonos) {
    StringBuilder resultado = new StringBuilder();
    resultado.append("\n===== LISTA DE TELÉFONOS =====\n");
    resultado.append(String.format("%-15s %-20s %-10s %-15s %-10s%n",
        "CodTelefono", "Nombre", "Precio", "Marca", "Disponible"));
    resultado.append("-------------------------------------------------------------\n");

    for (Telefonos telefono : telefonos) {
      resultado.append(String.format("%-15d %-20s %-10.2f %-15s %-10d%n",
          telefono.getCodTelefono(), telefono.getNombre(), telefono.getPrecio(), telefono.getMarca(),
          telefono.getDisponible()));
    }

    System.out.println(resultado.toString());
  }

  private static void consultarTablaAmortizacion(Scanner scanner) {
    System.out.println("Ingrese la cédula:");
    String cedula = scanner.nextLine();
    List<Tabla> tabla = compraServicio.consultarTablaAmortizacion(cedula);

    if (tabla == null || tabla.isEmpty()) {
      System.out.println("No se encontraron datos de amortización para la cédula: " + cedula);
      return;
    }

    StringBuilder resultado = new StringBuilder();
    resultado.append("\n===== TABLA DE AMORTIZACIÓN =====\n");
    resultado.append(String.format("%-15s %-10s %-15s %-15s %-15s %-15s%n",
        "CodCredito", "Cuota", "ValorCuota", "InteresPagado", "CapitalPagado", "Saldo"));
    resultado.append("------------------------------------------------------------------------\n");

    for (Tabla t : tabla) {
      resultado.append(String.format("%-15d %-10d %-15.2f %-15.2f %-15.2f %-15.2f%n",
          t.getCodCredito(), t.getCuota(), t.getValorCuota(), t.getInteresPagado(), t.getCapitalPagado(),
          t.getSaldo()));
    }

    System.out.println(resultado.toString());
  }

  private static void gestionarTelefonos(Scanner scanner) {
    while (true) {
      System.out.println("Seleccione una opción:");
      System.out.println("1. Mostrar teléfonos");
      System.out.println("2. Crear teléfono");
      System.out.println("3. Actualizar teléfono");
      System.out.println("4. Regresar");

      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline

      switch (choice) {
        case 1:
          mostrarTelefonos(telefonoServicio.getAllTelefonos());
          break;
        case 2:
          crearTelefono(scanner);
          break;
        case 3:
          actualizarTelefono(scanner);
          break;
        case 4:
          return;
        default:
          System.out.println("Opción no válida.");
      }
    }
  }

  private static void crearTelefono(Scanner scanner) {
    System.out.println("Ingrese los detalles del teléfono:");
    System.out.print("Nombre: ");
    String nombre = scanner.nextLine();
    System.out.print("Precio: ");
    double precio = scanner.nextDouble();
    scanner.nextLine(); // consume newline
    System.out.print("Marca: ");
    String marca = scanner.nextLine();
    System.out.print("Disponible (1 para sí, 0 para no): ");
    int disponible = scanner.nextInt();
    scanner.nextLine(); // consume newline

    Telefonos nuevoTelefono = new Telefonos();
    nuevoTelefono.setNombre(nombre);
    nuevoTelefono.setPrecio(precio);
    nuevoTelefono.setMarca(marca);
    nuevoTelefono.setDisponible(disponible);

    String resultInsert = telefonoServicio.insertTelefono(nuevoTelefono);
    System.out.println(resultInsert);
  }

  private static void actualizarTelefono(Scanner scanner) {
    List<Telefonos> telefonos = telefonoServicio.getAllTelefonos();
    if (telefonos == null || telefonos.isEmpty()) {
      System.out.println("No hay teléfonos disponibles.");
      return;
    }

    mostrarTelefonos(telefonos);
    System.out.println("Ingrese el código del teléfono a actualizar:");
    int codTelefonoActualizar = scanner.nextInt();
    scanner.nextLine(); // consume newline

    System.out.println("Ingrese los nuevos detalles del teléfono:");
    System.out.print("Nombre: ");
    String nombreActualizar = scanner.nextLine();
    System.out.print("Precio: ");
    double precioActualizar = scanner.nextDouble();
    scanner.nextLine(); // consume newline
    System.out.print("Marca: ");
    String marcaActualizar = scanner.nextLine();
    System.out.print("Disponible (1 para sí, 0 para no): ");
    int disponibleActualizar = scanner.nextInt();
    scanner.nextLine(); // consume newline

    Telefonos telefonoActualizado = new Telefonos();
    telefonoActualizado.setCodTelefono(codTelefonoActualizar);
    telefonoActualizado.setNombre(nombreActualizar);
    telefonoActualizado.setPrecio(precioActualizar);
    telefonoActualizado.setMarca(marcaActualizar);
    telefonoActualizado.setDisponible(disponibleActualizar);

    String resultUpdate = telefonoServicio.updateTelefono(telefonoActualizado);
    System.out.println(resultUpdate);
  }

  private static void obtenerFacturas(Scanner scanner) {
    System.out.println("Ingrese la cédula:");
    String cedula = scanner.nextLine();
    List<Factura> facturas = compraServicio.obtenerFactura(cedula);

    if (facturas == null || facturas.isEmpty()) {
      System.out.println("No se encontraron facturas para la cédula: " + cedula);
      return;
    }

    StringBuilder resultado = new StringBuilder();
    resultado.append("\n===== LISTA DE FACTURAS =====\n");
    resultado.append(String.format("%-10s %-15s %-15s %-10s %-15s %-20s %-20s %-15s%n",
        "CodCompra", "FormaPago", "Fecha", "Descuento", "PrecioFinal", "NombreCliente", "NombreTelefono",
        "MarcaTelefono"));
    resultado.append("---------------------------------------------------------------------------------------------\n");

    for (Factura factura : facturas) {
      resultado.append(String.format("%-10d %-15s %-15s %-10.2f %-15.2f %-20s %-20s %-15s%n",
          factura.getCodCompra(), factura.getFormaPago(), factura.getFecha(), factura.getDescuento(),
          factura.getPreciofinal(),
          factura.getNombreCliente(), factura.getNombreTelefono(), factura.getMarcaTelefono()));
    }

    System.out.println(resultado.toString());
  }
}
