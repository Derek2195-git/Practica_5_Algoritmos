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

    public DescripcionCarta() {
        listaDetalles = new ListaCircular<>();
        labelNombre = new Label();
        labelAtributo = new Label();
        labelTipo = new Label();
        labelAtaque = new Label();
        labelDefensa = new Label();

        labelRareza = new Label("Rareza: ");
        labelDescripcion = new Label();
        labelDescripcion.setMaxWidth(650);
        labelDescripcion.setWrapText(true);
        labelDescripcion.setTextAlignment(TextAlignment.JUSTIFY);

        descripcionCarta = new ScrollPane(labelDescripcion);
        descripcionCarta.setFitToWidth(true);
        descripcionCarta.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        descripcionCarta.getStyleClass().add("scroll-descripcion");
        VBox.setVgrow(descripcionCarta, Priority.ALWAYS);

        rarezaCarta = new HBox(5);
        rarezaCarta.setAlignment(Pos.CENTER);


        listaDetalles.insertaFin(labelNombre);
        listaDetalles.insertaFin(labelAtributo);
        listaDetalles.insertaFin(labelTipo);
        listaDetalles.insertaFin(labelAtaque);
        listaDetalles.insertaFin(labelDefensa);
        listaDetalles.insertaFin(labelRareza);
        listaDetalles.insertaFin(labelDescripcion);


    }

    public void anadirNivelCarta(int rareza) {
        // 1. Limpiar las imágenes de la carta anterior
        rarezaCarta.getChildren().clear();

        for (int i = 0; i < rareza; i++) {
            try {
                // 2. Usa la ruta correcta desde tu carpeta resources
                Image imagen = new Image(getClass().getResource("/recursos/iconos/RarezaCartaYGO.png").toExternalForm());
                ImageView icono = new ImageView(imagen);

                icono.setFitHeight(32);
                icono.setFitWidth(32);
                icono.setPreserveRatio(true);

                rarezaCarta.getChildren().add(icono);
            } catch (RuntimeException e) {
                System.out.println("No se pudo cargar la imagen de rareza.");
                break;
            }
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
        contenedorDescripcion.setMaxWidth(Region.USE_PREF_SIZE);
        contenedorDescripcion.setAlignment(Pos.CENTER);

        HBox primeraFila = new HBox();
        primeraFila.setAlignment(Pos.CENTER);
        primeraFila.getChildren().addAll(labelRareza, labelNombre, labelAtributo);


        HBox segundaFila = new HBox();
        segundaFila.setAlignment(Pos.CENTER);
        segundaFila.getChildren().addAll(rarezaCarta);

        HBox penultimaFila = new HBox(30);
        penultimaFila.setAlignment(Pos.CENTER);
        penultimaFila.getChildren().addAll(labelAtaque, labelDefensa);

        contenedorDescripcion.getChildren().addAll(
                primeraFila, segundaFila, labelTipo, penultimaFila, descripcionCarta
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
}