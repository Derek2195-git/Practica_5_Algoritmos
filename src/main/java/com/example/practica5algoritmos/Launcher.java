package com.example.practica5algoritmos;

import com.example.practica5algoritmos.Controlador.Controlador;
import com.example.practica5algoritmos.Vista.VentanaLibreria;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) {
        Controlador controlador = new Controlador(stage);
    }
}
