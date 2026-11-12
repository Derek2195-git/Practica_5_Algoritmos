package com.example.practica5algoritmos;

import com.example.practica5algoritmos.Controlador.Controlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new Controlador(stage);
    }
}
