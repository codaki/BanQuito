const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");
const authService = require("./services/LoginService");
const compraService = require("./services/CompraService");
const telefonoService = require("./services/TelefonoService");
const telefonoController = require("./public/controller/telefonoController"); // Importa el controlador de teléfono
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

app.post("/comprarEfectivo", async (req, res) => {
  const { codTelefono, codCedula } = req.body;
  try {
    const result = await compraService.comprarEfectivo(codTelefono, codCedula);
    res.json({ success: true, result });
  } catch (error) {
    res.status(500).json({
      success: false,
      message: "Compra en efectivo error",
      error: error.message,
    });
  }
});

app.post("/comprarCredito", async (req, res) => {
  const { codTelefono, cedula, plazoMeses } = req.body;
  try {
    const result = await compraService.comprarCredito(
      codTelefono,
      cedula,
      plazoMeses
    );
    res.json({ success: true, result });
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
  const { codCredito } = req.body;
  try {
    const result = await compraService.consultarTablaAmortizacion(codCredito);
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
  try {
    const result = await telefonoService.updateTelefono(telefono);
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

// Integrar el controlador de teléfono
telefonoController(app);

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
