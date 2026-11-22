package com.example.practica5algoritmos.Vista.Secciones;

import com.example.practica5algoritmos.Vista.ObjetosGUI.DescripcionCarta;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SeccionDetalles {

    private VBox contenedor;
    private DescripcionCarta detallesCartaActual;
    public SeccionDetalles() {
        detallesCartaActual = new DescripcionCarta();

        contenedor = new VBox();
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getChildren().addAll(detallesCartaActual.crearContenedor());
    }

    public void actualizarDetalles(String nombre, String atributo, String nivel, String tipo, String ataque,
                                   String defensa, String rareza, String descripcion) {
        detallesCartaActual.actualizarDetalles(nombre, atributo, Integer.valueOf(nivel), tipo, ataque, defensa, rareza, descripcion);
    }

    public VBox getContenedor() {
        return contenedor;
    }


}
