import { showModal } from "./modal.js";

document
  .getElementById("telefonoForm")
  .addEventListener("submit", async (e) => {
    e.preventDefault();

    const nombre = document.getElementById("nombre").value;
    const precio = document.getElementById("precio").value;
    const marca = document.getElementById("marca").value;
    const disponible =
      document.getElementById("disponible").value === "true" ? 1 : 0;

    const telefono = {
      nombre,
      precio,
      marca,
      disponible,
    };

    try {
      const response = await fetch("/insertTelefono", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ telefono }),
      });

      const result = await response.json();

      if (result.success) {
        showModal("Éxito", "Teléfono guardado exitosamente.");
        window.location.href = "/telefonos";
      } else {
        showModal("Error", "Fallo al guardar el teléfono.");
      }
    } catch (error) {
      console.error("Insert telefono error:", error);
      showModal(
        "Error",
        "Un error ha ocurrido durante el guardado del teléfono. Por favor, revise la conexión."
      );
    }
  });
