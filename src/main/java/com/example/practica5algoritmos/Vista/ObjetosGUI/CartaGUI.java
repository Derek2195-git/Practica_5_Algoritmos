package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.LibreriaCartas.CartaYGO;
import javafx.geometry.Insets;

public class CartaGUI extends ImageButton {
    private static final int ANCHO = 320;
    private static final int ALTO = 240;


    private CartaYGO carta;

    /**
     * Constructor de la representación visual de la carta
     * @param carta Carta a mostrar
     */
    public CartaGUI(CartaYGO carta) {
        super(generarRuta(carta.getImagen()), ALTO, ANCHO);
        this.carta = carta;

        setPadding(Insets.EMPTY);
        setFocusTraversable(false);
    }

    /**
     * Metodo que cambia la imagen de la carta
     * @param carta
     */
    public void setCarta(CartaYGO carta) {
        this.carta = carta;
        if (carta == null) {
            setGraphic(null);
            return;
        }
        cambiarImagen(generarRuta(carta.getImagen()));
    }

    /**
     * Metodo que genera la ruta donde se ubica la imagen de la carta
     * @param ruta Nombre del archivo de la carta
     * @return Ruta completa de la ubicación de la carta
     */
    public static String generarRuta(String ruta) {
        return "/recursos/cartas_por_id/" + ruta;
    }

    /**
     * Metodo que encoje el tamaño de la carta, es usado para las laterales
     * @param tamanoCarta Nuevo tamaño de la carta
     */
    public void cambiarTamano(int tamanoCarta) {
        cambiarTamanoImagen(tamanoCarta, tamanoCarta/2);
    }

    /**
     * Getter del ancho de la carta
     * @return Ancho de la carta
     */
    public int getAncho() {
        return ANCHO;
    }

    /**
     * Getter de la altura de la carta
     * @return Altura de la carta
     */
    public int getAlto() {
        return ALTO;
    }

}