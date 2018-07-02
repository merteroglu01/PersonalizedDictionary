package com.merteroglu;

public class Config {

    public static boolean shuffleMod = true;
    public static String userHomePath = System.getProperty("user.home");
    public static String defaulFolderName = "/Personal Dictionary";
    public static String fileName = "/words.txt";
    public static String subPath = userHomePath + defaulFolderName;
    public static String fullPath = subPath + fileName;
}
