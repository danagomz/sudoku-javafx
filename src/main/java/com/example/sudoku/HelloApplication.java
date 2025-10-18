package com.example.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("inicio-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Sudoku");
        stage.getIcons().add(new Image(
                getClass().getResourceAsStream("/com/example/sudoku/images/pngwing.com (1).png")
        ));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
