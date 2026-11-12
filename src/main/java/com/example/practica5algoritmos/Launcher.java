package com.example.practica5algoritmos;

import com.example.practica5algoritmos.Controlador.Controlador;
import com.example.practica5algoritmos.Vista.VentanaLibreria;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Launcher extends Application {
    private MediaPlayer musicaFondo = null;

    @Override
    public void start(Stage stage) {
        Controlador controlador = new Controlador(stage);
        iniciarMusica();
    }

    private void iniciarMusica() {
        if (musicaFondo != null) return;
        String ruta = "/hip_shop.mp3";
        try {
            Media media = new Media(
                    getClass().getResource(ruta).toURI().toString()
            );
            musicaFondo = new MediaPlayer(media);
            musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
            musicaFondo.setVolume(0.1);
            musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
            musicaFondo.play();
        } catch (Exception e) {
            System.out.println("No se encontró el archivo de música con la ruta " + ruta);
        }
    }
}
