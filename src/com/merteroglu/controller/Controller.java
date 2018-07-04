package com.merteroglu.controller;

import com.merteroglu.Config;
import com.merteroglu.Dictionary;
import com.merteroglu.util.FileOperations;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

public class Controller {

    @FXML
    private Button addWordBtn;

    @FXML
    private TextField addForeignWordTextField;

    @FXML
    private TextField addNativeWordTextField;

    @FXML
    private Button dailyExerciseBtn;

    @FXML
    private Button weeklyExerciseBtn;

    @FXML
    private Button fullExerciseBtn;

    @FXML
    private Button checkBtn;

    @FXML
    private TextField tryForeignWordTextField;

    private String tryForeignWord = "";

    @FXML
    private TextField tryNativeWordTextField;

    @FXML
    private Text foreignWordText;

    @FXML
    private Text nativeWordText;

    @FXML
    private ToggleButton changeInputAreaToggleBtn;

    @FXML
    private Label infoText;

    @FXML
    private Text linkedInURL;

    private static Desktop desktop = Desktop.getDesktop();

    @FXML
    private Text webSiteURL;

    @FXML
    private Text licenceURL;

    private short exerciseType = 0;

    private String[] row = new String[3];

    private Random random = new Random();

    private String tryNativeWord = "";

    private String nativeWordInput = "";

    private String foreignWordInput = "";

    private static final String linkedInURLasString = "https://linkedin.com/in/merteroglu";

    private static final String webSiteURLasString = "https://www.merteroglu.com.tr";

    private static final String licenceURLasString = webSiteURLasString + "/general-licence.html";

    /**
     * if new word added to system, save that to words.txt file
     */
    @FXML
    private void saveToDictionary() {
        try {
            FileOperations.addToDictionary(addForeignWordTextField.getText(), addNativeWordTextField.getText());
            String[] row = new String[]{addForeignWordTextField.getText(), addNativeWordTextField.getText(), String.valueOf(System.currentTimeMillis())};
            /*
            dynamically add the new added word the active lists
             */
            Dictionary.dailyExerciseWordList.add(row);
            Dictionary.weeklyExerciseWordList.add(row);
            Dictionary.allTimeExerciseWordList.add(row);
            /*
            update list sizes
             */
            updateDictionarySizes();
            /*
            show success message to user
             */
            infoText.setText("Succesfully added to dictionary!");

        } catch (IOException e) {
            infoText.setText("Unknown Error Occurred While Trying to Save");
        }
    }

    /*
        updates the list sizes
    */
    private void updateDictionarySizes() {
        Dictionary.dailyListSize += 1;
        Dictionary.weeklyListSize += 1;
        Dictionary.allTimeListSize += 1;
    }

    /**
     * select next word based on the exercise mod
     */
    @FXML
    private void goToNextWord() {
        int tmpNumber;
        switch (Config.exerciseMod) {
            case 0: {
                if(Config.shuffleMod) tmpNumber = random.nextInt(Dictionary.dailyListSize);
                else tmpNumber = getCounterNumber();
                tryForeignWord = Dictionary.dailyExerciseWordList.get(tmpNumber)[0];
                tryNativeWord = Dictionary.dailyExerciseWordList.get(tmpNumber)[1];
                nativeWordText.setText(tryNativeWord);
                foreignWordText.setText(tryForeignWord);
                break;
            }
            case 1: {
                if(Config.shuffleMod) tmpNumber = random.nextInt(Dictionary.weeklyListSize);
                else tmpNumber = getCounterNumber();
                tryForeignWord = Dictionary.weeklyExerciseWordList.get(tmpNumber)[0];
                tryNativeWord = Dictionary.weeklyExerciseWordList.get(tmpNumber)[1];
                foreignWordText.setText(tryForeignWord);
                nativeWordText.setText(tryNativeWord);
                break;
            }
            case 2: {
                if(Config.shuffleMod) tmpNumber = random.nextInt(Dictionary.allTimeListSize);
                else tmpNumber = getCounterNumber();
                tryForeignWord = Dictionary.allTimeExerciseWordList.get(tmpNumber)[0];
                tryNativeWord = Dictionary.allTimeExerciseWordList.get(tmpNumber)[1];
                foreignWordText.setText(tryForeignWord);
                nativeWordText.setText(tryNativeWord);
                break;
            }
            default:
                break;
        }
    }

    /**
     * set mod to daily mod and choose next word from daily dictionary list
     */
    @FXML
    private void dailyMod() {
        Config.exerciseMod = 0;
        infoText.setText("Daily Exercise Mod Activated!");
        if (Dictionary.dailyExerciseWordList.size() == 0) infoText.setText("Your Dictionary Is Empty !");
        else {
            row = Dictionary.dailyExerciseWordList.get(random.nextInt(Dictionary.dailyListSize));
            if (exerciseType == 0) {
                foreignWordText.setText(row[0]);
                tryForeignWord = row[0];
                tryNativeWord = row[1];
            } else {
                nativeWordText.setText(row[1]);
                tryForeignWord = row[0];
                tryNativeWord = row[1];
            }
        }
    }

