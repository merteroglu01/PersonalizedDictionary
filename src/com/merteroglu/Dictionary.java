package com.merteroglu;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    public static List<String[]> dailyExerciseWordList = new ArrayList<>();
    public static List<String[]> weeklyExerciseWordList = new ArrayList<>();
    public static List<String[]> allTimeExerciseWordList = new ArrayList<>();
    public static final long ONE_DAY_IN_MILLI_SECONDS = 86400000L;
    public static final long ONE_WEEK_IN_MILLI_SECONDS = 86400000L;
    public static long currentTime = System.currentTimeMillis();
    public static int dailyListSize = 0;
    public static int weeklyListSize = 0;
    public static int allTimeListSize = 0;
    public static int exerciseMod = 0;

    public static void createDictionary(String data){
        String[] lines = data.split("\n");
        for (String line :
                lines) {
            String[] tmp = (line.split(" "));
            if(currentTime - Long.valueOf(tmp[2]) < ONE_DAY_IN_MILLI_SECONDS) {
                dailyExerciseWordList.add(tmp);
                weeklyExerciseWordList.add(tmp);
                allTimeExerciseWordList.add(tmp);
            }else if(currentTime - Long.valueOf(tmp[2]) > ONE_WEEK_IN_MILLI_SECONDS) allTimeExerciseWordList.add(tmp);
            else {
                weeklyExerciseWordList.add(tmp);
            }
        }
        weeklyExerciseWordList.addAll(dailyExerciseWordList);
        dailyListSize = dailyExerciseWordList.size();
        weeklyListSize = weeklyExerciseWordList.size();
        allTimeListSize = allTimeExerciseWordList.size();
    }
}
