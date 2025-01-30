const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");
const authService = require("./services/LoginService");
const compraService = require("./services/CompraService");
const telefonoService = require("./services/TelefonoService");
const telefonoController = require("./public/controller/telefonoController");
const ImageService = require("./services/ImageService");
const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, "public")));
app.use(express.static(path.join(__dirname, "views")));

// Routes
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "login.html"));
});

app.get("/consultarCreditos", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "consultarCreditos.html"));
});

app.get("/crudTelefonos", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "crudTelefonos.html"));
});

app.post("/login", async (req, res) => {
  const { username, password } = req.body;
  try {
    const isAuthenticated = await authService.authenticate(username, password);
    res.json({ success: isAuthenticated });
  } catch (error) {
    res.status(500).json({ success: false, message: "Authentication error" });
  }
});

app.get("/account", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "account.html"));
});

app.get("/telefonos", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "telefonos.html"));
});

app.get("/crudTelefonos", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "crudTelefonos.html"));
});
app.get("/editarTelefonos", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "editarTelefonos.html"));
});

app.get("/comprarTelefono", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "comprarTelefono.html"));
});
app.post("/obtenerFacturas", async (req, res) => {
  const { cedula } = req.body;
  try {
    const result = await compraService.obtenerFactura(cedula);
    res.json({ success: true, facturas: result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Error al obtener las facturas",
      error: error.message,
    });
  }
});
app.post("/obtenerFacturaEspecifica", async (req, res) => {
  const { cedula, grupoId } = req.body;
  try {
    const result = await compraService.obtenerFacturaEspecifica(
      cedula,
      grupoId
    );
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Obtener factura específica error",
      error: error.message,
    });
  }
});
app.post("/comprarEfectivo", async (req, res) => {
  const { carrito, cedula } = req.body;
  console.log(carrito, cedula);
  try {
    const result = await compraService.comprarEfectivo(carrito, cedula);
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Compra en efectivo error",
      error: error.message,
    });
  }
});
app.get("/factura", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "factura.html"));
});
app.post("/comprarCredito", async (req, res) => {
  const { carrito, cedula, plazoMeses } = req.body;
  console.log(carrito, plazoMeses, cedula);
  try {
    const result = await compraService.comprarCredito(
      carrito,
      cedula,
      plazoMeses
    );
    res.json({ success: true, result, message: result.message });
    console.log(result);
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Compra con crédito error",
      error: error.message,
    });
  }
});

app.post("/obtenerFactura", async (req, res) => {
  const { cedula } = req.body;
  try {
    const result = await compraService.obtenerFactura(cedula);
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Obtener factura error",
      error: error.message,
    });
  }
});

app.post("/consultarTablaAmortizacion", async (req, res) => {
  const { cedula } = req.body;
  try {
    const result = await compraService.consultarTablaAmortizacion(cedula);
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Consultar tabla de amortización error",
      error: error.message,
    });
  }
});

app.post("/getTelefonoById", async (req, res) => {
  const { id } = req.body;
  try {
    const result = await telefonoService.getTelefonoById(id);
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Get teléfono by ID error",
      error: error.message,
    });
  }
});

app.post("/insertTelefono", async (req, res) => {
  const { telefono } = req.body;
  try {
    const result = await telefonoService.insertTelefono(telefono);
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Insert teléfono error",
      error: error.message,
    });
  }
});

app.post("/updateTelefono", async (req, res) => {
  const { telefono } = req.body;
  console.log(telefono);
  try {
    const result = await telefonoService.updateTelefono(telefono);
    console.log(result);
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Update teléfono error",
      error: error.message,
    });
  }
});

app.get("/getAllTelefonos", async (req, res) => {
  try {
    const result = await telefonoService.getAllTelefonos();
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Get all teléfonos error",
      error: error.message,
    });
  }
});

app.get("/getImage", async (req, res) => {
  const { filename } = req.query;

  try {
    const imageBase64 = await ImageService.GetImage(filename);
    res.json({ success: true, image: imageBase64 });
  } catch (error) {
    console.error("Error fetching image:", error);
    res.status(500).json({ success: false, message: "Error fetching image" });
  }
});

app.post("/uploadImage", async (req, res) => {
  const { fileName, imageData } = req.body;

  try {
    const result = await ImageService.UploadImage(fileName, imageData);
    res.json({ success: true, result });
  } catch (error) {
    console.error("Error uploading image");
    res.status(500).json({ success: false, message: "Error uploading image" });
  }
});

// Integrar el controlador de teléfono
telefonoController(app);

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
