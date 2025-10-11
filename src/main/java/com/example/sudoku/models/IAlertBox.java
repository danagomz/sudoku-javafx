package com.example.sudoku.models;

/**
 * Interfaz que define los métodos para mostrar diferentes tipos de alertas
 * dentro del juego Sudoku 2x3.
 *
 * @author Dana Sofia Gomez, Miguel Angel Lasso
 * @version 1.0
 */
public interface IAlertBox {

    /**
     * Muestra las reglas del juego Sudoku 2x3.
     */
    void mostrarReglasSudoku();

    /**
     * Muestra una alerta de confirmación para iniciar un nuevo juego.
     *
     * @return true si el usuario confirma, false si cancela.
     */
    boolean mostrarConfirmacionNuevoJuego();

    /**
     * Muestra un mensaje de victoria cuando se completa correctamente el tablero.
     */
    void mostrarVictoria();
}

