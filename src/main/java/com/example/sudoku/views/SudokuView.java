package com.example.sudoku.views;

import com.example.sudoku.controllers.SudokuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa la ventana principal del juego Sudoku.
 * Implementa el patrón Singleton para garantizar una única instancia.
 *
 * @author Miguel Angel Lasso y Danna Sofia Gomez
 * @version 1.0
 */
public class SudokuView extends Stage {

    private SudokuController sudokuController;

    /**
     * Constructor que inicializa la ventana del juego Sudoku.
     * Carga la interfaz FXML, obtiene el controlador y configura las propiedades de la ventana.
     *
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    public SudokuView() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/sudoku-view.fxml")
        );
        Parent root = fxmlLoader.load();
        this.sudokuController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle("Sudoku");
        this.getIcons().add(new Image(
                getClass().getResourceAsStream("/com/example/sudoku/images/pngwing.com (1).png")
        ));
        this.setResizable(false);
    };

    /**
     * Devuelve el controlador asociado a esta vista.
     *
     * @return el controlador de Sudoku
     */
    public SudokuController getSudokuController() { return this.sudokuController; }

    /**
     * Metodo estatico que devuelve la unica instancia de la clase Sudoku.view
     * Implementa el patrón Singleton usando una clase holder interna.
     *
     * @return la instancia única de SudokuView
     * @throws IOException si ocurre un error al crear la instancia
     */
    public static SudokuView getInstance() throws IOException{
        if (SudokuViewHolder.INSTANCE == null){
            SudokuViewHolder.INSTANCE = new SudokuView();
        }
        return SudokuViewHolder.INSTANCE;
    }

    /**
     * Clase holder interna para la implementación del patrón Singleton.
     * Garantiza una inicialización perezosa y thread-safe.
     */
    private static class SudokuViewHolder{
        private static SudokuView INSTANCE;
    }

}