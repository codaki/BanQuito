const SoapClient = require("./soapclient");

class CompraService {
  constructor() {
    this.wsdlUrl = "http://192.168.8.83:8080/TELFQUITO_SOAP_JAVA/WSCompra?wsdl";
    this.namespace = "http://ws.monster.edu.ec/";
  }

  async comprarEfectivo(carrito, codCedula) {
    try {
      console.log("Comprando en efectivo", carrito, codCedula);
      const args1 = {
        carrito: { telefonoCarrito: carrito },
        codcCedula: codCedula,
      };
      console.log("Args", args1);
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      return new Promise((resolve, reject) => {
        const args = {
          carrito: { telefonoCarrito: carrito },
          codcCedula: codCedula,
        };

        client.comprarEfectivo(args, (err, result) => {
          if (err) {
            console.error("SOAP Request Error:", err);
            return reject(err);
          }
          if (result && result.return) {
            resolve(result.return);
          } else {
            console.error("SOAP Response Error: No return value");
            return reject(new Error("No return value from SOAP response"));
          }
        });
      });
    } catch (error) {
      console.error("Compra Efectivo Error:", error);
      throw error;
    }
  }

  async comprarCredito(carrito, cedula, plazoMeses) {
    try {
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      return new Promise((resolve, reject) => {
        const args = {
          carrito: { telefonoCarrito: carrito },
          cedula: cedula,
          plazoMeses: plazoMeses,
        };

        client.comprarCredito(args, (err, result) => {
          if (err) {
            console.error("SOAP Request Error:", err);
            return reject(err);
          }
          if (result && result.return) {
            resolve(result.return);
          } else {
            console.error("SOAP Response Error: No return value");
            return reject(new Error("No return value from SOAP response"));
          }
        });
      });
    } catch (error) {
      console.error("Compra Credito Error:", error);
      throw error;
    }
  }

  async obtenerFactura(cedula) {
    try {
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      return new Promise((resolve, reject) => {
        const args = {
          cedula: cedula,
        };

        client.obtenerFactura(args, (err, result) => {
          if (err) {
            console.error("SOAP Request Error:", err);
            return reject(err);
          }
          if (result && result.return) {
            resolve(result.return);
          } else {
            console.error("SOAP Response Error: No return value");
            return reject(new Error("No return value from SOAP response"));
          }
        });
      });
    } catch (error) {
      console.error("Obtener Factura Error:", error);
      throw error;
    }
  }

  async consultarTablaAmortizacion(cedula) {
    try {
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      return new Promise((resolve, reject) => {
        const args = {
          cedula: cedula,
        };

        client.consultarTablaAmortizacion(args, (err, result) => {
          if (err) {
            console.error("SOAP Request Error:", err);
            return reject(err);
          }
          if (result && result.return) {
            resolve(result.return);
          } else {
            console.error("SOAP Response Error: No return value");
            return reject(new Error("No return value from SOAP response"));
          }
        });
      });
    } catch (error) {
      console.error("Error in consultarTablaAmortizacion:", error);
      throw error;
    }
  }
  async obtenerFacturaEspecifica(cedula, grupoId) {
    try {
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      return new Promise((resolve, reject) => {
        const args = {
          cedula: cedula,
          grupoId: grupoId,
        };

        client.obtenerFacturaEspecifica(args, (err, result) => {
          if (err) {
            console.error("SOAP Request Error:", err);
            return reject(err);
          }
          resolve(result.return);
        });
      });
    } catch (error) {
      console.error("Obtener Factura Especifica Error:", error);
      throw error;
    }
  }
}

module.exports = new CompraService();