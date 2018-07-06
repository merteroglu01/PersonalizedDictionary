package com.merteroglu;

import com.merteroglu.controller.Controller;
import com.merteroglu.util.FileOperations;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    /**
     * handles the file operations such as open, close , read ...
     */
    private FileOperations fileOperations;

    /**
     * after successfully read operation result will be stored as String format
     */
    private String data = "";

    @Override
    public void start(Stage primaryStage) throws Exception {

        fileOperations = new FileOperations();

        data = initialize();
        /**
         * if file is empty ( first start ) don't try to create dictionary.
         */
        if (!data.isEmpty()) Dictionary.createDictionary(data);

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

    /**
     * checks the program is executed first time or not
     * calls file reader method to read the words.txt file
     *
     * @return
     * @throws IOException
     */
    private String initialize() throws IOException {
        fileOperations.checkFirstStart();
        return data = fileOperations.readFileAsText();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
