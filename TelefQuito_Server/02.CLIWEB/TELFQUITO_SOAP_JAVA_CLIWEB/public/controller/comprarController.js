import { showModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
  const urlParams = new URLSearchParams(window.location.search);
  const telefonoId = urlParams.get("id");

  if (telefonoId) {
    loadTelefonoData(telefonoId);
  }

  document.getElementById("tipoCompra").addEventListener("change", (e) => {
    const tipoCompra = e.target.value;
    document.querySelectorAll(".compra-opcion").forEach((el) => {
      el.classList.add("hidden");
      el.querySelectorAll("input").forEach((input) => {
        input.removeAttribute("required");
      });
    });
    if (tipoCompra === "efectivo") {
      const compraEfectivo = document.getElementById("compraEfectivo");
      compraEfectivo.classList.remove("hidden");
      compraEfectivo
        .querySelector("input")
        .setAttribute("required", "required");
    } else if (tipoCompra === "credito") {
      const compraCredito = document.getElementById("compraCredito");
      compraCredito.classList.remove("hidden");
      compraCredito.querySelectorAll("input").forEach((input) => {
        input.setAttribute("required", "required");
      });
    }
  });

  document
    .getElementById("compraForm")
    .addEventListener("submit", async (e) => {
      e.preventDefault();

      const tipoCompra = document.getElementById("tipoCompra").value;
      const codTelefono = parseInt(
        document.getElementById("telefonoId").value,
        10
      );
      const cedula =
        tipoCompra === "efectivo"
          ? document.getElementById("cedula").value
          : document.getElementById("cedulaCredito").value;
      const plazoMeses =
        tipoCompra === "credito"
          ? parseInt(document.getElementById("plazoMeses").value, 10)
          : null;

      try {
        const response = await fetch(
          tipoCompra === "efectivo" ? "/comprarEfectivo" : "/comprarCredito",
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              codTelefono,
              cedula,
              ...(tipoCompra === "credito" && { plazoMeses }),
            }),
          }
        );

        const result = await response.json();
        console.log(result);
        if (result.success) {
          if (
            tipoCompra === "credito" &&
            result.message === "Compra exitosa a credito!!"
          ) {
            showModal("Éxito", "Compra realizada exitosamente.", () => {
              window.location.href = "/telefonos";
            });
          } else {
            showModal("Resultado", result.result);
          }
        } else {
          showModal("Error", "Fallo al realizar la compra.");
        }
      } catch (error) {
        console.error("Compra error:", error);
        showModal("Error", result, null, true);
      }
    });
});

async function loadTelefonoData(id) {
  try {
    const response = await fetch("/getTelefonoById", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ id }),
    });

    const result = await response.json();

    if (result.success) {
      const telefono = result.result;
      document.getElementById("telefonoId").value = telefono.codTelefono;
      document.getElementById("nombre").textContent = telefono.nombre;
      document.getElementById("precio").textContent = telefono.precio;
      document.getElementById("marca").textContent = telefono.marca;
    } else {
      showModal("Error", "Fallo en recuperar los datos del teléfono.");
    }
  } catch (error) {
    console.error("Load telefono data error:", error);
    showModal(
      "Error",
      "Un error ha ocurrido durante la carga de los datos del teléfono. Por favor, revise la conexión."
    );
  }
}
