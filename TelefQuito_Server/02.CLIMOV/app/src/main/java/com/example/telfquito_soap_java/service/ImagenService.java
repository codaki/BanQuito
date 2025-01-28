package com.example.telfquito_soap_java.service;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Base64;

public class ImagenService {

    private static final String TAG = "WSImageService";
    private static final String NAMESPACE = "http://ws.monster.edu.ec/";
    private static final String UPLOAD_METHOD_NAME = "uploadImage";
    private static final String UPLOAD_SOAP_ACTION = "http://ws.monster.edu.ec/WSImage/uploadImageRequest";
    private static final String DOWNLOAD_METHOD_NAME = "downloadImage";
    private static final String DOWNLOAD_SOAP_ACTION = "http://ws.monster.edu.ec/WSImage/downloadImageRequest";
    private static final String URL = "http://192.168.225.83:8080/TELFQUITO_SOAP_JAVA/WSImageService";
    private static final int TIMEOUT = 15000;

    public interface SoapCallback {
        void onSuccess(String response);
        void onError(String errorMessage);
    }

    public interface DownloadSoapCallback {
        void onDownloadSuccess(byte[] imageData);
        void onError(String errorMessage);
    }

    @SuppressLint("StaticFieldLeak")
    public void uploadImage(final String fileName, final byte[] imageData, final SoapCallback callback) {
        new AsyncTask<Void, Void, AsyncTaskResult<String>>() {
            @Override
            protected AsyncTaskResult<String> doInBackground(Void... voids) {
                try {
                    Log.d(TAG, "Creating SOAP request for uploadImage with fileName=" + fileName);

                    // Create SOAP request
                    SoapObject request = new SoapObject(NAMESPACE, UPLOAD_METHOD_NAME);

                    // Add properties
                    PropertyInfo fileNameProperty = new PropertyInfo();
                    fileNameProperty.setName("fileName");
                    fileNameProperty.setValue(fileName);
                    fileNameProperty.setType(String.class);
                    request.addProperty(fileNameProperty);

                    PropertyInfo imageDataProperty = new PropertyInfo();
                    imageDataProperty.setName("imageData");
                    imageDataProperty.setValue(Base64.getEncoder().encodeToString(imageData));
                    imageDataProperty.setType(String.class);
                    request.addProperty(imageDataProperty);

                    Log.d(TAG, "Request object created: " + request.toString());

                    // Configure SOAP envelope
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = false;

                    Log.d(TAG, "Envelope configured");

                    // Configure transport with timeout
                    HttpTransportSE transport = new HttpTransportSE(URL, TIMEOUT);
                    transport.debug = true;

                    try {
                        Log.d(TAG, "Making SOAP request to " + URL);
                        transport.call(UPLOAD_SOAP_ACTION, envelope);

                        Log.d(TAG, "Request dump: " + transport.requestDump);
                        Log.d(TAG, "Response dump: " + transport.responseDump);

                        // Handle response
                        if (envelope.getResponse() instanceof SoapPrimitive) {
                            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                            return new AsyncTaskResult<>(response.toString());
                        } else {
                            return new AsyncTaskResult<>(new Exception("Unexpected response type"));
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "SOAP call failed", e);
                        return new AsyncTaskResult<>(e);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Request preparation failed", e);
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            protected void onPostExecute(AsyncTaskResult<String> result) {
                if (result.getError() != null) {
                    String errorMessage = "Error: " + result.getError().getMessage();
                    Log.e(TAG, errorMessage);
                    callback.onError(errorMessage);
                } else {
                    Log.d(TAG, "Success! Response: " + result.getResult());
                    callback.onSuccess(result.getResult());
                }
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void downloadImage(final String fileName, final DownloadSoapCallback callback) {
        new AsyncTask<Void, Void, AsyncTaskResult<byte[]>>() {
            @Override
            protected AsyncTaskResult<byte[]> doInBackground(Void... voids) {
                try {
                    Log.d(TAG, "Creating SOAP request for downloadImage with fileName=" + fileName);

                    // Create SOAP request
                    SoapObject request = new SoapObject(NAMESPACE, DOWNLOAD_METHOD_NAME);

                    // Add properties
                    PropertyInfo fileNameProperty = new PropertyInfo();
                    fileNameProperty.setName("fileName");
                    fileNameProperty.setValue(fileName);
                    fileNameProperty.setType(String.class);
                    request.addProperty(fileNameProperty);

                    Log.d(TAG, "Request object created: " + request.toString());

                    // Configure SOAP envelope
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = false;

                    Log.d(TAG, "Envelope configured");

                    // Configure transport with timeout
                    HttpTransportSE transport = new HttpTransportSE(URL, TIMEOUT);
                    transport.debug = true;

                    try {
                        Log.d(TAG, "Making SOAP request to " + URL);
                        transport.call(DOWNLOAD_SOAP_ACTION, envelope);

                        Log.d(TAG, "Request dump: " + transport.requestDump);
                        Log.d(TAG, "Response dump: " + transport.responseDump);

                        // Handle response
                        if (envelope.getResponse() instanceof SoapPrimitive) {
                            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                            byte[] imageData = Base64.getDecoder().decode(response.toString());
                            return new AsyncTaskResult<>(imageData);
                        } else {
                            return new AsyncTaskResult<>(new Exception("Unexpected response type"));
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "SOAP call failed", e);
                        return new AsyncTaskResult<>(e);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Request preparation failed", e);
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            protected void onPostExecute(AsyncTaskResult<byte[]> result) {
                if (result.getError() != null) {
                    String errorMessage = "Error: " + result.getError().getMessage();
                    Log.e(TAG, errorMessage);
                    callback.onError(errorMessage);
                } else {
                    Log.d(TAG, "Success! Response: " + result.getResult());
                    callback.onDownloadSuccess(result.getResult());
                }
            }
        }.execute();
    }

    private static class AsyncTaskResult<T> {
        private T result;
        private Exception error;

        AsyncTaskResult(T result) {
            this.result = result;
        }

        AsyncTaskResult(Exception error) {
            this.error = error;
        }

        T getResult() {
            return result;
        }

        Exception getError() {
            return error;
        }
    }
}
