package com.merteroglu;

import com.merteroglu.util.FileOperations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    public void start(Stage primaryStage) throws Exception{

        fileOperations = new FileOperations();

        data = initialize();
        /**
         * if file is empty ( first start ) don't try to create dictionary.
         */
        if(!data.isEmpty()) Dictionary.createDictionary(data);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Personalized Dictionary");

        primaryStage.getIcons().add(new Image("file:icon.png"));

        primaryStage.setScene(new Scene(root, 640, 400));

        primaryStage.show();
    }

    /**
     * checks the program is executed first time or not
     * calls file reader method to read the words.txt file
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
