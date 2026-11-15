package com.example.practica5algoritmos.Vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class VentanaFiltros {
    public double separacion = 10;
    public double ancho_estimado = 280;

    private Button botonTodos;
    private Button botonNiveles;
    private Button botonAtributos;
    private Button botonAtaque;
    private Button botonDefensa;
    private VBox contenedor;
    private Stage ventana;

    public VentanaFiltros() {
        botonTodos = crearBoton("Mostrar todos");
        botonNiveles = crearBoton("Mostrar por Niveles");
        botonAtributos = crearBoton("Mostrar por Atributos");
        botonAtaque = crearBoton("Mostrar por Ataque");
        botonDefensa = crearBoton("Mostrar por Defensa");


        contenedor = new VBox(8);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(16));
        contenedor.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        contenedor.getChildren().addAll(botonTodos, botonNiveles,
                botonAtributos, botonAtaque, botonDefensa);


    }

    public Button crearBoton(String nombreBoton) {
        Button boton = new Button(nombreBoton);
        boton.getStyleClass().add("boton-filtro");
        boton.setMaxWidth(Double.MAX_VALUE);
        return boton;
    }

    public void abrirVentana(Window ventanaPrincipal) {
        if (ventana == null) {
            crearVentana(ventanaPrincipal);
        }
        if (!ventana.isShowing()) {
            ventana.show();
        }
    }

    public boolean ventanaEstaAbierta() {
        return ventana != null && ventana.isShowing();
    }

    public void crearVentana(Window ventanaPrincipal) {
        Scene escena = new Scene(contenedor);
        escena.getStylesheets().add(getClass().getResource("/recursos/estilos.css").toExternalForm());


        ventana = new Stage();
        ventana.initOwner(ventanaPrincipal);
        ventana.setTitle("Filtros de visuaizacon");
        ventana.setResizable(false);
        ventana.setScene(escena);

        Rectangle2D pantalla = Screen.getPrimary().getVisualBounds();
        double x = ventanaPrincipal.getX() + ventanaPrincipal.getWidth() + separacion;
        if (x + ancho_estimado > pantalla.getMaxX()) {
            x = pantalla.getMaxX() - ancho_estimado;
        }
        ventana.setX(x);
        ventana.setY(ventanaPrincipal.getY());
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
