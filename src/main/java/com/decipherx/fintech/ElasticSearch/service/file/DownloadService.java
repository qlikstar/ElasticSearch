package com.decipherx.fintech.ElasticSearch.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class DownloadService {

    @Autowired
    UnzipService unzipService;

    private String fileDownloadUrl= "https://www.askebsa.dol.gov/FOIA%20Files/2016/Latest/F_5500_2016_Latest.zip";
    private String downloadedFileName;

    public String getFileDownloadUrl() {
        return fileDownloadUrl;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        if (!fileDownloadUrl.trim().equals(""))
            this.fileDownloadUrl = fileDownloadUrl;
    }

    public String getDownloadedFileName() {
        return downloadedFileName;
    }

    public void setDownloadedFileName(String downloadedFileName) {
        this.downloadedFileName = downloadedFileName;
    }


    private static final int BUFFER_SIZE = 4096;

    /**
     * Downloads a file from a URL
     * @throws IOException
     */
    public void downloadFile() throws IOException {

        URL url = new URL(fileDownloadUrl);
        System.out.println("Downloading the file ...... from " + fileDownloadUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                fileName = fileDownloadUrl.substring(fileDownloadUrl.lastIndexOf("/") + 1,fileDownloadUrl.length());
            }
            File folder = new File("target/downloads");
            if(!folder.exists()){
                folder.mkdir();
            }

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = folder + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded to : " + saveFilePath);
            System.out.println("File Download : Done");

        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }

    public void unzipDownloadedFile(){
        unzipService.unzipZippedFile();
    }



//    public static void main(String[] args) {
//        DownloadService downloadService = new DownloadService();
//        try {
//            downloadService.downloadFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //downloadService.downloadFileNew();
//    }

}
