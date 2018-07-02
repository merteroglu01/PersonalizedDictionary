package com.merteroglu;

import com.merteroglu.util.FileOperations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {

    private FileOperations fileOperations;
    private String data = "";
    public static List<String[]> splittedLines;
    @Override

    // TODO: 28.06.2018 try catch for empty file
    // TODO: 28.06.2018 handle throws
    // TODO: 28.06.2018 addtoDictionary
    // TODO: 28.06.2018 get from dictionary
    // TODO: 2.07.2018 add comment lines
    public void start(Stage primaryStage) throws Exception{
        fileOperations = new FileOperations();
        data = initialize();
        if(!data.isEmpty()) Dictionary.createDictionary(data);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Personalized Dictionary");
        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();
    }

    private String initialize() throws IOException {
        fileOperations.checkFirstStart();
        return data = fileOperations.readFileAsText();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
