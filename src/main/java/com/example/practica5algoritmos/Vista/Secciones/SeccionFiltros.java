package com.example.practica5algoritmos.Vista.Secciones;

import javafx.scene.control.Button;

public class SeccionFiltros {
    private Button botonTodos;
    private Button botonNiveles;
    private Button botonAtributos;
    private Button botonAtaque;
    private Button botonDefensa;

    public SeccionFiltros() {
        botonTodos = new Button("Mostrar todos");
        botonTodos.getStyleClass().add("boton-filtro");

        botonNiveles = new Button("Mostrar por Niveles");
        botonNiveles.getStyleClass().add("boton-filtro");

        botonAtributos = new Button("Mostrar por Atributos");
        botonAtributos.getStyleClass().add("boton-filtro");

        botonAtaque = new Button("Mostrar por Ataque");
        botonAtaque.getStyleClass().add("boton-filtro");

        botonDefensa = new Button("Mostrar por Defensa");
        botonDefensa.getStyleClass().add("boton-filtro");


    }

    public void mostrarBotones(boolean esVisible) {
        botonTodos.setVisible(esVisible);
        botonNiveles.setVisible(esVisible);
        botonAtributos.setVisible(esVisible);
        botonAtaque.setVisible(esVisible);
        botonDefensa.setVisible(esVisible);
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
}
