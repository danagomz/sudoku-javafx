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
    /** Tamaño total del tablero (6x6) */
    private final int TAMANO = 6;

    /** Matriz principal que guarda los números del Sudoku */
    private int[][] tablero;

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
                // Paso 4: Prueba cada número mezclado
                for (int numero : listaNumeros)
                {
                    if (validador.sePuedePonerNumero(tablero, fila, columna, numero))
                    { // Verifica si ese número se puede poner en la celda
                        tablero[fila][columna] = numero; // coloca el número
                        break; // sale del ciclo interno porque ya colocó un número válido
                    }
                }
            }
        }
    }

    /**
     * Devuelve el tablero Sudoku generado
     * @return una matriz de 6x6 con los números del Sudoku válido
     */
    public int[][] getTablero() {
        return tablero;
    }

}
