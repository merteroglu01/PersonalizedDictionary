package com.merteroglu;

import com.merteroglu.util.FileOperations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private FileOperations fileOperations;
    private String data;
    private List<String[]> splittedLines;
    @Override

    // TODO: 28.06.2018 try catch for empty file
    // TODO: 28.06.2018 handle throws
    // TODO: 28.06.2018 addtoDictionary
    // TODO: 28.06.2018 get from dictionary
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Personalized Dictionary");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();
        fileOperations = new FileOperations();
        fileOperations.checkFirstStart();
        data = fileOperations.readFileAsText();
        splittedLines = new ArrayList<>();
        String[] lines = data.split("\n");
        for (String line :
                lines) {
            splittedLines.add(line.split(" "));
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
