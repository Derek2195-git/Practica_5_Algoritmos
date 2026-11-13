package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.LibreriaCartas.CartaYGO;
import javafx.geometry.Insets;

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