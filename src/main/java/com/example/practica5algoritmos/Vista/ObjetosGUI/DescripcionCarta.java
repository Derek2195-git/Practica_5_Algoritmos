package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.Listas.ListaCircular;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class DescripcionCarta extends VBox {
    private final int TAMANO_ICONO = 22;
    private Label labelNombre ;
    private Label labelAtributo ;

    private Label labelTipo ;
    private Label labelAtaque ;
    private Label labelDefensa ;
    private Label labelRareza;
    private HBox rarezaCarta;
    private Label labelDescripcion ;
    private ScrollPane descripcionCarta;
    private ListaCircular<Label> listaDetalles;
    private Image iconoNivel;

    public DescripcionCarta() {
        listaDetalles = new ListaCircular<>();
        labelNombre = new Label();
        labelAtributo = new Label();
        labelTipo = new Label();
        labelAtaque = new Label();
        labelDefensa = new Label();

        labelRareza = new Label("Rareza: ");
        labelDescripcion = new Label();
        labelDescripcion.setWrapText(true);
        labelDescripcion.setMaxWidth(Double.MAX_VALUE);
        labelDescripcion.setTextAlignment(TextAlignment.JUSTIFY);
        labelDescripcion.getStyleClass().add("label-descripcion");

        descripcionCarta = new ScrollPane(labelDescripcion);
        descripcionCarta.setFitToWidth(true);
        descripcionCarta.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        descripcionCarta.setMinHeight(60);
        descripcionCarta.setPrefHeight(60);
        descripcionCarta.getStyleClass().add("scroll-descripcion");
        VBox.setVgrow(descripcionCarta, Priority.ALWAYS);

        rarezaCarta = new HBox(5);
        rarezaCarta.setAlignment(Pos.CENTER);

        try {
            iconoNivel = new Image(getClass().getResource("/recursos/iconos/RarezaCartaYGO.png")
                    .toExternalForm(), TAMANO_ICONO, TAMANO_ICONO, true, true);

        } catch (RuntimeException e) {
            System.out.println("No se pudó cargar la imagen de la rareza de las cartas");
        }

        listaDetalles.insertaFin(labelNombre);
        listaDetalles.insertaFin(labelAtributo);
        listaDetalles.insertaFin(labelTipo);
        listaDetalles.insertaFin(labelAtaque);
        listaDetalles.insertaFin(labelDefensa);
        listaDetalles.insertaFin(labelRareza);
        listaDetalles.insertaFin(labelDescripcion);


    }

    public void anadirNivelCarta(int rareza) {
        rarezaCarta.getChildren().clear();
        if (iconoNivel == null) return;

        for (int i = 0; i < rareza; i++) {
            ImageView icono = new ImageView(iconoNivel);
            icono.setFitHeight(TAMANO_ICONO);
            icono.setFitWidth(TAMANO_ICONO);
            icono.setPreserveRatio(true);
            rarezaCarta.getChildren().add(icono);
        }
    }

    public void actualizarDetalles(String nombre, String atributo, int nivel, String tipo, String ataque,
                                   String defensa, String rareza, String descripcion) {

        configurarVisibilidadLabel(labelNombre, nombre, nombre);
        configurarVisibilidadLabel(labelAtributo, atributo, atributo);
        configurarVisibilidadLabel(labelTipo, tipo, tipo);
        configurarVisibilidadLabel(labelRareza, rareza, rareza);
        configurarVisibilidadLabel(labelDescripcion, descripcion, descripcion);

        configurarVisibilidadLabel(labelAtaque, ataque, "ATK: " + ataque);
        configurarVisibilidadLabel(labelDefensa, defensa, "DEF: " + defensa);



        if (nivel == -1 || nivel == 0) {
            rarezaCarta.setVisible(false);
            rarezaCarta.setManaged(false);
        } else {
            rarezaCarta.setVisible(true);
            rarezaCarta.setManaged(true);
            anadirNivelCarta(nivel);
        }
    }

    public VBox crearContenedor() {
        VBox contenedorDescripcion = new VBox(5);
        contenedorDescripcion.setMaxWidth(Double.MAX_VALUE);
        contenedorDescripcion.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(contenedorDescripcion, Priority.ALWAYS);
        contenedorDescripcion.setAlignment(Pos.CENTER);

        HBox primeraFila = new HBox(12);
        primeraFila.setAlignment(Pos.CENTER);
        primeraFila.getChildren().addAll(labelRareza, labelNombre, labelAtributo);

        HBox penultimaFila = new HBox(30);
        penultimaFila.setAlignment(Pos.CENTER);
        penultimaFila.getChildren().addAll(labelTipo, labelAtaque, labelDefensa);

        contenedorDescripcion.getChildren().addAll(
                primeraFila, rarezaCarta, penultimaFila, descripcionCarta
        );
        contenedorDescripcion.getStyleClass().add("descripcion");


        return contenedorDescripcion;
    }

    private void configurarVisibilidadLabel(Label label, String valorOriginal, String textoFinal) {
        if (valorOriginal == null || valorOriginal.equals("N/A") || valorOriginal.equals("-1")) {
            label.setVisible(false);
            label.setManaged(false); // Evita que ocupe espacio en el contenedor
        } else {
            label.setText(textoFinal);
            label.setVisible(true);
            label.setManaged(true); // Lo vuelve a incluir en el layout
        }
    }

    public void meterEstilos() {
        Label labelActual = listaDetalles.eliminaFin();
        while (labelActual != null) {
            labelActual.getStyleClass().add("label");
            labelActual = listaDetalles.eliminaFin();
        }
    }
}