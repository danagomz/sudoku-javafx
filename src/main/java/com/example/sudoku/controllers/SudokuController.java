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

/**
 * Controlador principal del tablero de Sudoku 6x6.
 * Maneja la lógica de la interfaz, eventos de botones y teclado,
 * verificación de números, conflictos y ayudas.
 * @author Dana Sofia Gomez, Miguel Angel Lasso
 * @version 1.0
 */
public class SudokuController {
    /** Tamaño del tablero (6x6). */
    private static final int TAMANO = 6;

    /** Modelo del juego Sudoku. */
    private Sudoku sudoku;

    /** Interfaz para mostrar alertas y mensajes al usuario. */
    private IAlertBox alertBox;

    /** Lista con todos los TextFields del tablero en orden. */
    private ArrayList<TextField> todosLosCampos;

    /** Botón para pedir ayuda. */
    @FXML
    private Button btnAyuda;

    /** Botón para iniciar un nuevo juego. */
    @FXML
    private Button btnNuevoJuego;

    /** Botón para verificar los números ingresados. */
    @FXML
    private Button btnVerificar;

    /** Contenedor principal del tablero Sudoku. */
    @FXML
    private GridPane gridpaneSudoku;

    /** TextFields que representan cada celda del tablero. */
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

    /**
     * Inicializa el controlador después de que se cargue el FXML.
     * Se encarga de iniciar todo el juego.
     */
    @FXML
    private void initialize() {
        iniciarTodo();
    }

    /**
     * Inicializa todos los componentes del juego:
     * crea el objeto Sudoku, la lista de TextFields,
     * configura eventos, y actualiza la pantalla.
     */
    private void iniciarTodo() {
        sudoku = new Sudoku();
        alertBox = new AlertBox();
        sudoku.iniciarJuego();
        todosLosCampos = new ArrayList<>();

        guardarTodosLosCampos();
        verificarNumeros();
        configurarEventoEnter();
        actualizarPantalla();
    }

    /**
     * Acción del botón "Ayuda".
     * Llama al método para dar una pista al usuario.
     *
     * @param event Evento generado al presionar el botón.
     */
    @FXML
    void onActionAyuda(ActionEvent event) {
        darAyuda();
    }

    /**
     * Acción del botón "Nuevo Juego".
     * Pregunta confirmación al usuario y reinicia el tablero si confirma.
     *
     * @param event Evento generado al presionar el botón.
     */
    @FXML
    void onActionNuevoJuego(ActionEvent event) {
        if (alertBox.mostrarConfirmacionNuevoJuego()) {
            iniciarTodo();
        }
    }

    /**
     * Acción del botón "Verificar".
     * Recorre todas las celdas y procesa los números
     * igual que si se presionara Enter en cada TextField.
     *
     * @param event Evento generado al presionar el botón.
     */
    @FXML
    void onActionVerificar(ActionEvent event) {    // Procesar todos los campos que tengan texto (como ENTER lo haría uno por uno)
        verificarNumeros();
    }

    /**
     * Guarda todos los TextFields en la lista todosLosCampos
     * para poder manejarlos más fácilmente.
     */
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

