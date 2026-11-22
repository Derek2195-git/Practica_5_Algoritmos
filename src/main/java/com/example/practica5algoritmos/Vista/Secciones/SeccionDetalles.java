package com.example.practica5algoritmos.Vista.Secciones;

import com.example.practica5algoritmos.Vista.ObjetosGUI.DescripcionCarta;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SeccionDetalles {

    private VBox contenedor;
    private DescripcionCarta detallesCartaActual;

    /**
     * Constructor de la sección de detalles
     */
    public SeccionDetalles() {
        detallesCartaActual = new DescripcionCarta();

        contenedor = new VBox();
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getChildren().addAll(detallesCartaActual.crearContenedor());
    }

    /**
     * Metodo que actualiza los detalles de la carta actual
     * @param nombre Nuevo nombre a mostrar
     * @param atributo Nuevo atributo a mostrar
     * @param nivel Nuevo nivel a mostrar
     * @param tipo Nuevo tipo a mostrar
     * @param ataque Nuevo ataque a mostrar
     * @param defensa Nueva defensa a mostrar
     * @param rareza Nueva rareza a mostrar
     * @param descripcion Nueva descripcion a mostrar
     */
    public void actualizarDetalles(String nombre, String atributo, String nivel, String tipo, String ataque,
                                   String defensa, String rareza, String descripcion) {
        detallesCartaActual.actualizarDetalles(nombre, atributo, Integer.valueOf(nivel), tipo, ataque, defensa, rareza, descripcion);
    }

    /**
     * Getter del contenedor de la sección de detalles
     * @return Contenedor que contiene toda la sección
     */
    public VBox getContenedor() {
        return contenedor;
    }


}