    /**
     * set mod to weekly mod and choose next word from weekly dictionary list
     */
    @FXML
    private void weeklyMod() {
        Config.exerciseMod = 1;
        infoText.setText("Weekly Exercise Mod Activated!");
        if (Dictionary.weeklyExerciseWordList.size() == 0) infoText.setText("Your Dictionary Is Empty !");
        else {
            row = Dictionary.weeklyExerciseWordList.get(random.nextInt(Dictionary.weeklyListSize));
            if (exerciseType == 0) {
                foreignWordText.setText(row[0]);
                tryForeignWord = row[0];
                tryNativeWord = row[1];
            } else {
                nativeWordText.setText(row[1]);
                tryNativeWord = row[1];
                tryForeignWord = row[0];

            }
        }
    }
    /**
     * set mod to all time mod and choose next word from all time dictionary list
     */
    @FXML
    private void allTimeMod() {
        Config.exerciseMod = 2;
        infoText.setText("All Time Exercise Mod Activated!");
        if (Dictionary.allTimeExerciseWordList.size() == 0) infoText.setText("Your Dictionary Is Empty !");
        else {
            row = Dictionary.allTimeExerciseWordList.get(random.nextInt(Dictionary.allTimeListSize));
            if (exerciseType == 0) {
                foreignWordText.setText(row[0]);
                tryNativeWord = row[1];
                tryForeignWord = row[0];
            } else {
                nativeWordText.setText(row[1]);
                tryNativeWord = row[1];
                tryForeignWord = row[0];
            }
        }
    }

    /**
     * it changes the input area like native word exercise or foreign word exercise
     */
    @FXML
    private void changeExerciseType() {
        if (exerciseType == 0) {
            foreignWordText.setVisible(false);
            tryForeignWordTextField.setVisible(true);
            nativeWordText.setVisible(true);
            tryNativeWordTextField.setVisible(false);
            exerciseType = 1;
        } else {
            foreignWordText.setVisible(true);
            tryForeignWordTextField.setVisible(false);
            nativeWordText.setVisible(false);
            tryNativeWordTextField.setVisible(true);
            exerciseType = 0;
        }
        switch (Config.exerciseMod) {
            case 0:
                dailyMod();
                break;
            case 1:
                weeklyMod();
                break;
            case 2:
                allTimeMod();
                break;
            default:
                break;
        }
        infoText.setText("Exercise Type Changed !");
    }

    /**
     * it controls the input is correct
     */

    @FXML
    private void check() {
        if (!tryForeignWord.isEmpty()) {
            if (exerciseType == 0) {
                nativeWordInput = tryNativeWordTextField.getText().trim().toLowerCase();
                if (tryNativeWord.equals(nativeWordInput)) {
                    infoText.setText("Correct!");
                    goToNextWord();
                } else infoText.setText("Not correct!");
            } else {
                foreignWordInput = tryForeignWordTextField.getText().trim().toLowerCase();
                if (tryForeignWord.equals(foreignWordInput)) {
                    infoText.setText("Correct!");
                    goToNextWord();
                }
            }
        } else {
            infoText.setText("Please Choose Exercise Mod !");
        }
    }

    @FXML
    private void linkedInURLonClick() {
        try {
            desktop.browse(URI.create(linkedInURLasString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void webSiteURLonClick() {
        try {
            desktop.browse(URI.create(webSiteURLasString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void licenceURLonClick() {
        try {
            desktop.browse(URI.create(licenceURLasString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeShuffleMod(){
        if(Config.shuffleMod) { Config.shuffleMod = false; infoText.setText("Shuffle Mod Closed!"); }
        else { Config.shuffleMod = true; infoText.setText("Shuffle Mod Opened!"); }
    }

    /**
     * it returns next word index based on the shuffle mod
     * @return next word index
     */
    private int getCounterNumber() {
        switch (Config.exerciseMod) {
            case 0:
                if (Config.lastWordIndexInList + 1 >= Dictionary.dailyListSize) {
                    infoText.setText("All Words Studied!");
                    Config.lastWordIndexInList = 0;
                    return 0;
                } else
                    return ++Config.lastWordIndexInList;
            case 1:
                if (Config.lastWordIndexInList + 1 >= Dictionary.weeklyListSize) {
                    infoText.setText("All Words Studied!");
                    Config.lastWordIndexInList = 0;
                    return 0;
                } else
                    return ++Config.lastWordIndexInList;
            case 2:
                if (Config.lastWordIndexInList + 1 >= Dictionary.allTimeListSize) {
                    infoText.setText("All Words Studied!");
                    Config.lastWordIndexInList = 0;
                    return 0;
                } else
                    return ++Config.lastWordIndexInList;
            default:
                return 0;
        }
    }

}
