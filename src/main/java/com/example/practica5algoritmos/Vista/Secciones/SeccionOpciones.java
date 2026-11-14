package com.example.practica5algoritmos.Vista.Secciones;

import com.example.practica5algoritmos.Vista.ObjetosGUI.BotonSonido;
import com.example.practica5algoritmos.Vista.ObjetosGUI.ImageButton;
import javafx.scene.layout.HBox;

public class SeccionOpciones {
    private BotonSonido botonSonido;
    private HBox contenedor;
    private ImageButton botonFiltros;

    public SeccionOpciones() {
        botonSonido = new BotonSonido("/recursos/hip_shop.mp3");
        contenedor = new HBox(5);
        botonFiltros = new ImageButton("/recursos/iconos/lupa.png", 32, 32);

        contenedor.getChildren().addAll(botonSonido, botonFiltros);
    }

    public void alSeleccionarFiltros(Runnable accion) { botonFiltros.setOnAction(e-> accion.run()); }

    public HBox getContenedor() {
        return contenedor;
    }
}
