package com.example.sudoku.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa la ventana de inicio del juego Sudoku.
 * Implementa el patrón Singleton para garantizar una única instancia.
 *
 * @author Jonathan Alexander Agurto Jimenez, Moisés David Arrazola Quiroz, Luis Rodrigo Grajeda Olivas
 * @version 1.0
 */
public class InicioView extends Stage {

    /**
     * Constructor que inicializa la ventana de inicio.
     * Carga la interfaz FXML, configura la escena y establece las propiedades de la ventana.
     *
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    public InicioView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/inicio-view.fxml")
        );
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle("Inicio");
        this.getIcons().add(new Image(
                getClass().getResourceAsStream("/com/example/sudoku/images/pngwing.com (1).png")
        ));
        this.setResizable(false);

    }

    /**
     * Metodo estatico que devuelve la unica instancia de la clase Inicio.view.
     * Implementa el patrón Singleton usando una clase holder interna.
     *
     * @return la instancia única de InicioView
     * @throws IOException si ocurre un error al crear la instancia
     */
    public static InicioView getInstance() throws IOException {
        if (InicioView.InicioViewHolder.INSTANCE == null) {
            InicioView.InicioViewHolder.INSTANCE = new InicioView();
        }
        return InicioView.InicioViewHolder.INSTANCE;
    }

    /**
     *
     * Clase holder interna para la implementación del patrón Singleton.
     * Garantiza una inicialización perezosa y thread-safe.
     */
    private static class InicioViewHolder {
        private static InicioView INSTANCE = null;
    }

}