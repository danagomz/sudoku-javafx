package com.example.sudoku.models;

/**Esta clase no valida nada por sí misma; solo guarda y gestiona el estado del tablero
 * @author Dana Sofia Gomez y Miguel Angel Lasso
 * @version 1.0
 * */

public class TableroSudoku {
    /** Tamaño del tablero */
    private static final int TAMANO = 6;

    /** Matriz que guarda los números actuales del tablero */
    private int[][] tablero;
    /**
     * Constructor flexible.
     * Si recibe una matriz inicial, la clona.
     * Si recibe null, crea un tablero vacío (todo en ceros).
     * @param tableroInicial matriz 6x6 con los números del tablero o null para crear uno vacío.
     */
    public TableroSudoku(int[][] tableroInicial) {
        tablero = new int[TAMANO][TAMANO];

        if (tableroInicial != null) {
            for (int fila = 0; fila < TAMANO; fila++) {
                for (int columna = 0; columna < TAMANO; columna++) {
                    tablero[fila][columna] = tableroInicial[fila][columna];
                }
            }
        }
    }

    /**
     * Devuelve el número guardado en una celda específica.
     * @param fila posición de la fila (0 a 5)
     * @param columna posición de la columna (0 a 5)
     * @return número en esa celda (0 si está vacía)
     */
    public int getNumero(int fila, int columna) {
        return tablero[fila][columna];
    }

    /**
     * Cambia el número en una celda específica del tablero.
     * @param fila posición de la fila
     * @param columna posición de la columna
     * @param numero nuevo número a colocar (1–6 o 0 si se quiere borrar)
     */
    public void setNumero(int fila, int columna, int numero) {
        tablero[fila][columna] = numero;
    }

    /**
     * Indica si una celda está vacía (es decir, vale 0).
     * @param fila posición de la fila
     * @param columna posición de la columna
     * @return true si está vacía, false si tiene un número
     */
    public boolean estaVacio(int fila, int columna) {
        return tablero[fila][columna] == 0;
    }

    /**
     * Crea una copia exacta del tablero actual (útil para validaciones e implementar la ayuda del juego).
     * @return una nueva instancia de TableroSudoku con los mismos valores
     */
    public TableroSudoku clonarTablero() {

        TableroSudoku copia = new TableroSudoku(tablero); // crea un tablero vacío
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                copia.setNumero(fila, columna, this.tablero[fila][columna]); // copia celda por celda
            }
        }
        return copia;
        }

    /**
     * Devuelve la matriz interna del tablero.
     * @return la matriz 6x6 actual
     */
    public int[][] getMatriz() {
        return tablero;
    }
}
