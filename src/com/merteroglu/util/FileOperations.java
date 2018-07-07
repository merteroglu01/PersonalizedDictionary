package com.merteroglu.util;

import com.merteroglu.Config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileOperations {

    public List<String> readAllLinesFromFile() throws IOException{
        Charset charset = Charset.forName("UTF-8");
        return Files.readAllLines(Paths.get(Config.fullPath),charset);
    }

    public boolean checkFirstStart() throws IOException{
        File file = new File(Config.fullPath);
        if(!file.exists()) {
            file = new File(Config.subPath);
            file.mkdir();
            file = new File(Config.fullPath);
            file.createNewFile();
            return true;
        }
        else return false;
    }

    public static void addToDictionary(String foreignWord, String nativeWord) throws IOException {
        String row = foreignWord.trim() + " " + nativeWord.trim() + " " + System.currentTimeMillis() + "\n";
        Path path = Paths.get(Config.fullPath);
        Files.write(path,row.getBytes(Charset.forName("UTF-8")),APPEND);
    }
}
