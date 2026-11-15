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
    private Stage stage;

    public Controlador(Stage stage) {
        this.stage = stage;
        try {
            cartas = new LectorCSV().leerCartas();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        libreria = new LibreriaYGO(cartas);
        ventana = new VentanaLibreria(libreria.getActual());
        ventana.getSeccionFiltros().cargarAtributos(libreria.getAtributosDisponibles());
        Scene escena = new Scene(ventana.getVentana(), 800, 600);

        ventana.getSeccionCartas().setOnClickCartaCentral(this::manejarClickCartaCentral);
        ventana.getSeccionCartas().setOnClickCartaDerecha(this::manejarClickCartaDerecha);
        ventana.getSeccionCartas().setOnClickCartaIzquierda(this::manejarClickCartaIzquierda);
        ventana.getSeccionOpciones().alSeleccionarFiltros(this::manejarClickFiltro);

        ventana.getSeccionFiltros().alMostrarTodos(this::manejarMostrarTodos);
        ventana.getSeccionFiltros().alMostrarPorAtributos(this::manejarMostrarAtributos);
        ventana.getSeccionFiltros().alMostrarPorNiveles(this::manejarMostrarNiveles);
        ventana.getSeccionFiltros().alMostrarPorAtaque(this::manejarMostrarAtaque);
        ventana.getSeccionFiltros().alMostrarPorDefensa(this::manejarMostrarDefensa);

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

        // Y solo me quedaria actualizar el label aqui
        ventana.actualizarCantidadCartas(libreria.getPosicion(), libreria.getTotal());

    }

    public void manejarClickFiltro() {
        ventana.getSeccionFiltros().abrirVentana(stage);
    }


    public void manejarMostrarTodos() {
        libreria.mostrarTodas();
        actualizarCartas();
        actualizarDetalles();
    }

    public void manejarMostrarNiveles() {
        String nivel = ventana.getSeccionFiltros().getNivelSeleccionado();
        if (nivel == null) return;
        if (nivel.equalsIgnoreCase("N/A")) {
            libreria.mostrarHechizosTrampas();
        } else {
            libreria.mostrarPorNivel(Integer.parseInt(nivel));
        }
        actualizarCartas();
        actualizarDetalles();
    }

    public void manejarMostrarAtributos() {
        String atributo = ventana.getSeccionFiltros().getAtributoSeleccionado();
        if (atributo == null) return;

        libreria.mostrarPorAtributo(atributo);
        actualizarCartas();
        actualizarDetalles();
    }

    public void manejarMostrarAtaque() {
        libreria.mostrarPorAtaque(!ventana.getSeccionFiltros().isAtaqueAscendente());
        actualizarDetalles();
        actualizarCartas();
    }

    public void manejarMostrarDefensa() {
        libreria.mostrarPorDefensa(!ventana.getSeccionFiltros().isDefensaAscendente());
        actualizarDetalles();
        actualizarCartas();
    }



}
