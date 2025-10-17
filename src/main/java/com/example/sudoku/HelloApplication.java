package com.example.sudoku;

import com.example.sudoku.views.InicioView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        InicioView inicioView = InicioView.getInstance();
        inicioView.show();

    }
}
