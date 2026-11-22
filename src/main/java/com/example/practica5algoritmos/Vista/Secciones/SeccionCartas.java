package com.example.practica5algoritmos.Vista.Secciones;

import com.example.practica5algoritmos.Modelo.LibreriaCartas.CartaYGO;
import com.example.practica5algoritmos.Vista.ObjetosGUI.CartaGUI;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class SeccionCartas {
    private HBox contenedor;
    private CartaGUI cartaCentral;
    private CartaGUI cartaIzquierda;
    private CartaGUI cartaDerecha;
    private RotateTransition rotacionCarta;
    private int tamanosCartasLaterales = 160;

    /**
     * Constructor de la sección de cartas
     * @param carta Carta central a gráficar
     */
    public SeccionCartas(CartaYGO carta) {
        contenedor = new HBox();

        crearCartaCentral(carta);
        crearCartaIzquierda(carta);
        crearCartaDerecha(carta);

        contenedor.setSpacing(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getChildren().addAll(cartaIzquierda, cartaCentral, cartaDerecha);
    }

    /**
     * Metodo que crea la carta central
     * @param carta Carta a graficar
     */
    public void crearCartaCentral(CartaYGO carta) {
        cartaCentral = new CartaGUI(carta);
        rotacionCarta = new RotateTransition(Duration.seconds(8), cartaCentral);
        rotacionCarta.setAxis(Rotate.Y_AXIS);
        rotacionCarta.setByAngle(270);
        rotacionCarta.setAutoReverse(true);

        rotacionCarta.setInterpolator(Interpolator.LINEAR);
        rotacionCarta.setCycleCount(Animation.INDEFINITE);
        rotacionCarta.play();
    }

    /**
     * Metodo que crea la carta de la izquierda
     * @param carta Carta a graficar
     */
    public void crearCartaIzquierda(CartaYGO carta) {
        cartaDerecha = new CartaGUI(carta);
        cartaDerecha.cambiarTamano(tamanosCartasLaterales);

    }

    /**
     * Metodo que crea la carta de la derecha
     * @param carta Carta a graficar
     */
    public void crearCartaDerecha(CartaYGO carta) {
        cartaIzquierda = new CartaGUI(carta);
        cartaIzquierda.cambiarTamano(tamanosCartasLaterales);
    }

    /**
     * Getter del contenedor donde se ubica la sección
     * @return Contendor que engloba toda la seccion
     */
    public HBox getContenedor() {
        return contenedor;
    }

    /**
     * Metodo que retorna la animación dada a la carta central
     * @return Animación rotatoria dada a la carta
     */
    public RotateTransition getAnimacionCarta() {
        return rotacionCarta;
    }

    /**
     * Getter de la carta izquierda
     * @return Representación visual de la carta izquierda
     */
    public CartaGUI getCartaDerecha() {
        return cartaDerecha;
    }

    /**
     * Getter de la carta derecha
     * @return Representación visual de la carta derecha
     */
    public CartaGUI getCartaIzquierda() {
        return cartaIzquierda;
    }

    /**
     * Metodo que actualiza las cartas de la sección
     * @param anterior Carta anterior a la central
     * @param actual Carta a mostrar
     * @param siguiente Carta siguiente a la central
     */
    public void mostrarCartas(CartaYGO anterior, CartaYGO actual, CartaYGO siguiente) {
        actualizarCarta(anterior, cartaIzquierda);
        actualizarCarta(actual, cartaCentral);
        actualizarCarta(siguiente, cartaDerecha);
    }

    /**
     * Metodo que actualiza la carta actual
     * @param carta Carta dada por el controlador a graficar
     * @param cartaAMostrar Representación visual de la carta correspondiente
     */
    public void actualizarCarta(CartaYGO carta, CartaGUI cartaAMostrar) {
        cartaAMostrar.setVisible(carta != null);
        if (carta != null) cartaAMostrar.setCarta(carta);
    }

    /**
     * Asigna un evento al clickear la carta central
     * @param accion Accion a realizar
     */
    public void setOnClickCartaCentral(Runnable accion) {
        cartaCentral.setOnMouseClicked(e -> accion.run());
    }

    /**
     * Asigna un evento al clickear la carta de la derecha
     * @param accion Accion a realizar
     */
    public void setOnClickCartaDerecha(Runnable accion) {
        cartaDerecha.setOnMouseClicked(e-> accion.run());
    }

    /**
     * Asigna un evento al clickear la carta de la izquierda
     * @param accion Accion a realizar
     */
    public void setOnClickCartaIzquierda(Runnable accion) {
        cartaIzquierda.setOnMouseClicked(e-> accion.run());
    }
}
