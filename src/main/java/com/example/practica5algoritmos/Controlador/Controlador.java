package com.example.practica5algoritmos.Controlador;

import com.example.practica5algoritmos.Modelo.LibreriaCartas.CartaYGO;
import com.example.practica5algoritmos.Modelo.LibreriaCartas.LectorCSV;
import com.example.practica5algoritmos.Modelo.LibreriaCartas.LibreriaYGO;
import com.example.practica5algoritmos.Vista.Secciones.SeccionCartas;
import com.example.practica5algoritmos.Vista.Secciones.SeccionDetalles;
import com.example.practica5algoritmos.Vista.Ventana.VentanaLibreria;
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

    /**
     * Constructor del controlador, esté inicializa
     * todo lo necesario para la libreria
     * @param stage
     */
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


    /**
     * Metodo que maneja la acción que ocurre cuando le damos click a la carta central
     */
    public void manejarClickCartaCentral() {
        RotateTransition rotacionCarta = ventana.getSeccionCartas().getAnimacionCarta();
        rotacionCarta.setRate(rotacionCarta.getRate() *- 1);
    }

    /**
     * Metodo que maneja la acción que ocurre cuando le
     * damos click a la carta de la derecha
     */
    public void manejarClickCartaDerecha() {
        libreria.avanzar();
        actualizarDetalles();
        actualizarCartas();
    }

    /**
     * Metodo que maneja la acción a realizar cuando clickeamos la carta de la izquierda
     */
    public void manejarClickCartaIzquierda() {
        libreria.retroceder();
        actualizarDetalles();
        actualizarCartas();
    }

    /**
     * Metodo que actualiza las cartas mostradas al usuario cuando este aplica un filtro o
     * clickea una de las cartas de los lados
     */
    public void actualizarCartas() {
        SeccionCartas seccionCartas = ventana.getSeccionCartas();
        seccionCartas.mostrarCartas(libreria.getAnterior(), libreria.getActual(), libreria.getSiguiente());
    }

    /**
     * Metodo que actualiza el apartado de detalles de la carta del centro,
     * así como el label que indica la carta actual y el total de estas
     */
    public void actualizarDetalles() {
        SeccionDetalles seccionDetalles = ventana.getSeccionDetalles();


        seccionDetalles.actualizarDetalles(libreria.getActual().getNombre(), libreria.getActual().getAtributo(),
                String.valueOf(libreria.getActual().getNivel()), libreria.getActual().getTipo(),
                String.valueOf(libreria.getActual().getAtaque()), String.valueOf(libreria.getActual().getDefensa()),
                libreria.getActual().getRareza(), libreria.getActual().getDescripcion());

        // Y solo me quedaria actualizar el label aqui
        ventana.actualizarCantidadCartas(libreria.getPosicion(), libreria.getTotal());

    }

    /**
     * Metodo que maneja lo que ocurre cuando el usuario le da click al botón de mostrar los filtros
     */
    public void manejarClickFiltro() {
        ventana.getSeccionFiltros().abrirVentana(stage);
    }

    /**
     * Metodo que maneja lo que ocurre cuando el usuario
     * le da click al botón de mostrar todas las cartas
     */
    public void manejarMostrarTodos() {
        libreria.mostrarTodas();
        actualizarCartas();
        actualizarDetalles();
    }

    /**
     * Metodo que maneja lo que ocurre cuando el usuario le da click al
     * boton de mostrar todas las cartas
     */
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

    /**
     * Metodo que maneja lo que ocurre cuando se activa el botón de mostrar todas las cartas por
     * un atributo en especifico
     */
    public void manejarMostrarAtributos() {
        String atributo = ventana.getSeccionFiltros().getAtributoSeleccionado();
        if (atributo == null) return;

        libreria.mostrarPorAtributo(atributo);
        actualizarCartas();
        actualizarDetalles();
    }

    /**
     * Metodo que maneja lo que ocurre cuando se activa el botón de ordenar y mostrar todas las
     * cartas por su ataque
     */
    public void manejarMostrarAtaque() {
        libreria.mostrarPorAtaque(!ventana.getSeccionFiltros().isAtaqueAscendente());
        actualizarDetalles();
        actualizarCartas();
    }

    /**
     * Metodo que maneja lo que ocurre cuando se hace click al botón de ordenar y mostrar
     * las cartas por su defensa
     */
    public void manejarMostrarDefensa() {
        libreria.mostrarPorDefensa(!ventana.getSeccionFiltros().isDefensaAscendente());
        actualizarDetalles();
        actualizarCartas();
    }



}
