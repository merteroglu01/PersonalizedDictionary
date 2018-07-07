package com.merteroglu;

import java.util.ArrayList;
import java.util.List;

public final class Dictionary {

    /**
        List that holds the words that added to file in a current day
     */
    public static List<String[]> dailyExerciseWordList = new ArrayList<>();
    /**
     * List that holds the words that added to file in a week.
     */
    public static List<String[]> weeklyExerciseWordList = new ArrayList<>();
    /**
     * List that holds the words that added to file from first start
     */
    public static List<String[]> allTimeExerciseWordList = new ArrayList<>();

    /**
     * one day in milliseconds to calculate whether word is added within a current day
     */
    private static final long ONE_DAY_IN_MILLI_SECONDS = 86400000L;

    /**
     * one week in milliseconds to calculate whether the word is added within a current week
     */
    private static final long ONE_WEEK_IN_MILLI_SECONDS = 86400000L;

    /**
     * currentTime holds the current time
     */
    private static final long currentTime = System.currentTimeMillis();

    public static int dailyListSize = 0;

    public static int weeklyListSize = 0;

    public static int allTimeListSize = 0;


    /**
     * initializer method that creates list based on the added time
     * @param allLines All file(words.txt) read as List<String>
     */
    static void createDictionary(List<String> allLines){
        for (String line : allLines) {
            String[] tmp = (line.split(" "));
            /*
            if word added within a day
             */
            if(currentTime - Long.valueOf(tmp[2]) < ONE_DAY_IN_MILLI_SECONDS) {
                dailyExerciseWordList.add(tmp);
            }
            /*
            if word added within 7 days.
             */
            else if(currentTime - Long.valueOf(tmp[2]) > ONE_WEEK_IN_MILLI_SECONDS) allTimeExerciseWordList.add(tmp);
            /*
            if word added before 7 days.
             */
            else if(ONE_DAY_IN_MILLI_SECONDS < currentTime - Long.valueOf(tmp[2])  && currentTime - Long.valueOf(tmp[2]) < ONE_WEEK_IN_MILLI_SECONDS){
                weeklyExerciseWordList.add(tmp);
            }
        }

        weeklyExerciseWordList.addAll(dailyExerciseWordList);
        allTimeExerciseWordList.addAll(dailyExerciseWordList);
        allTimeExerciseWordList.addAll(weeklyExerciseWordList);

        dailyListSize = dailyExerciseWordList.size();
        weeklyListSize = weeklyExerciseWordList.size();
        allTimeListSize = allTimeExerciseWordList.size();
    }
}
