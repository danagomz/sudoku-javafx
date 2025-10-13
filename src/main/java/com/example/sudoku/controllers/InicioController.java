package com.example.sudoku.controllers;

import com.example.sudoku.models.AlertBox;
import com.example.sudoku.models.IAlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioController {

    private IAlertBox alertBox;

    @FXML
    private Button btnJugar;

    @FXML
    private Button btnReglas;

    @FXML
    void onActionJugar(ActionEvent event) {
/*
        SudokuView sudokuView = SudokuView.getInstance();
        Sudoku.show();

        // Close stage
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();

        SudokuController sudokuController = sudokuView.getSudokuController(); */

    }

    @FXML
    void onActionReglas(ActionEvent event) {
        IAlertBox alertBox = new AlertBox();
        alertBox.mostrarReglasSudoku();
    }

}


