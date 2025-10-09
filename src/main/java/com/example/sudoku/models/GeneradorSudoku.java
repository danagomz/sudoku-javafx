package com.example.sudoku.models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que genera un tablero válido de Sudoku 6x6.
 * Esta clase crea un tablero con números del 1 al 6, asegurando que
 * cada fila, columna y bloque 2x3 cumplan las reglas del Sudoku (no puede haber números repetidos)
 * Usa la clase ValidadorSudoku para verificar cada número colocado
 * y un ArrayList para manejar los números posibles de forma sencilla
 *
 * @author Dana Sofia Gomez y Miguel Angel Lasso
 * @version 1.0
 */

public class GeneradorSudoku {
    /** ctes del tamaño total del tablero (6x6) */
    private final int TAMANO = 6;
    private static final int FILAS_POR_BLOQUE = 2;
    private static final int COLUMNAS_POR_BLOQUE = 3;

    /** Matriz principal que guarda los números del Sudoku */
    private int[][] tablero;
    /** Matriz del jugador que guarda los números del tablero del jugador*/
    private int[][] tableroJugador;
    /** Objeto que valida si las reglas del Sudoku se cumplen */
    private ValidadorSudoku validador;
    /** Lista de números posibles del 1 al 6, que se mezcla en cada fila */
    private ArrayList<Integer> listaNumeros;

    /**
     * Constructor de la clase.
     * Crea un tablero vacío, inicializa el validador y genera un Sudoku válido
     */
    public GeneradorSudoku() {
        tablero = new int[TAMANO][TAMANO]; // crea una matriz 6x6 vacía
        tableroJugador = new int[TAMANO][TAMANO]; // crear tablero del jugador vacío
        validador = new ValidadorSudoku(); // crea el objeto que validará las reglas
        listaNumeros = new ArrayList<>();// crea la lista de números disponibles
        generarTablero();// genera automáticamente el tablero
    }

    /**
     * Genera un tablero Sudoku 6x6 válido
     * Mezcla los números del 1 al 6 en cada fila e intenta colocarlos en el tablero
     * usando el validador para asegurar que cada posición cumpla las reglas del juego
     */
    private void generarTablero() {

        // Paso 1: Llenar la lista con los números del 1 al 6
        for (int i = 1; i <= TAMANO; i++)
        {
            listaNumeros.add(i);
        }

        // Paso 2:Recorre todas las filas
        for (int fila = 0; fila < TAMANO; fila++)
        {
            Collections.shuffle(listaNumeros);// Mezcla los números para que cada fila sea diferente
            // Paso 3:Recorre todas las columnas
            for (int columna = 0; columna < TAMANO; columna++)
            {

                // Paso 4: Prueba cada número de la lista mezclada
                for (int i = 0; i < listaNumeros.size(); i++) {
                    int numeroActual = listaNumeros.get(i); // obtener el número actual de la lista

                    if (validador.sePuedePonerNumero(tablero, fila, columna, numeroActual)) {// Verifica si se puede colocar este número en la celda
                        tablero[fila][columna] = numeroActual; // colocar el número en el tablero
                        break;
                    }
                }
            }
        }
    }

    /**
     * Genera un tablero para el jugador mostrando solo 2 números por bloque 2x3
     */
    public int[][] generarTableroJugador() {

        for (int i = 0; i < TAMANO; i++) {// Limpia el tablero del jugador
            for (int j = 0; j < TAMANO; j++) {
                tableroJugador[i][j] = 0;
            }
        }

        // Recorrer bloques 2x3
        for (int filaInicioBloque = 0; filaInicioBloque < TAMANO; filaInicioBloque += FILAS_POR_BLOQUE) {
            for (int columnaInicioBloque = 0; columnaInicioBloque < TAMANO; columnaInicioBloque += COLUMNAS_POR_BLOQUE) {

                // Crear lista de todas las posiciones del bloque
                ArrayList<int[]> posicionesBloque = new ArrayList<>();

                for (int fila = filaInicioBloque; fila < filaInicioBloque + FILAS_POR_BLOQUE; fila++) {
                    for (int columna = columnaInicioBloque; columna < columnaInicioBloque + COLUMNAS_POR_BLOQUE; columna++) {
                        int[] coordenada = new int[2];
                        coordenada[0] = fila;
                        coordenada[1] = columna;
                        posicionesBloque.add(coordenada);
                    }
                }

                // Mezclar posiciones para que sean aleatorias
                Collections.shuffle(posicionesBloque);

                // Tomar solo las primeras 2 posiciones
                for (int i = 0; i < 2; i++) {
                    int[] posicion = posicionesBloque.get(i);
                    int fila = posicion[0];
                    int columna = posicion[1];
                    tableroJugador[fila][columna] = tablero[fila][columna]; // copiar número al tablero del jugador
                }
            }
        }

        return tableroJugador;
    }

    /**
     * Devuelve el tablero Sudoku generado
     * @return una matriz de 6x6 con los números del Sudoku válido
     */
    public int[][] getTablero() {
        return tablero;
    }

    /**
     * Devuelve el tablero del Jugador
     * @return una matriz de 6x6 con los números del Sudoku válido
     */
    public int[][] getTableroJugador() {
        return tableroJugador;
    }

}
