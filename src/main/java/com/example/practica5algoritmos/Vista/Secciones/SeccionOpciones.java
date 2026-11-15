package com.example.practica5algoritmos.Vista.Secciones;

import com.example.practica5algoritmos.Modelo.Listas.ListaCircular;
import com.example.practica5algoritmos.Vista.ObjetosGUI.BotonSonido;
import com.example.practica5algoritmos.Vista.ObjetosGUI.ImageButton;
import javafx.scene.layout.HBox;

public class SeccionOpciones {
    private BotonSonido botonSonido;
    private HBox contenedor;
    private ImageButton botonFiltros;

    public SeccionOpciones() {
        botonSonido = new BotonSonido(crearPlayList());
        contenedor = new HBox(5);
        botonFiltros = new ImageButton("/recursos/iconos/lupa.png", 32, 32);

        contenedor.getChildren().addAll(botonSonido, botonFiltros);
    }

    public ListaCircular<String> crearPlayList() {
        ListaCircular<String> playlist = new ListaCircular<>();
        playlist.insertaFin("/recursos/canciones/hip_shop.mp3");
        playlist.insertaFin("/recursos/canciones/flower_cafe.mp3");
        playlist.insertaFin("/recursos/canciones/greenroom_detune.mp3");
        return playlist;
    }

    public void alSeleccionarFiltros(Runnable accion) { botonFiltros.setOnAction(e-> accion.run()); }

    public HBox getContenedor() {
        return contenedor;
    }
}
