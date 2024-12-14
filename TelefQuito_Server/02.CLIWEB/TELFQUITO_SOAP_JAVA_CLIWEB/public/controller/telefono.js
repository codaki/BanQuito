import { showModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
  document
    .getElementById("crudTelefonosButton")
    .addEventListener("click", () => {
      window.location.href = "/crudTelefonos";
    });

  document
    .getElementById("consultarCreditosButton")
    .addEventListener("click", () => {
      window.location.href = "/consultarCreditos";
    });

  fetchAllTelefonos(); // Fetch all teléfonos when the page loads
});

async function searchTelefono() {
  const telefonoId = document.getElementById("telefonoId").value;

  try {
    const response = await fetch("/getTelefonoById", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ id: telefonoId }),
    });

    const result = await response.json();

    if (result.success) {
      populateTelefono(result.telefono);
    } else {
      showModal("Error", "Fallo en recuperar el teléfono.");
    }
  } catch (error) {
    console.error("Search telefono error:", error);
    showModal(
      "Error",
      "Un error ha ocurrido durante la búsqueda del teléfono. Por favor, revise la conexión."
    );
  }
}

async function fetchAllTelefonos() {
  try {
    const response = await fetch("/getAllTelefonos");
    const result = await response.json();

    if (result.success) {
      populateTelefonos(result.result);
    } else {
      showModal("Error", "Fallo en recuperar los teléfonos.");
    }
  } catch (error) {
    console.error("Fetch all telefonos error:", error);
    showModal(
      "Error",
      "Un error ha ocurrido durante la recuperación de los teléfonos. Por favor, revise la conexión."
    );
  }
}

function populateTelefono(telefono) {
  const telefonoInfo = document.getElementById("telefono-info");
  telefonoInfo.innerHTML = `
    <div>
      <div class="font-bold text-black">ID: ${telefono.codTelefono}</div>
      <div class="font-normal text-gray-700">Nombre: ${telefono.nombre}</div>
      <div class="font-normal text-gray-700">Precio: $${telefono.precio}</div>
      <div class="font-normal text-gray-700">Marca: ${telefono.marca}</div>
      <div class="font-normal text-gray-700">Disponible: ${telefono.disponible}</div>
    </div>
  `;
}

function populateTelefonos(telefonos) {
  const telefonoInfo = document.getElementById("movements-list");
  telefonoInfo.innerHTML = "";

  telefonos.forEach((telefono) => {
    const telefonoItem = document.createElement("div");
    telefonoItem.className = "movement-item";
    telefonoItem.innerHTML = `
      <div class="row bg-radius2 bg-secondary-light py-3 mb-2">
        <div class="col-6">
          <div class="font-bold text-black">ID: ${telefono.codTelefono}</div>
          <div class="font-normal text-gray-700">Nombre: ${telefono.nombre}</div>
          <div class="font-normal text-gray-700">Precio: $${telefono.precio}</div>
        </div>
        <div class="col-6 d-flex flex-column align-items-end">
          <div class="font-normal text-gray-700">Marca: ${telefono.marca}</div>
          <div class="font-normal text-gray-700">Disponible: ${telefono.disponible}</div>
          <div class="d-flex justify-content-end mt-2">
            <button class="btn btn-primary mr-2" onclick="editTelefono(${telefono.codTelefono})">Editar</button>
            <button class="btn btn-primary mr-2" onclick="deleteTelefono(${telefono.codTelefono})">Gestionar</button>
          </div>
        </div>
      </div>
    `;
    telefonoInfo.appendChild(telefonoItem);
  });
}
