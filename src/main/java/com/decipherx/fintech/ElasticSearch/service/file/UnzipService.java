package com.decipherx.fintech.ElasticSearch.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class UnzipService {

    @Autowired
    FileProcessor fileProcessor;

    private String zippedFileLocation = "target/downloads/F_5500_2016_Latest.zip";

    public UnzipService() {
    }

    public void unzipZippedFile(){

        byte[] buffer = new byte[1024];

        try{

            //create output directory is not exists
            File folder = new File("target/unzipped");
            if(!folder.exists()){
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zippedFileLocation));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){

                String fileName = ze.getName();
                File newFile = new File(folder +"/" + fileName);

                System.out.println("file unzip : "+ newFile.getAbsoluteFile());

                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Unzipping the file: Done");
            fileProcessor.processCSVFile();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) throws IOException {
//        UnzipService unzipService = new UnzipService();
//        unzipService.unzipZippedFile();
//    }

}
