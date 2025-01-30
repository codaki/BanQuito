import { showModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
  document
    .getElementById("searchButton")
    .addEventListener("click", searchFacturas);
});

async function searchFacturas() {
  const cedula = document.getElementById("cedula").value;

  try {
    const response = await fetch("/obtenerFacturas", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ cedula: cedula }),
    });

    const result = await response.json();

    if (result.success) {
      groupFacturasByGrupoId(result.facturas);
    } else {
      showModal("Error", "Fallo en recuperar las facturas.");
    }
  } catch (error) {
    console.error("Search facturas error:", error);
    showModal(
      "Error",
      "Un error ha ocurrido durante la búsqueda de las facturas. Por favor, revise la conexión."
    );
  }
}

function groupFacturasByGrupoId(facturas) {
  const facturasContainer = document.getElementById("facturasContainer");
  facturasContainer.innerHTML = ""; // Clear previous invoices

  // Group purchases by grupoId
  const facturasGrouped = facturas.reduce((acc, factura) => {
    const { grupoId } = factura;
    if (!acc[grupoId]) {
      acc[grupoId] = {
        grupoId,
        fecha: factura.fecha,
        formaPago: factura.formaPago,
        precioTotal: 0,
        compras: [],
      };
    }
    acc[grupoId].compras.push(factura);
    acc[grupoId].precioTotal += factura.preciofinal;
    return acc;
  }, {});

  Object.values(facturasGrouped)
    .sort((a, b) => b.grupoId - a.grupoId)
    .forEach((factura) => {
      const facturaCard = document.createElement("div");
      facturaCard.classList.add("factura-card");

      facturaCard.innerHTML = `
        <h3>Factura #${factura.grupoId}</h3>
        <p><strong>Fecha:</strong> ${factura.fecha}</p>
        <p><strong>Forma de Pago:</strong> ${factura.formaPago}</p>
        <p><strong>Total:</strong> $${factura.precioTotal.toFixed(2)}</p>
        <button class="btn-primary ver-factura" data-cedula="${
          document.getElementById("cedula").value
        }" data-grupoid="${factura.grupoId}">Ver Factura</button>
      `;

      facturasContainer.appendChild(facturaCard);
    });

  // Attach event listeners to buttons
  document.querySelectorAll(".ver-factura").forEach((button) => {
    button.addEventListener("click", (event) => {
      const cedula = event.target.getAttribute("data-cedula");
      const grupoId = event.target.getAttribute("data-grupoid");
      verFactura(cedula, grupoId);
    });
  });
}

// Redirect to invoice details page
window.verFactura = function (cedula, grupoId) {
  window.location.href = `/facturaEspecifica.html?cedula=${cedula}&grupoId=${grupoId}`;
};
