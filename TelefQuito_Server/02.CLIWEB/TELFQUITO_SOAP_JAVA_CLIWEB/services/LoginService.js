const SoapClient = require("./soapclient");

class AuthService {
  constructor() {
    this.wsdlUrl = "http://192.168.8.83:8080/TELFQUITO_SOAP_JAVA/WSLogin?wsdl";
  }

  async authenticate(username, password) {
    try {
      const soapClient = new SoapClient(this.wsdlUrl);
      const client = await soapClient.createClient();

      //   const hashedPassword = SoapClient.hashPassword(password);

      return new Promise((resolve, reject) => {
        client.auth({ username, password: password }, (err, result) => {
          if (err) reject(err);
          resolve(result.return);
        });
      });
    } catch (error) {
      console.error("Authentication Error:", error);
      throw error;
    }
  }
}

module.exports = new AuthService();
