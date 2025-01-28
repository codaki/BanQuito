package com.example.telfquito_soap_java.controller;

import com.example.telfquito_soap_java.service.ImagenService;

public class ImageController {
    private ImagenService wsImageService;

    public ImageController(ImagenService wsImageService) {
        this.wsImageService = wsImageService;
    }

    public interface ImageCallback {
        void onSuccess(String response);
        void onError(String errorMessage);
    }

    public interface ImageDownloadCallback {
        void onDownloadSuccess(byte[] imageData);
        void onError(String errorMessage);
    }

    // Method to upload an image
    public void uploadImage(String fileName, byte[] imageData, final ImageCallback callback) {
        wsImageService.uploadImage(fileName, imageData, new ImagenService.SoapCallback() {
            @Override
            public void onSuccess(String response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    // Method to download an image
    public void downloadImage(String fileName, final ImageDownloadCallback callback) {
        wsImageService.downloadImage(fileName, new ImagenService.DownloadSoapCallback() {
            @Override
            public void onDownloadSuccess(byte[] imageData) {
                callback.onDownloadSuccess(imageData);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }
}
