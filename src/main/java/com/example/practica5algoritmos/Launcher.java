package com.example.practica5algoritmos;

import com.example.practica5algoritmos.Controlador.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) {
        Controlador controlador = new Controlador(stage);
    }
}
