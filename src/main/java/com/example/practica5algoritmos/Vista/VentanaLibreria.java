package com.example.practica5algoritmos.Vista;

import com.example.practica5algoritmos.Modelo.LibreriaCartas.CartaYGO;
import com.example.practica5algoritmos.Vista.Secciones.SeccionCartas;
import com.example.practica5algoritmos.Vista.Secciones.SeccionDetalles;
import com.example.practica5algoritmos.Vista.Secciones.SeccionFiltros;
import com.example.practica5algoritmos.Vista.Secciones.SeccionOpciones;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class VentanaLibreria {

    private VBox ventana;
    private SeccionCartas seccionCartas;
    private SeccionDetalles seccionDetalles;
    private SeccionOpciones seccionOpciones;
    private SeccionFiltros seccionFiltros;

    public VentanaLibreria(CartaYGO carta) {
        seccionCartas = new SeccionCartas(carta);
        seccionDetalles = new SeccionDetalles();
        seccionOpciones = new SeccionOpciones();
        seccionFiltros = new SeccionFiltros();

        ventana = new VBox();

        ventana.getChildren().addAll(seccionOpciones.getContenedor(), seccionCartas.getContenedor(), seccionDetalles.getContenedor());
        ventana.setAlignment(Pos.CENTER);
        ventana.getStylesheets().add(getClass().getResource("/recursos/estilos.css").toExternalForm());
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

    public SeccionOpciones getSeccionOpciones() {
        return seccionOpciones;
    }


}
