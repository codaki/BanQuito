const SoapClient = require("./soapclient");

class CompraService {
  constructor() {
    this.wsdlUrl = "http://localhost:8080/TELFQUITO_SOAP_JAVA/WSCompra?wsdl";
    this.namespace = "http://ws.monster.edu.ec/";
  }

  async comprarEfectivo(codTelefono, codCedula) {
    try {
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      return new Promise((resolve, reject) => {
        const args = {
          codTelefono: codTelefono,
          codcCedula: codCedula,
        };

        client.comprarEfectivo(args, (err, result) => {
          if (err) {
            console.error("SOAP Request Error:", err);
            return reject(err);
          }
          resolve(result.return);
        });
      });
    } catch (error) {
      console.error("Compra Efectivo Error:", error);
      throw error;
    }
  }

  async comprarCredito(codTelefono, cedula, plazoMeses) {
    try {
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      return new Promise((resolve, reject) => {
        const args = {
          codTelefono: codTelefono,
          cedula: cedula,
          plazoMeses: plazoMeses,
        };

        client.comprarCredito(args, (err, result) => {
          if (err) {
            console.error("SOAP Request Error:", err);
            return reject(err);
          }
          resolve(result.return);
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
          resolve(result.return);
        });
      });
    } catch (error) {
      console.error("Obtener Factura Error:", error);
      throw error;
    }
  }

  async consultarTablaAmortizacion(codCredito) {
    try {
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      return new Promise((resolve, reject) => {
        const args = {
          codCredito: codCredito,
        };

        client.consultarTablaAmortizacion(args, (err, result) => {
          if (err) {
            console.error("SOAP Request Error:", err);
            return reject(err);
          }
          resolve(result.return);
        });
      });
    } catch (error) {
      console.error("Consultar Tabla Amortizacion Error:", error);
      throw error;
    }
  }
}

module.exports = new CompraService();
