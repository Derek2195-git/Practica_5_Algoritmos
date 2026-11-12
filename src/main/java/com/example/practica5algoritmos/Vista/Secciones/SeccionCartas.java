package com.example.practica5algoritmos.Vista.Secciones;

import com.example.practica5algoritmos.Modelo.CartaYGO;
import com.example.practica5algoritmos.Vista.ObjetosGUI.CartaGUI;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
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

    public SeccionCartas(CartaYGO carta) {
        contenedor = new HBox();

        crearCartaCentral(carta);
        crearCartaIzquierda(carta);
        crearCartaDerecha(carta);

        contenedor.setSpacing(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getChildren().addAll(cartaIzquierda, cartaCentral, cartaDerecha);
    }

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

    public void crearCartaIzquierda(CartaYGO carta) {
        cartaDerecha = new CartaGUI(carta);
        cartaDerecha.cambiarTamano(tamanosCartasLaterales);

    }

    public void crearCartaDerecha(CartaYGO carta) {
        cartaIzquierda = new CartaGUI(carta);
        cartaIzquierda.cambiarTamano(tamanosCartasLaterales);
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public RotateTransition getAnimacionCarta() {
        return rotacionCarta;
    }

    public CartaGUI getCartaDerecha() {
        return cartaDerecha;
    }

    public CartaGUI getCartaIzquierda() {
        return cartaIzquierda;
    }

    public void mostrarCartas(CartaYGO anterior, CartaYGO actual, CartaYGO siguiente) {
        actualizarCarta(anterior, cartaIzquierda);
        actualizarCarta(actual, cartaCentral);
        actualizarCarta(siguiente, cartaDerecha);
    }

    public void actualizarCarta(CartaYGO carta, CartaGUI cartaAMostrar) {
        cartaAMostrar.setVisible(carta != null);
        if (carta != null) cartaAMostrar.setCarta(carta);
    }

    public void setOnClickCartaCentral(Runnable accion) {
        cartaCentral.setOnMouseClicked(e -> accion.run());
    }

    public void setOnClickCartaDerecha(Runnable accion) {
        cartaDerecha.setOnMouseClicked(e-> accion.run());
    }

    public void setOnClickCartaIzquierda(Runnable accion) {
        cartaIzquierda.setOnMouseClicked(e-> accion.run());
    }
}
