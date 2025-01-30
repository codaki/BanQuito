const SoapClient = require("./soapclient");

class ImageService {
    constructor() {
        this.wsdlUrl = "http://192.168.8.83:8080/TELFQUITO_SOAP_JAVA/WSImageService?wsdl";
    }

    async GetImage(filename) {
        try {
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            return new Promise((resolve, reject) => {
                client.downloadImage({ fileName: filename }, (err, result) => {
                    if (err) {
                        return reject(err);
                    }
                    if (!result || !result.return) {
                        return reject(new Error("Invalid SOAP Response"));
                    }
                    resolve(result.return); // Base64 string of the image
                });
            });
        } catch (error) {
            console.error("ImageError:", error);
            throw error;
        }
    }

    async UploadImage(filename, imageBase64) {
        try {
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            console.log("Uploading image:", filename);
            return new Promise((resolve, reject) => {
                const args = {
                    fileName: filename,
                    imageData: imageBase64,
                };

                client.uploadImage(args, (err, result) => {
                    if (err) {
                        console.error("SOAP Request Error:", err);
                        return reject(err);
                    }
                    if (!result || !result.return) {
                        console.error("Invalid SOAP Response:", result);
                        return reject(new Error("Invalid SOAP Response"));
                    }
                    resolve(result.return);
                });
            });
        } catch (error) {
            console.error("ImageError:", error);
            throw error;
        }
    }
}

module.exports = new ImageService();