    /**
     * Configura el evento de presionar la tecla Enter
     * en cada TextField para procesar el número automáticamente.
     */
    private void configurarEventoEnter() {

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

    /**
     * Procesa un número ingresado en una celda del tablero.
     * Verifica que sea válido, coloca el número si es correcto,
     * marca conflictos si es incorrecto, y actualiza la pantalla.
     *
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     * @param texto Texto ingresado en la celda.
     */
    private void procesarNumero(int fila, int columna, String texto) {
        if (texto.isEmpty()) {
            return;
        }

        int numero = 0;
        boolean esNumeroValido = false;

        // Verificar si es número válido (1-6) y convertimos la info del textfield a int
        for (int i = 1; i <= TAMANO; i++) {
            if (texto.equals(String.valueOf(i))) {//se convierte el número de la lista a String para poder compararlos
                numero = i;
                esNumeroValido = true; // Solo se vuelve true si encuentra coincidencia(num del 1 al 6)
                break;
            }
        }

        if (!esNumeroValido) {
            alertBox.mostrarError();
            return;
        }

// Verificar si la celda está vacía
        if (!sudoku.celdaEstaVacia(fila, columna)) {
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
    }


    /**
     * Actualiza todos los TextFields del tablero según el estado del Sudoku.
     * Muestra números, bloquea celdas iniciales y aplica colores.
     */
    private void actualizarPantalla() {
        for (int i = 0; i < todosLosCampos.size(); i++) {
            int fila = i / TAMANO;
            int columna = i % TAMANO;
            TextField campo = todosLosCampos.get(i);
            int numero = sudoku.getNumeroEnCelda(fila, columna);

            if (numero != 0) {
                // Muestra el número en la celda
                campo.setText(String.valueOf(numero));

                // Verifica si la celda es una del tablero original (no vacía)
                boolean esInicial = !sudoku.celdaEstaVacia(fila, columna);

                // Si es una celda inicial, no se puede editar y tiene fondo gris
                if (esInicial) {
                    campo.setEditable(false);
                    campo.setStyle("-fx-text-fill: black; -fx-background-color: #e0e0e0;");
                }
                // Si no es inicial, se puede editar y tiene fondo blanco
                else {
                    campo.setEditable(true);
                    campo.setStyle("-fx-text-fill: black; -fx-background-color: white;");
                }

            } else {
                // Si el número es 0, la celda está vacía
                campo.setText("");
                campo.setEditable(true);
                campo.setStyle("-fx-text-fill: black; -fx-background-color: white;");
            }
        }
        btnAyuda.setDisable(!sudoku.sePuedeUsarAyuda());
    }


    /**
     * Limpia los colores de todas las celdas del tablero,
     * dejando las celdas vacías en blanco y las iniciales en gris.
     */
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

    /**
     * Marca en rojo las celdas que tienen conflictos con el número ingresado.
     *
     * @param filaProblematica Fila de la celda conflictiva.
     * @param columnaProblematica Columna de la celda conflictiva.
     * @param numeroProblematico Número que genera el conflicto.
     */
    private void marcarConflictos(int filaProblematica, int columnaProblematica, int numeroProblematico) {
        limpiarColores();

        // Marcar celda problemática
        int posicionEnLista = filaProblematica * TAMANO + columnaProblematica;
        todosLosCampos.get(posicionEnLista).setStyle("-fx-text-fill: red;");

        // Buscar conflictos en fila
        for (int columna = 0; columna < TAMANO; columna++) {
            if (columna != columnaProblematica && sudoku.getNumeroEnCelda(filaProblematica, columna) == numeroProblematico) {
                int resaltarCeldasEnConflicto = filaProblematica * TAMANO + columna;
                todosLosCampos.get(resaltarCeldasEnConflicto).setStyle("-fx-text-fill: red;");
            }
        }

        // Buscar conflictos en columna
        for (int fila = 0; fila < TAMANO; fila++) {
            if (fila != filaProblematica && sudoku.getNumeroEnCelda(fila, columnaProblematica) == numeroProblematico) {
                int resaltarCeldasEnConflicto = fila * TAMANO + columnaProblematica;
                todosLosCampos.get(resaltarCeldasEnConflicto).setStyle("-fx-text-fill: red;");
            }
        }

        // Buscar conflictos en bloque
        int filaInicio = (filaProblematica / 2) * 2;
        int columnaInicio = (columnaProblematica / 3) * 3;

        for (int fila = filaInicio; fila < filaInicio + 2; fila++) {
            for (int columna = columnaInicio; columna < columnaInicio + 3; columna++) {
                if ((fila != filaProblematica || columna != columnaProblematica) && sudoku.getNumeroEnCelda(fila, columna) == numeroProblematico) {
                    int resaltarCeldasEnConflicto = fila * TAMANO + columna;
                    todosLosCampos.get(resaltarCeldasEnConflicto).setStyle("-fx-text-fill: red;");
                }
            }
        }
    }

    private void verificarNumeros() {
        for (int i = 0; i < todosLosCampos.size(); i++) {
            TextField campo = todosLosCampos.get(i);
            int fila = i / TAMANO;
            int columna = i % TAMANO;
            String texto = campo.getText();

            boolean tieneTexto = !texto.isEmpty();
            boolean esEditable = campo.isEditable();

            if (tieneTexto && esEditable) {
                procesarNumero(fila, columna, texto);
            }
        }
    }

    /**
     * Recorre todas las celdas del tablero y procesa los números
     * que el usuario haya ingresado en celdas editables.
     * Funciona de manera similar a presionar Enter en cada TextField.
     */
    private void darAyuda () {
        if (sudoku.sePuedeUsarAyuda()) {
            int[] ayuda = sudoku.proporcionarAyuda();

            if (ayuda != null) {
                actualizarPantalla();
                int posicionEnLista = ayuda[0] * TAMANO + ayuda[1];
                todosLosCampos.get(posicionEnLista).setStyle("-fx-text-fill: blue;");
                verificarSiGano();
            }
        }
    }

    /**
     * Verifica si el jugador ha completado correctamente el tablero.
     * Si el jugador gana, muestra un mensaje de victoria y reinicia el juego.
     */
        private void verificarSiGano () {
            if (sudoku.verificarSiGano()) {
                alertBox.mostrarVictoria();
                iniciarTodo();
            }
        }
    }