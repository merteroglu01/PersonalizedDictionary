package com.merteroglu.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperations {

    private String data = "";
    private String dictionary = "/Personal Dictionary";
    private String fileName = "words.txt";
    private String fullPath = dictionary + fileName;

    public String readFileAsText() throws IOException{
        data = new String(Files.readAllBytes(Paths.get(fullPath)));
        System.out.println(data);
        return data;
    }

    public boolean checkFirstStart() throws IOException{
        String userHomePagePath = System.getProperty("user.home");
        File file = new File(userHomePagePath+"/Personal Dictionary");
        if(!file.exists()) {
            file.createNewFile();
            file.mkdir();
            file = new File(userHomePagePath + fullPath);
            file.createNewFile();
            return true;
        }
        else return false;
    }

    public void addToDictionary(String foreignWord, String nativeWord, Path path) throws IOException {
        String row = foreignWord+nativeWord+System.currentTimeMillis();
        Files.write(path,row.getBytes(Charset.forName("UTF-8")));
    }
}
