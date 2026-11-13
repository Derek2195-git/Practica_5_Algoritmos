package com.example.practica5algoritmos.Controlador;

import com.example.practica5algoritmos.Modelo.LibreriaCartas.CartaYGO;
import com.example.practica5algoritmos.Modelo.LibreriaCartas.LectorCSV;
import com.example.practica5algoritmos.Modelo.LibreriaCartas.LibreriaYGO;
import com.example.practica5algoritmos.Vista.Secciones.SeccionCartas;
import com.example.practica5algoritmos.Vista.Secciones.SeccionDetalles;
import com.example.practica5algoritmos.Vista.VentanaLibreria;
import javafx.animation.RotateTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controlador {

    private ArrayList<CartaYGO> cartas;
    private VentanaLibreria ventana;
    private LibreriaYGO libreria;

    public Controlador(Stage stage) {
        try {
            cartas = new LectorCSV().leerCartas();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        libreria = new LibreriaYGO(cartas);
        ventana = new VentanaLibreria(libreria.getActual());
        Scene escena = new Scene(ventana.getVentana(), 800, 600);

        ventana.getSeccionCartas().setOnClickCartaCentral(this::manejarClickCartaCentral);
        ventana.getSeccionCartas().setOnClickCartaDerecha(this::manejarClickCartaDerecha);
        ventana.getSeccionCartas().setOnClickCartaIzquierda(this::manejarClickCartaIzquierda);
        ventana.getSeccionOpciones().alSeleccionarFiltros(this::manejarClickFiltro);

        actualizarCartas();
        actualizarDetalles();

        stage.setTitle("YUGIOH");
        stage.setScene(escena);
        stage.show();

    }



    public void manejarClickCartaCentral() {
        RotateTransition rotacionCarta = ventana.getSeccionCartas().getAnimacionCarta();
        rotacionCarta.setRate(rotacionCarta.getRate() *- 1);
    }

    public void manejarClickCartaDerecha() {
        libreria.avanzar();
        actualizarDetalles();
        actualizarCartas();
    }

    public void manejarClickCartaIzquierda() {
        libreria.retroceder();
        actualizarDetalles();
        actualizarCartas();
    }

    public void actualizarCartas() {
        SeccionCartas seccionCartas = ventana.getSeccionCartas();
        seccionCartas.mostrarCartas(libreria.getAnterior(), libreria.getActual(), libreria.getSiguiente());
    }

    public void actualizarDetalles() {
        SeccionDetalles seccionDetalles = ventana.getSeccionDetalles();


        seccionDetalles.actualizarDetalles(libreria.getActual().getNombre(), libreria.getActual().getAtributo(),
                String.valueOf(libreria.getActual().getNivel()), libreria.getActual().getTipo(),
                String.valueOf(libreria.getActual().getAtaque()), String.valueOf(libreria.getActual().getDefensa()),
                libreria.getActual().getRareza(), libreria.getActual().getDescripcion());

    }

    public void manejarClickFiltro() {

    }

}
