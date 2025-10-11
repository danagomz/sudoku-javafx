package com.example.sudoku.models;

/**
 * Esta clase se encarga de validar un Sudoku de 6x6 (bloques de 2 filas x 3 columnas).
 * Verifica si los números cumplen con las reglas del Sudoku: no se repiten en filas, columnas ni bloques.
 *
 * @author Dana Sofia Gómez, Miguel Angel Lasso
 * @version 1.0
 */
public class ValidadorSudoku {

    // Constantes para definir el tamaño del tablero y los bloques
    private static final int TAMANO_TABLERO = 6;
    private static final int FILAS_POR_BLOQUE = 2;
    private static final int COLUMNAS_POR_BLOQUE = 3;

    /**
     * Revisa si un número ya está presente en una fila específica.
     * @param tablero El tablero de Sudoku actual.
     * @param numeroFila La fila que se quiere revisar.
     * @param numeroARevisar El número que se quiere comprobar.
     * @return true si el número no está repetido en la fila, false si ya existe.
     */
    public boolean validarNumeroEnFila(int[][] tablero, int numeroFila, int numeroARevisar) {
        for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
            int valorActual = tablero[numeroFila][columna];
            if (valorActual == numeroARevisar) {
                return false; // el número ya está en la fila
            }
        }
        return true; // el número no se repite
    }

    /**
     * Revisa si un número ya está presente en una columna específica.
     * @param tablero El tablero de Sudoku actual.
     * @param numeroColumna La columna que se quiere revisar.
     * @param numeroARevisar El número que se quiere comprobar.
     * @return true si el número no está repetido en la columna, false si ya existe.
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
     * Revisa si un número ya está presente en el bloque 2x3 correspondiente a la posición dada.
     * @param tablero El tablero de Sudoku actual.
     * @param fila La fila de la celda que se quiere revisar.
     * @param columna La columna de la celda que se quiere revisar.
     * @param numeroARevisar El número que se quiere comprobar.
     * @return true si el número no está repetido en el bloque, false si ya existe.
     */
    public boolean validarNumeroEnBloque(int[][] tablero, int fila, int columna, int numeroARevisar) {
        int filaInicioBloque;
        int columnaInicioBloque;

        // Determinar la fila inicial del bloque
        if (fila < 2) {
            filaInicioBloque = 0;
        } else if (fila < 4) {
            filaInicioBloque = 2;
        } else {
            filaInicioBloque = 4;
        }

        // Determinar la columna inicial del bloque
        if (columna < 3) {
            columnaInicioBloque = 0;
        } else {
            columnaInicioBloque = 3;
        }

        // Revisar todas las celdas del bloque
        for (int filaActual = filaInicioBloque; filaActual < filaInicioBloque + FILAS_POR_BLOQUE; filaActual++) {
            for (int columnaActual = columnaInicioBloque; columnaActual < columnaInicioBloque + COLUMNAS_POR_BLOQUE; columnaActual++) {
                if (tablero[filaActual][columnaActual] == numeroARevisar) {
                    return false; // el número ya está en el bloque
                }
            }
        }
        return true; // el número no se repite en el bloque
    }

    /**
     * Revisa si una fila completa es válida, es decir, que no tenga números repetidos.
     * @param tablero El tablero de Sudoku actual.
     * @param fila La fila que se quiere revisar.
     * @return true si la fila es válida, false si hay números repetidos.
     */
    public boolean filaCompletaEsValida(int[][] tablero, int fila) {
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            int numeroARevisar = tablero[fila][i];
            if (numeroARevisar != 0) {
                for (int j = i + 1; j < TAMANO_TABLERO; j++) {
                    if (tablero[fila][j] == numeroARevisar) {
                        return false; // número repetido encontrado
                    }
                }
            }
        }
        return true; // no se encontraron repeticiones
    }

    /**
     * Revisa si una columna completa es válida, es decir, que no tenga números repetidos.
     * @param tablero El tablero de Sudoku actual.
     * @param columna La columna que se quiere revisar.
     * @return true si la columna es válida, false si hay números repetidos.
     */
    public boolean columnaCompletaEsValida(int[][] tablero, int columna) {
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            int numeroARevisar = tablero[i][columna];
            if (numeroARevisar != 0) {
                for (int j = i + 1; j < TAMANO_TABLERO; j++) {
                    if (tablero[j][columna] == numeroARevisar) {
                        return false; // número repetido encontrado
                    }
                }
            }
        }
        return true; // no se encontraron repeticiones
    }

    /**
     * Revisa si un bloque completo 2x3 es válido, es decir, que no tenga números repetidos.
     * @param tablero El tablero de Sudoku actual.
     * @param filaInicioBloque La fila inicial del bloque.
     * @param columnaInicioBloque La columna inicial del bloque.
     * @return true si el bloque es válido, false si hay números repetidos.
     */
    public boolean bloqueCompletoEsValido(int[][] tablero, int filaInicioBloque, int columnaInicioBloque) {
        for (int filaActual = filaInicioBloque; filaActual < filaInicioBloque + FILAS_POR_BLOQUE; filaActual++) {
            for (int columnaActual = columnaInicioBloque; columnaActual < columnaInicioBloque + COLUMNAS_POR_BLOQUE; columnaActual++) {
                int numeroActual = tablero[filaActual][columnaActual];
                if (numeroActual != 0) {
                    for (int filaComparacion = filaActual; filaComparacion < filaInicioBloque + FILAS_POR_BLOQUE; filaComparacion++) {
                        for (int columnaComparacion = columnaActual + 1; columnaComparacion < columnaInicioBloque + COLUMNAS_POR_BLOQUE; columnaComparacion++) {
                            if (tablero[filaComparacion][columnaComparacion] == numeroActual) {
                                return false; // número repetido dentro del bloque
                            }
                        }
                    }
                }
            }
        }
        return true; // bloque sin repeticiones
    }

    /**
     * Revisa si todo el tablero está completamente lleno (sin celdas vacías).
     * @param tablero El tablero de Sudoku actual.
     * @return true si el tablero está lleno, false si hay al menos una celda vacía.
     */
    public boolean tableroLleno(int[][] tablero) {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
                if (tablero[fila][columna] == 0) {
                    return false; // hay una celda vacía
                }
            }
        }
        return true; // todas las celdas tienen números
    }

    /**
     * Verifica si se puede poner un número en una celda específica.
     * @param tablero El tablero de Sudoku actual.
     * @param fila La fila de la celda.
     * @param columna La columna de la celda.
     * @param numero El número que se quiere colocar.
     * @return true si se puede colocar sin romper las reglas, false si ya existe en fila, columna o bloque.
     */
    public boolean sePuedePonerNumero(int[][] tablero, int fila, int columna, int numero) {
        boolean filaValida = validarNumeroEnFila(tablero, fila, numero);
        boolean columnaValida = validarNumeroEnColumna(tablero, columna, numero);
        boolean bloqueValido = validarNumeroEnBloque(tablero, fila, columna, numero);

        return filaValida && columnaValida && bloqueValido;
    }

    /**
     * Verifica si el tablero completo es correcto según las reglas del Sudoku.
     * @param tablero El tablero de Sudoku actual.
     * @return true si todas las filas, columnas y bloques son válidos, false si alguna parte falla.
     */
    public boolean tableroEsCorrecto(int[][] tablero) {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            if (!filaCompletaEsValida(tablero, fila)) {
                return false; // fila inválida
            }
        }

        for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
            if (!columnaCompletaEsValida(tablero, columna)) {
                return false; // columna inválida
            }
        }

        for (int filaBloque = 0; filaBloque < TAMANO_TABLERO; filaBloque += FILAS_POR_BLOQUE) {
            for (int columnaBloque = 0; columnaBloque < TAMANO_TABLERO; columnaBloque += COLUMNAS_POR_BLOQUE) {
                if (!bloqueCompletoEsValido(tablero, filaBloque, columnaBloque)) {
                    return false; // bloque inválido
                }
            }
        }
        return true; // todo el tablero es correcto
    }

}
