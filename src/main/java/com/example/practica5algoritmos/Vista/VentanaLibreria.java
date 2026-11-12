package com.example.practica5algoritmos.Vista;

import com.example.practica5algoritmos.Modelo.CartaYGO;
import com.example.practica5algoritmos.Vista.Secciones.SeccionCartas;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class VentanaLibreria {

    private VBox ventana;
    private SeccionCartas seccionCartas;

    public VentanaLibreria(CartaYGO carta) {
        seccionCartas = new SeccionCartas(carta);
        ventana = new VBox();

        ventana.getChildren().add(seccionCartas.getContenedor());
        ventana.setAlignment(Pos.CENTER);
        ventana.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
    }

    public VBox getVentana() {
        return ventana;
    }

    public SeccionCartas getSeccionCartas() {
        return seccionCartas;
    }


}
