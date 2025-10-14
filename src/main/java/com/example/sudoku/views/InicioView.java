package com.example.sudoku.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioView extends Stage {

    public InicioView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/com/example/sudoku/inicio-view.fxml")
        );
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle("Sudoku");
    }

    public static InicioView getInstance() throws IOException {
        if (InicioView.InicioViewHolder.INSTANCE == null) {
            InicioView.InicioViewHolder.INSTANCE = new InicioView();
        }
        return InicioView.InicioViewHolder.INSTANCE;
    }

    private static class InicioViewHolder {
        private static InicioView INSTANCE = null;
    }
}
