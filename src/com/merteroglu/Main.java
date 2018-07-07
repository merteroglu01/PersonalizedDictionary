package com.merteroglu;

import com.merteroglu.controller.Controller;
import com.merteroglu.util.FileOperations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    /**
     * handles the file operations such as open, close , read ...
     */
    private FileOperations fileOperations;

    /**
     * after successfully read operation result will be stored as List
     */
    private List<String> allLines = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        fileOperations = new FileOperations();

        /**
         * checks the program is executed first time or not
         */
        fileOperations.checkFirstStart();

        /**
         * reads all lines from file ( words.txt )
         */
        allLines = fileOperations.readAllLinesFromFile();

        /**
         * if file is empty ( first start ) don't try to create dictionary.
         */
        if (allLines.size() != 0) Dictionary.createDictionary(allLines);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller c = loader.getController();


        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setTitle("Personalized Dictionary");

        Scene scene = new Scene(root, 640, 400);

        primaryStage.setScene(scene);
        c.getTryNativeWordTextField().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    c.check();
                    break;
                case TAB:
                    c.goToNextWord();
                    break;
                default:
                    break;
            }
        });
        c.getTryForeignWordTextField().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    c.check();
                    break;
                case TAB:
                    c.goToNextWord();
                    break;
                default:
                    break;
            }
        });
        c.getAddNativeWordTextField().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                c.saveToDictionary();
            }
        });
        if(Dictionary.dailyListSize != 0) c.goToNextWord();
        c.getTryNativeWordTextField().requestFocus();
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
