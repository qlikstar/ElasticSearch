package com.decipherx.fintech.ElasticSearch.service.file;

import com.decipherx.fintech.ElasticSearch.service.RestService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileProcessor {

    private String[] headers;

    String filePath = "target/unzipped/f_5500_2016_latest.csv";

    public FileProcessor() {
    }

    public void processCSVFile() throws IOException {

        ExecutorService executor = Executors.newFixedThreadPool(100);
        File file = new File(filePath);
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");

        try {
            String line = it.nextLine();
            headers = line.split(",");

            int documentCounter = 1;
            while (it.hasNext()) {
                line = it.nextLine();
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                int i=0;
                StringJoiner result = new StringJoiner(",", "{", "}");
                for(String header : headers){
                    if (i < values.length) {
                        if (!values[i].trim().equals(""))
                            result.add("\"" + header + "\" : " + values[i].trim());
                        i++;
                    }
                }
                //System.out.println(result);
                Runnable worker = new RestService(documentCounter, result.toString());
                executor.execute(worker);
                documentCounter ++;
            }

        } finally {
            LineIterator.closeQuietly(it);
        }
    }

//    public static void main(String[] args) throws IOException {
//        FileProcessor fileProcessor = new FileProcessor();
//        fileProcessor.processCSVFile("target/unzipped/f_5500_2016_latest.csv");
//    }

}
