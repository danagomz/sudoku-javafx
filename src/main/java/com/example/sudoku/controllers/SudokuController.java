package com.example.sudoku.controllers;

import com.example.sudoku.models.AlertBox;
import com.example.sudoku.models.IAlertBox;
import com.example.sudoku.models.Sudoku;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import java.util.ArrayList;

public class SudokuController {
    /**
     * ctes del tamaño total del tablero (6x6)
     */
    private static final int TAMANO = 6;
    private static final int FILAS_POR_BLOQUE = 2;
    private static final int COLUMNAS_POR_BLOQUE = 3;

    private Sudoku sudoku;
    private IAlertBox alertBox;

    private ArrayList<TextField> todosLosCampos;
    @FXML
    private Button btnAyuda;

    @FXML
    private Button btnNuevoJuego;

    @FXML
    private Button btnVerificar;

    @FXML
    private GridPane gridpaneSudoku;

    @FXML
    private GridPane gridpaneb1, gridpaneb2, gridpaneb3, gridpaneb4, gridpaneb5, gridpaneb6;

    @FXML
    private TextField textField00, textField01, textField02, textField03, textField04, textField05;
    @FXML
    private TextField textField10, textField11, textField12, textField13, textField14, textField15;
    @FXML
    private TextField textField20, textField21, textField22, textField23, textField24, textField25;
    @FXML
    private TextField textField30, textField31, textField32, textField33, textField34, textField35;
    @FXML
    private TextField textField40, textField41, textField42, textField43, textField44, textField45;
    @FXML
    private TextField textField50, textField51, textField52, textField53, textField54, textField55;

    @FXML private void initialize() {
        iniciarTodo();
    }

    private void iniciarTodo() {
        sudoku = new Sudoku();
        alertBox = new AlertBox();
        sudoku.iniciarJuego();
        todosLosCampos = new ArrayList<>();
        guardarTodosLosCampos();
        configurarTodosLosEventos();
        actualizarPantalla();
    }

    @FXML
    void onActionAyuda(ActionEvent event) {
        darAyuda();
    }

    @FXML
    void onActionNuevoJuego(ActionEvent event) {
        iniciarTodo();
    }

    @FXML
    void onActionVerificar(ActionEvent event) {
        verificarTodoElTablero();
    }


    private void guardarTodosLosCampos() {
        // Agregar todos los TextFields a la lista en orden
        todosLosCampos.add(textField00);
        todosLosCampos.add(textField01);
        todosLosCampos.add(textField02);
        todosLosCampos.add(textField03);
        todosLosCampos.add(textField04);
        todosLosCampos.add(textField05);

        todosLosCampos.add(textField10);
        todosLosCampos.add(textField11);
        todosLosCampos.add(textField12);
        todosLosCampos.add(textField13);
        todosLosCampos.add(textField14);
        todosLosCampos.add(textField15);

        todosLosCampos.add(textField20);
        todosLosCampos.add(textField21);
        todosLosCampos.add(textField22);
        todosLosCampos.add(textField23);
        todosLosCampos.add(textField24);
        todosLosCampos.add(textField25);

        todosLosCampos.add(textField30);
        todosLosCampos.add(textField31);
        todosLosCampos.add(textField32);
        todosLosCampos.add(textField33);
        todosLosCampos.add(textField34);
        todosLosCampos.add(textField35);

        todosLosCampos.add(textField40);
        todosLosCampos.add(textField41);
        todosLosCampos.add(textField42);
        todosLosCampos.add(textField43);
        todosLosCampos.add(textField44);
        todosLosCampos.add(textField45);

        todosLosCampos.add(textField50);
        todosLosCampos.add(textField51);
        todosLosCampos.add(textField52);
        todosLosCampos.add(textField53);
        todosLosCampos.add(textField54);
        todosLosCampos.add(textField55);
    }

