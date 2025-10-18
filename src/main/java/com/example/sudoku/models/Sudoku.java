package com.example.sudoku.models;

public class Sudoku {
    private static final int TAMANO = 6;
    private ValidadorSudoku validador;
    private TableroSudoku tableroJugador;
    private GeneradorSudoku generador;
    private int celdasVaciasRestantes;
    private int[][] tableroSolucion;
    /**
     * Constructor de la clase Sudoku.
     * Inicializa los componentes principales del juego.
     */
    public Sudoku() {
        this.generador = new GeneradorSudoku();
        this.validador = new ValidadorSudoku();
        tableroSolucion = generador.getTablero();

        int[][] pistasIniciales = generador.generarTableroJugador();
        tableroJugador = new TableroSudoku(pistasIniciales);//primeros num del sudoku
        this.celdasVaciasRestantes = contarCeldasVacias();
    }

    /**
     * Inicia un nuevo juego de Sudoku generando un tablero para el jugador.
     * El tablero generado tendrá celdas vacías listas para que el jugador complete.
     */
    public void iniciarJuego() {
        // Generar un nuevo tablero para el jugador
        int[][] tableroJugador = generador.generarTableroJugador();
        this.tableroJugador = new TableroSudoku(tableroJugador);
        this.celdasVaciasRestantes = contarCeldasVacias();
    }

    /**
     * Cuenta las celdas vacías en el tablero actual.
     * @return número de celdas vacías
     */
    private int contarCeldasVacias() {
        int vacias = 0;
        int[][] juegoActual = tableroJugador.getTablero();
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                if (juegoActual[fila][columna] == 0) {
                    vacias++;
                }
            }
        }
        return vacias;
    }

    /**
     * Intenta colocar un número en una celda específica del tablero.
     * Verifica si el movimiento es válido antes de actualizar el tablero.
     * @param fila La fila donde se quiere colocar el número (0-5)
     * @param columna La columna donde se quiere colocar el número (0-5)
     * @param numero El número que se quiere colocar (1-6)
     * @return true si el número se pudo colocar correctamente, false si el movimiento es inválido
     */
    public boolean intentarPonerNumero(int fila, int columna, int numero) {

        if (!tableroJugador.estaVacio(fila, columna)) {// Verificar que la celda esté vacía
            return false;
        }

        if (numero < 1 || numero > 6) {  // Verificar que el número sea válido (1-6)
            return false;
        }

        if (tableroSolucion[fila][columna] == numero) {
            tableroJugador.setNumero(fila, columna, numero); // Número correcto
            celdasVaciasRestantes--;
            return true;
        }

        return false;
    }

    /**
     * Verifica si el jugador ha ganado el juego.
     * Un jugador gana si el tablero está completo y es correcto.
     * @return true si el jugador ganó, false en caso contrario
     */
    public boolean verificarSiGano() {
        int[][] juegoActual = tableroJugador.getTablero();

        // Verificar si el tablero está lleno y es correcto
        boolean tableroLleno = validador.tableroLleno(juegoActual);
        boolean tableroCorrecto = validador.tableroEsCorrecto(juegoActual);

        return tableroLleno && tableroCorrecto;
    }

    /**
     * Proporciona ayuda al jugador revelando un número correcto en una celda vacía.
     * Solo se permite usar ayuda si NO es el último turno (más de 1 celda vacía).
     * Marca que se usó ayuda para afectar la condición de victoria.
     *
     * @return un array con [fila, columna, numero] donde se puso la ayuda, o null si no se puede usar ayuda
     */
    public int[] proporcionarAyuda() {

        if (celdasVaciasRestantes <= 1) {// Verificar si se puede usar ayuda (no puede ser el último turno)
            return null; // No se permite ayuda en el último turno
        }

        // Buscar una celda vacía en el tablero del jugador
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                if (tableroJugador.estaVacio(fila, columna)) {
                    int numeroCorrecto = tableroSolucion[fila][columna]; // Obtener el número correcto del tablero completo

                    tableroJugador.setNumero(fila, columna, numeroCorrecto);// Poner el número en el tablero del jugador
                    celdasVaciasRestantes--;

                    int[] ayuda = new int[3]; //guardamos fila, columna y la pista en un array ya que son datos fijos
                    ayuda[0] = fila;
                    ayuda[1] = columna;
                    ayuda[2] = numeroCorrecto;

                    return ayuda;
                }
            }
        }
        return null; // No hay celdas vacías
    }

    /**
     * Verifica si se puede usar ayuda en este momento.
     * La ayuda no se puede usar en el último turno.
     * @return true si se puede usar ayuda, false en caso contrario
     */
    public boolean sePuedeUsarAyuda() {

        return celdasVaciasRestantes > 1;
    }

    /**
     * Obtiene el número en una celda específica del tablero.
     * @param fila La fila de la celda (0-5)
     * @param columna La columna de la celda (0-5)
     * @return El número en la celda, o 0 si está vacía
     */
    public int getNumeroEnCelda(int fila, int columna) {

        return tableroJugador.getNumero(fila, columna);
    }

    /**
     * Verifica si una celda específica está vacía.
     * @param fila La fila de la celda (0-5)
     * @param columna La columna de la celda (0-5)
     * @return true si la celda está vacía, false si tiene un número
     */
    public boolean celdaEstaVacia(int fila, int columna) {

        return tableroJugador.estaVacio(fila, columna);
    }

}
