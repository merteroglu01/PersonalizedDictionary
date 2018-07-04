package com.merteroglu;

public class Config {

    /**
     * stores the shuffle mod situation
     * initialize mod is off.
     */
    public static boolean shuffleMod = false;

    public static final String userHomePath = System.getProperty("user.home");

    public static final String defaulFolderName = "/Personal Dictionary";

    public static final String fileName = "/words.txt";

    public static final String subPath = userHomePath + defaulFolderName;

    public static final String fullPath = subPath + fileName;

    /**
     * stores the last word index in list if shuffle mod off
     * if shuffle mod on it gets random numbers within the range that specified from list size.
     */
    public static int lastWordIndexInList = 0;

    /**
     *  it specifies which exercise mod is active.
     *  Daily, Weekly, AllTime
     */
    public static int exerciseMod = 0;
}
