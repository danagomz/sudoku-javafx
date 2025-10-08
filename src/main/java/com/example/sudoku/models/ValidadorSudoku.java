package com.example.sudoku.models;

/**
 * Clase que valida un Sudoku de 6x6 (bloques de 2 filas x 3 columnas)
 * usando una lógica sencilla y muy explícita.
 * @author Dana Sofia Gómez, Miguel Angel Lasso
 * @version 1.0
 */
public class ValidadorSudoku {

    // Constantes del tamaño del tablero y los bloques
    private static final int TAMANO_TABLERO = 6;
    private static final int FILAS_POR_BLOQUE = 2;
    private static final int COLUMNAS_POR_BLOQUE = 3;

    /**
     * Revisa si el número ya existe en la fila indicada.
     */
    public boolean validarNumeroEnFila(int[][] tablero, int numeroFila, int numeroARevisar) {
        for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
            int valorActual = tablero[numeroFila][columna];
            if (valorActual == numeroARevisar)
            {
                return false; // el número ya está en la fila
            }
        }
        return true; // el número no se repite
    }

    /**
     * Revisa si el número ya existe en la columna indicada.
     */
    public boolean validarNumeroEnColumna(int[][] tablero, int numeroColumna, int numeroARevisar) {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            int valorActual = tablero[fila][numeroColumna];
            if (valorActual == numeroARevisar) {
                return false; // el número ya está en la columna
            }
        }
        return true; // el número no se repite
    }

    /**
     * Revisa si el número ya existe en el bloque 2x3 correspondiente.
     * Aquí se calcula manualmente el inicio del bloque.
     */
    public boolean validarNumeroEnBloque(int[][] tablero, int fila, int columna, int numeroARevisar) {
        int filaInicioBloque;
        int columnaInicioBloque;

        // Buscar manualmente el bloque según la fila
        if (fila < 2) { // filas 0 y 1
            filaInicioBloque = 0;
        } else if (fila < 4) { // filas 2 y 3
            filaInicioBloque = 2;
        } else { // filas 4 y 5
            filaInicioBloque = 4;
        }

        // Buscar manualmente el bloque según la columna
        if (columna < 3) { // columnas 0,1,2
            columnaInicioBloque = 0;
        } else { // columnas 3,4,5
            columnaInicioBloque = 3;
        }

        // Revisar las 6 celdas del bloque (2 filas x 3 columnas)
        for (int filaActual  = filaInicioBloque; filaActual  < filaInicioBloque + FILAS_POR_BLOQUE; filaActual ++) {
            for (int columnaActual = columnaInicioBloque; columnaActual < columnaInicioBloque + COLUMNAS_POR_BLOQUE; columnaActual++) {
                if (tablero[filaActual ][columnaActual] == numeroARevisar) {
                    return false; // el número ya está en el bloque
                }
            }
        }
        return true; // el número no se repite en el bloque
    }

    /**
     * Revisa si una fila completa no tiene números repetidos.
     */
    public boolean filaCompletaEsValida(int[][] tablero, int fila) {
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            int numeroARevisar = tablero[fila][i];
            if (numeroARevisar != 0) {
                // Verificar si el número se repite más adelante en la misma fila
                for (int j = i + 1; j < TAMANO_TABLERO; j++) {
                    if (tablero[fila][j] == numeroARevisar) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Revisa si una columna completa no tiene números repetidos.
     */
    public boolean columnaCompletaEsValida(int[][] tablero, int columna) {
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            int numeroARevisar = tablero[i][columna];
            if (numeroARevisar != 0) {
                for (int j = i + 1; j < TAMANO_TABLERO; j++) {
                    if (tablero[j][columna] == numeroARevisar) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Revisa si un bloque completo no tiene números repetidos.
     */
    public boolean bloqueCompletoEsValido(int[][] tablero, int filaInicioBloque, int columnaInicioBloque) {
        // Recorre todas las filas del bloque 2x3
        for (int filaActual = filaInicioBloque; filaActual < filaInicioBloque + FILAS_POR_BLOQUE; filaActual++) {
            // Recorre todas las columnas del bloque 2x3
            for (int columnaActual = columnaInicioBloque; columnaActual < columnaInicioBloque + COLUMNAS_POR_BLOQUE; columnaActual++) {

                int numeroActual = tablero[filaActual][columnaActual];

                // Si la celda no está vacía (es diferente de 0)
                if (numeroActual != 0) {

                    // Comparar este número con el resto de celdas del mismo bloque
                    for (int filaComparacion = filaActual; filaComparacion < filaInicioBloque + FILAS_POR_BLOQUE; filaComparacion++) {
                        for (int columnaComparacion = columnaActual + 1; columnaComparacion < columnaInicioBloque + COLUMNAS_POR_BLOQUE; columnaComparacion++) {

                            // Si hay un número igual en otra celda del mismo bloque
                            if (tablero[filaComparacion][columnaComparacion] == numeroActual) {
                                return false; // El bloque no es válido (número repetido)
                            }
                        }
                    }
                }
            }
        }
        // Si no se encontró ningún número repetido, el bloque es válido
        return true;
    }


    /**
     * Verifica si el tablero está completamente lleno
     */
    public boolean tableroLleno(int[][] tablero) {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
                if (tablero[fila][columna] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifica si se puede colocar un número en una celda específica.
     * Es decir, revisa fila, columna y bloque.
     */
    public boolean sePuedePonerNumero(int[][] tablero, int fila, int columna, int numero) {
        boolean filaValida = validarNumeroEnFila(tablero, fila, numero);
        boolean columnaValida = validarNumeroEnColumna(tablero, columna, numero);
        boolean bloqueValido = validarNumeroEnBloque(tablero, fila, columna, numero);

        if (filaValida && columnaValida && bloqueValido)
        {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Verifica si el tablero completo es correcto.
     */
    public boolean tableroEsCorrecto(int[][] tablero) {
        // Revisar todas las filas
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            if (!filaCompletaEsValida(tablero, fila)) {
                return false;
            }
        }

        // Revisar todas las columnas
        for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
            if (!columnaCompletaEsValida(tablero, columna)) {
                return false;
            }
        }

        // Revisar todos los bloques
        for (int filaBloque = 0; filaBloque < TAMANO_TABLERO; filaBloque += FILAS_POR_BLOQUE) {
            for (int columnaBloque = 0; columnaBloque < TAMANO_TABLERO; columnaBloque += COLUMNAS_POR_BLOQUE) {
                if (!bloqueCompletoEsValido(tablero, filaBloque, columnaBloque)) {
                    return false;
                }
            }
        }
        return true; // todas las partes son válidas
    }

}