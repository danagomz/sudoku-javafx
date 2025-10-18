package com.example.sudoku.controllers;

import com.example.sudoku.models.AlertBox;
import com.example.sudoku.models.IAlertBox;
import com.example.sudoku.models.Sudoku;
import com.example.sudoku.views.SudokuView;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador de la pantalla de inicio del juego Sudoku.
 * Permite al usuario iniciar el juego o ver las reglas.
 * @author Dana Sofia Gomez, Miguel Angel Lasso
 * @version 1.0
 */
public class InicioController {

    /** Botón para iniciar el juego. */
    @FXML
    private Button btnJugar;

    /** Botón para mostrar las reglas del juego. */
    @FXML
    private Button btnReglas;

    /**
     * Acción que se ejecuta al presionar el botón "Jugar".
     * Muestra un mensaje de inicio y, si el usuario confirma,
     * se abriría la ventana del Sudoku.
     *
     * @param event Evento generado al presionar el botón.
     */
    @FXML
    void onActionJugar(ActionEvent event) throws IOException {
        IAlertBox alertBox = new AlertBox();
        if (alertBox.mostrarInicio()) {
        SudokuView sudokuView = SudokuView.getInstance();
        sudokuView.show();

        // Close stage
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();

        }
    }

    /**
     * Acción que se ejecuta al presionar el botón "Reglas".
     * Muestra una ventana con las reglas del Sudoku.
     *
     * @param event Evento generado al presionar el botón.
     */
    @FXML
    void onActionReglas(ActionEvent event) {
        IAlertBox alertBox = new AlertBox();
        alertBox.mostrarReglasSudoku();
    }

}


