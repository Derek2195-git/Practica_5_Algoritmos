package com.example.practica5algoritmos.Vista.Ventana;

import com.example.practica5algoritmos.Modelo.LibreriaCartas.CartaYGO;
import com.example.practica5algoritmos.Vista.Secciones.SeccionCartas;
import com.example.practica5algoritmos.Vista.Secciones.SeccionDetalles;
import com.example.practica5algoritmos.Vista.Secciones.SeccionOpciones;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class VentanaLibreria {

    private final double ALTO_DETALLES = 260;

    private BorderPane ventana;
    private SeccionCartas seccionCartas;
    private SeccionDetalles seccionDetalles;
    private SeccionOpciones seccionOpciones;
    private VentanaFiltros seccionFiltros;
    private Label totalCartas;

    public VentanaLibreria(CartaYGO carta) {
        seccionCartas = new SeccionCartas(carta);
        seccionDetalles = new SeccionDetalles();
        seccionOpciones = new SeccionOpciones();
        seccionFiltros = new VentanaFiltros();
        totalCartas = new Label("");
        totalCartas.getStyleClass().add("label-cantidad");

        seccionOpciones.getContenedor().setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        StackPane barraSuperior = new StackPane(seccionOpciones.getContenedor(), totalCartas);
        StackPane.setAlignment(seccionOpciones.getContenedor(), Pos.CENTER_LEFT);
        StackPane.setAlignment(totalCartas, Pos.CENTER);
        barraSuperior.setPadding(new Insets(10));

        StackPane detallesCartas = new StackPane(seccionDetalles.getContenedor());
        detallesCartas.setMinHeight(ALTO_DETALLES);
        detallesCartas.setPrefHeight(ALTO_DETALLES);
        detallesCartas.setMaxHeight(ALTO_DETALLES);
        detallesCartas.setPadding(new Insets(0, 16, 12, 16));

        ventana = new BorderPane();
        ventana.setTop(barraSuperior);
        ventana.setCenter(seccionCartas.getContenedor());
        ventana.setBottom(detallesCartas);

        ventana.getStylesheets().add(getClass().getResource("/recursos/estilos.css").toExternalForm());
    }

    public BorderPane getVentana() {
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

    public VentanaFiltros getSeccionFiltros() {
        return seccionFiltros;
    }

    public void actualizarCantidadCartas(int cantidadCartas, int totalCartas) {
        this.totalCartas.setText(cantidadCartas + " / " + totalCartas);
    }


}
