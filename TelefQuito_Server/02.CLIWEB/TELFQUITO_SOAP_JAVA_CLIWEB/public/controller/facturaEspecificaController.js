import { showModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
  const urlParams = new URLSearchParams(window.location.search);
  const cedula = urlParams.get("cedula");
  const grupoId = urlParams.get("grupoId");

  if (cedula && grupoId) {
    loadFacturaEspecificaData(cedula, grupoId);
  }
});

async function loadFacturaEspecificaData(cedula, grupoId) {
  try {
    const response = await fetch("/obtenerFacturaEspecifica", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ cedula, grupoId }),
    });

    const result = await response.json();
    if (result.success && result.result.length > 0) {
      const factura = result.result[0]; // Obtener la primera factura
      document.getElementById("cedulaCliente").textContent = cedula;
      document.getElementById("nombreCliente").textContent =
        factura.nombreCliente;
      document.getElementById("formaPago").textContent = factura.formaPago;
      document.getElementById("empleado").textContent = factura.vendedor;

      const productosTableBody = document
        .getElementById("productosTable")
        .querySelector("tbody");
      productosTableBody.innerHTML = "";

      // Agrupar productos por nombre y marca
      const productosMap = new Map();
      result.result.forEach((producto) => {
        const key = `${producto.nombreTelefono}-${producto.marcaTelefono}`;
        if (!productosMap.has(key)) {
          productosMap.set(key, {
            nombreTelefono: producto.nombreTelefono,
            marcaTelefono: producto.marcaTelefono,
            preciofinal: producto.preciofinal,
            cantidad: 0,
            subtotal: 0,
            descuento: producto.descuento || 0, // Asegurarse de incluir el descuento
          });
        }
        const productoAgrupado = productosMap.get(key);
        productoAgrupado.cantidad += 1;
        productoAgrupado.subtotal += producto.preciofinal;
        productoAgrupado.descuento = producto.descuento || 0; // Sumar el descuento
      });

      // Mostrar productos agrupados en la tabla
      let totalFactura = 0;
      let totalDescuento = 0;
      let cantidadfinal = 0;
      productosMap.forEach((producto) => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${producto.marcaTelefono}</td>
          <td>${producto.nombreTelefono}</td>
          <td>$${producto.preciofinal.toFixed(2)}</td>
          <td>$${producto.descuento}</td>
          <td>${producto.cantidad}</td>
          <td>$${producto.subtotal.toFixed(2)}</td>
        `;
        cantidadfinal = producto.cantidad;
        productosTableBody.appendChild(row);
        totalFactura += producto.subtotal;
        totalDescuento += producto.descuento; // Sumar el descuento
      });

      const descuentos = totalDescuento * cantidadfinal;
      const total = descuentos + totalFactura;
      // Mostrar el total de la factura
      document.getElementById("totalFactura").textContent = `$${total.toFixed(
        2
      )}`;
      console.log(totalFactura);
      console.log(cantidadfinal);

      // Mostrar el descuento
      document.getElementById(
        "descuentoFactura"
      ).textContent = `$${descuentos.toFixed(2)}`;

      // Calcular y mostrar el valor total de la factura (total menos el descuento)

      document.getElementById(
        "valorTotalFactura"
      ).textContent = `$${totalFactura.toFixed(2)}`;
    } else {
      showModal("Error", "Fallo en recuperar los datos de la factura.");
    }
  } catch (error) {
    console.error("Load factura data error:", error);
    showModal(
      "Error",
      "Un error ha ocurrido durante la carga de los datos de la factura. Por favor, revise la conexión."
    );
  }
}
