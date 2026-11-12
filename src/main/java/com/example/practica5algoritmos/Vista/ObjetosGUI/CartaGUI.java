package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.CartaYGO;
import com.example.practica5algoritmos.Modelo.TipoVista;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class CartaGUI extends ImageButton {
    private static final int ANCHO = 320;
    private static final int ALTO = 240;


    private CartaYGO carta;

    public CartaGUI(CartaYGO carta) {
        super(generarRuta(carta.getImagen()), ALTO, ANCHO);
        this.carta = carta;

        setPadding(Insets.EMPTY);
        setFocusTraversable(false);
    }

    public void setCarta(CartaYGO carta) {
        this.carta = carta;
        if (carta == null) {
            setGraphic(null);
            return;
        }
        cambiarImagen(generarRuta(carta.getImagen()));
    }

    public static String generarRuta(String ruta) {
        return "/cartas_por_id/" + ruta;
    }

    public void cambiarTamano(int tamanoCarta) {
        cambiarTamanoImagen(tamanoCarta, tamanoCarta/2);
    }

    public int getAncho() {
        return ANCHO;
    }

    public int getAlto() {
        return ALTO;
    }

}