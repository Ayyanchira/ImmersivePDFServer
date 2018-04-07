package com.ssdi.immersivepdf.service;

import java.io.File;

public interface S3Services {
    public void downloadFile(String keyName);
    public void uploadFile(String keyName, File fileToUpload);
}