    private void configurarTodosLosEventos() {

        // Configurar evento Enter para cada TextField
        for (int i = 0; i < todosLosCampos.size(); i++) {
            TextField campo = todosLosCampos.get(i);
            final int fila = i / TAMANO;  // Dividir para obtener fila
            final int columna = i % TAMANO; // Resto para obtener columna

            campo.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    procesarNumero(fila, columna, campo.getText());
                }
            });
        }
    }

    private void procesarNumero(int fila, int columna, String texto) {
        if (texto.isEmpty()) {
            return;
        }

        try {
            int numero = Integer.parseInt(texto);

            // Verificar si el número es válido (1-6)
            if (numero < 1 || numero > 6) {
                alertBox.mostrarError();
                return;
            }

            // Verificar si la celda está vacía
            if (!sudoku.celdaEstaVacia(fila, columna)) {
                alertBox.mostrarError();
                return;
            }

            // Intentar colocar el número
            boolean exito = sudoku.intentarPonerNumero(fila, columna, numero);

            if (exito) {
                // Número válido - actualizar pantalla
                actualizarPantalla();
                verificarSiGano();
            } else {
                // Número inválido - marcar conflictos
                marcarConflictos(fila, columna, numero);
                alertBox.mostrarError();
            }

        } catch (NumberFormatException e) {
            alertBox.mostrarError();
        }
    }

    private void actualizarPantalla() {
        for (int i = 0; i < todosLosCampos.size(); i++) {
            int fila = i / TAMANO;
            int columna = i % TAMANO;
            TextField campo = todosLosCampos.get(i);
            int numero = sudoku.getNumeroEnCelda(fila, columna);

            if (numero != 0) {
                campo.setText(String.valueOf(numero));
                boolean esInicial = !sudoku.celdaEstaVacia(fila, columna);
                campo.setEditable(!esInicial);
                campo.setStyle(esInicial ?
                        "-fx-text-fill: black; -fx-background-color: #e0e0e0;" :
                        "-fx-text-fill: black; -fx-background-color: white;");
            } else {
                campo.setText("");
                campo.setEditable(true);
                campo.setStyle("-fx-text-fill: black; -fx-background-color: white;");
            }
        }
        btnAyuda.setDisable(!sudoku.sePuedeUsarAyuda());
    }

    private void limpiarColores() {
        for (int i = 0; i < todosLosCampos.size(); i++) {
            int fila = i / TAMANO;
            int columna = i % TAMANO;
            TextField campo = todosLosCampos.get(i);

            if (sudoku.celdaEstaVacia(fila, columna)) {
                // Celda vacía - fondo blanco, texto negro
                campo.setStyle("-fx-text-fill: black; -fx-background-color: white;");
            } else {
                // Celda con número - verificar si es inicial o del jugador
                if (!sudoku.celdaEstaVacia(fila, columna)) {
                    // Número inicial - fondo gris, texto negro
                    campo.setStyle("-fx-text-fill: black; -fx-background-color: #e0e0e0;");
                } else {
                    // Número del jugador - fondo blanco, texto negro
                    campo.setStyle("-fx-text-fill: black; -fx-background-color: white;");
                }
            }
        }
    }

    private void marcarConflictos(int filaProblematica, int columnaProblematica, int numeroProblematico) {
        limpiarColores();

        // Marcar celda problemática
        int indice = filaProblematica * TAMANO + columnaProblematica;
        todosLosCampos.get(indice).setStyle("-fx-text-fill: red;");

        // Buscar conflictos en fila
        for (int c = 0; c < TAMANO; c++) {
            if (c != columnaProblematica && sudoku.getNumeroEnCelda(filaProblematica, c) == numeroProblematico) {
                int idx = filaProblematica * TAMANO + c;
                todosLosCampos.get(idx).setStyle("-fx-text-fill: red;");
            }
        }

        // Buscar conflictos en columna
        for (int f = 0; f < TAMANO; f++) {
            if (f != filaProblematica && sudoku.getNumeroEnCelda(f, columnaProblematica) == numeroProblematico) {
                int idx = f * TAMANO + columnaProblematica;
                todosLosCampos.get(idx).setStyle("-fx-text-fill: red;");
            }
        }

        // Buscar conflictos en bloque
        int filaInicio = (filaProblematica / 2) * 2;
        int columnaInicio = (columnaProblematica / 3) * 3;

        for (int f = filaInicio; f < filaInicio + 2; f++) {
            for (int c = columnaInicio; c < columnaInicio + 3; c++) {
                if ((f != filaProblematica || c != columnaProblematica) &&
                        sudoku.getNumeroEnCelda(f, c) == numeroProblematico) {
                    int idx = f * TAMANO + c;
                    todosLosCampos.get(idx).setStyle("-fx-text-fill: red;");
                }
            }
        }
    }

    private void verificarTodoElTablero() {
        if (sudoku.verificarSiGano()) {
            alertBox.mostrarVictoria();
        } else {
            // Buscar y marcar todos los conflictos en el tablero
            buscarTodosLosConflictos();
            alertBox.mostrarError();
        }
    }

    private void buscarTodosLosConflictos() {
        limpiarColores();
        boolean hayConflictos = false;

        // Verificar cada celda
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                int numeroActual = sudoku.getNumeroEnCelda(fila, columna);
                if (numeroActual != 0) {
                    // Verificar si hay conflicto
                    if (tieneConflicto(fila, columna, numeroActual)) {
                        int indice = fila * TAMANO + columna;
                        todosLosCampos.get(indice).setStyle("-fx-text-fill: red;");
                        hayConflictos = true;
                    }
                }
            }
        }
    }

    private boolean tieneConflicto(int fila, int columna, int numero) {
        // Verificar fila
        for (int c = 0; c < TAMANO; c++) {
            if (c != columna && sudoku.getNumeroEnCelda(fila, c) == numero) {
                return true;
            }
        }

        // Verificar columna
        for (int f = 0; f < TAMANO; f++) {
            if (f != fila && sudoku.getNumeroEnCelda(f, columna) == numero) {
                return true;
            }
        }

        // Verificar bloque
        int filaInicio = (fila / 2) * 2;
        int columnaInicio = (columna / 3) * 3;

        for (int f = filaInicio; f < filaInicio + 2; f++) {
            for (int c = columnaInicio; c < columnaInicio + 3; c++) {
                if ((f != fila || c != columna) && sudoku.getNumeroEnCelda(f, c) == numero) {
                    return true;
                }
            }
        }

        return false;
    }

        private void darAyuda () {
            if (sudoku.sePuedeUsarAyuda()) {
                int[] ayuda = sudoku.proporcionarAyuda();
                if (ayuda != null) {
                    actualizarPantalla();
                    int indice = ayuda[0] * TAMANO + ayuda[1];
                    todosLosCampos.get(indice).setStyle("-fx-text-fill: blue;");
                    verificarSiGano();
                }
            } else {
                alertBox.mostrarNoHayAyuda();
            }
        }

        private void verificarSiGano () {
            if (sudoku.verificarSiGano()) {
                alertBox.mostrarVictoria();
            }
        }
    }

