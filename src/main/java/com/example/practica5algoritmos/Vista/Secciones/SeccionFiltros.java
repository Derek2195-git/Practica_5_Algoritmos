package com.example.practica5algoritmos.Vista.Secciones;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SeccionFiltros {
    private Button botonTodos;
    private Button botonNiveles;
    private Button botonAtributos;
    private Button botonAtaque;
    private Button botonDefensa;
    private VBox contenedor;
    private boolean seccionVisible = false;

    public SeccionFiltros() {
        botonTodos = crearBoton("Mostrar todos");
        botonNiveles = crearBoton("Mostrar por Niveles");
        botonAtributos = crearBoton("Mostrar por Atributos");
        botonAtaque = crearBoton("Mostrar por Ataque");
        botonDefensa = crearBoton("Mostrar por Defensa");


        contenedor = new VBox(8);
        contenedor.setAlignment(Pos.CENTER_LEFT);
        contenedor.setPadding(new Insets(12));
        contenedor.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        contenedor.getChildren().addAll(botonTodos, botonNiveles,
                botonAtributos, botonAtaque, botonDefensa);

        contenedor.setVisible(false);

    }

    public Button crearBoton(String nombreBoton) {
        Button boton = new Button(nombreBoton);
        boton.getStyleClass().add("boton-filtro");

        return boton;

    }

    public void alternarVisibilidadBotones() {
        seccionVisible = !seccionVisible;
        contenedor.setVisible(seccionVisible);
    }

    public void alMostrarTodos(Runnable accion) {
        botonTodos.setOnAction(e -> accion.run());
    }

    public void alMostrarPorNiveles(Runnable accion) {
        botonNiveles.setOnAction(e->accion.run());
    }

    public void alMostrarPorAtributos(Runnable accion) {
        botonAtributos.setOnAction(e-> accion.run());
    }

    public void alMostrarPorAtaque(Runnable accion){
        botonAtaque.setOnAction(e->accion.run());
    }

    public void alMostrarPorDefensa(Runnable accion) {
        botonDefensa.setOnAction(e-> accion.run());
    }

    public VBox getContenedor() {
        return contenedor;
    }
}
