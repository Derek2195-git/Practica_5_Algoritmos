package com.example.practica5algoritmos.Controlador;

import com.example.practica5algoritmos.Modelo.CartaYGO;
import com.example.practica5algoritmos.Modelo.LectorCSV;
import com.example.practica5algoritmos.Modelo.LibreriaYGO;
import com.example.practica5algoritmos.Vista.Secciones.SeccionCartas;
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

        actualizarCartas();

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
        actualizarCartas();
    }

    public void manejarClickCartaIzquierda() {
        libreria.retroceder();
        actualizarCartas();
    }

    public void actualizarCartas() {
        SeccionCartas seccionCartas = ventana.getSeccionCartas();
        seccionCartas.mostrarCartas(libreria.getAnterior(), libreria.getActual(), libreria.getSiguiente());
    }

}
