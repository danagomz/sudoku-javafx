package com.example.sudoku.models;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Implementación de la interfaz IAlertBox.
 * Gestiona las alertas personalizadas del juego Sudoku 2x3,
 * como reglas, confirmaciones y mensajes de victoria.
 *
 * @author Dana Sofia Gomez, Miguel Angel Lasso
 * @version 1.0
 */
public class AlertBox implements IAlertBox {

    /**
     * {@inheritDoc}
     */
    @Override
    public void mostrarReglasSudoku() {
        String reglas = """
                🎯 REGLAS DEL SUDOKU 2x3 🎯
                
                • TABLERO: 6x6 celdas dividido en 6 regiones de 2x3
                • OBJETIVO: Llenar el tablero con números del 1 al 6
                • REGLAS:
                  ✓ Cada FILA debe contener números 1-6 sin repetir
                  ✓ Cada COLUMNA debe contener números 1-6 sin repetir
                  ✓ Cada REGIÓN 2x3 debe contener números 1-6 sin repetir
                
                🆘 BOTÓN AYUDA:
                • Puedes usar ayuda UNA vez por partida
                • La ayuda revelará un número correcto
                • ⚠️ NO podrás GANAR si usas ayuda
                
                ❌ ERRORES:
                • El juego te avisará cuando cometas un error
                • Los errores no terminan el juego
                • Puedes corregir errores libremente
                
                🏆 VICTORIA:
                • Completa TODO el tablero correctamente
                • ¡SIN usar ayuda para GANAR!
                """;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reglas del Sudoku");
        alert.setHeaderText("📖 CÓMO JUGAR AL SUDOKU 2x3");
        alert.setContentText(reglas);

        alert.setWidth(600);
        alert.setHeight(700);
        alert.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean mostrarConfirmacionNuevoJuego() {
        String mensaje = """
                ¿Estás seguro de que quieres comenzar un nuevo juego?
                Si ya habías empezado un tablero, perderás el progreso actual.
                """;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nuevo Juego");
        alert.setHeaderText("Confirmar Nuevo Juego");
        alert.setContentText(mensaje);

        ButtonType botonSi = new ButtonType("Sí", ButtonBar.ButtonData.YES);
        ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(botonSi, botonNo);

        return alert.showAndWait().filter(response -> response == botonSi).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mostrarVictoria() {
        String mensaje = "¡FELICITACIONES! Completaste el Sudoku correctamente.";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Victoria!");
        alert.setHeaderText("¡GANASTE EL JUEGO!");
        alert.setContentText(mensaje);

        ButtonType botonNuevoJuego = new ButtonType("Nuevo Juego", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(botonNuevoJuego);

        alert.showAndWait();
    }
}

