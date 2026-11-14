package com.example.practica5algoritmos.Vista;

import com.example.practica5algoritmos.Modelo.LibreriaCartas.CartaYGO;
import com.example.practica5algoritmos.Vista.Secciones.SeccionCartas;
import com.example.practica5algoritmos.Vista.Secciones.SeccionDetalles;
import com.example.practica5algoritmos.Vista.Secciones.SeccionFiltros;
import com.example.practica5algoritmos.Vista.Secciones.SeccionOpciones;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VentanaLibreria {

    private StackPane ventana;
    private SeccionCartas seccionCartas;
    private SeccionDetalles seccionDetalles;
    private SeccionOpciones seccionOpciones;
    private SeccionFiltros seccionFiltros;
    private Label totalCartas;

    public VentanaLibreria(CartaYGO carta) {
        seccionCartas = new SeccionCartas(carta);
        seccionDetalles = new SeccionDetalles();
        seccionOpciones = new SeccionOpciones();
        seccionFiltros = new SeccionFiltros();
        totalCartas = new Label("");
        totalCartas.getStyleClass().add("label-cantidad");

        BorderPane barraSuperior = new BorderPane();
        barraSuperior.setLeft(seccionOpciones.getContenedor());
        barraSuperior.setCenter(totalCartas);
        barraSuperior.setPadding(new Insets(10));

        StackPane detallesCartas = new StackPane(seccionDetalles.getContenedor());
        detallesCartas.setMinHeight(170);
        detallesCartas.setPrefHeight(170);
        detallesCartas.setMaxHeight(170);
        detallesCartas.setPadding(new Insets(0, 16, 16, 16));

        BorderPane contenido = new BorderPane();
        contenido.setTop(barraSuperior);
        contenido.setCenter(seccionCartas.getContenedor());
        contenido.setBottom(detallesCartas);

        VBox filtros = seccionFiltros.getContenedor();
        StackPane.setAlignment(filtros, Pos.CENTER_LEFT);
        StackPane.setMargin(filtros, new Insets(0, 0, 0, 12));

        ventana = new StackPane(contenido, filtros);

        ventana.getStylesheets().add(getClass().getResource("/recursos/estilos.css").toExternalForm());
    }

    public StackPane getVentana() {
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

    public SeccionFiltros getSeccionFiltros() {
        return seccionFiltros;
    }

    public void actualizarCantidadCartas(int cantidadCartas, int totalCartas) {
        this.totalCartas.setText(cantidadCartas + " / " + totalCartas);
    }


}
