package com.example.practica5algoritmos.Vista;

import com.example.practica5algoritmos.Modelo.CartaYGO;
import com.example.practica5algoritmos.Vista.Secciones.SeccionCartas;
import com.example.practica5algoritmos.Vista.Secciones.SeccionDetalles;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class VentanaLibreria {

    private VBox ventana;
    private SeccionCartas seccionCartas;
    private SeccionDetalles seccionDetalles;

    public VentanaLibreria(CartaYGO carta) {
        seccionCartas = new SeccionCartas(carta);
        seccionDetalles = new SeccionDetalles();
        ventana = new VBox();

        ventana.getChildren().addAll(seccionCartas.getContenedor(), seccionDetalles.getContenedor());
        ventana.setAlignment(Pos.CENTER);
        ventana.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
    }

    public VBox getVentana() {
        return ventana;
    }

    public SeccionCartas getSeccionCartas() {
        return seccionCartas;
    }

    public SeccionDetalles getSeccionDetalles() {
        return seccionDetalles;
    }


}
