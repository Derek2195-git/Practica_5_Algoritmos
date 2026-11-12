package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.CartaYGO;
import com.example.practica5algoritmos.Modelo.ListaCircular;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DescripcionCarta extends VBox {
    private Label labelNombre ;
    private Label labelAtributo ;

    private Label labelTipo ;
    private Label labelAtaque ;
    private Label labelDefensa ;
    private Label labelRareza;
    private HBox rarezaCarta;
    private Label labelDescripcion ;
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
        rarezaCarta = new HBox();
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
                Image imagen = new Image(getClass().getResource("/RarezaCartaYGO.png").toExternalForm());
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
        labelNombre.setText(nombre);
        labelAtributo.setText(atributo);
        anadirNivelCarta(nivel);
        labelTipo.setText(tipo);
        labelAtaque.setText(ataque);
        labelDefensa.setText(defensa);
        labelRareza.setText(rareza);
        labelDescripcion.setText(descripcion);
    }

    public VBox crearContenedor() {
        VBox contenedorDescripcion = new VBox();

        contenedorDescripcion.getChildren().addAll(labelNombre, labelAtributo, rarezaCarta, labelTipo,
                labelAtaque, labelDefensa, labelRareza,labelDescripcion);
        contenedorDescripcion.setAlignment(Pos.CENTER);

        return contenedorDescripcion;
    }

    public void meterEstilos() {
        Label labelActual = listaDetalles.eliminaFin();
        while (labelActual != null) {
            labelActual.getStyleClass().add("label");
            labelActual = listaDetalles.eliminaFin();
        }
    }
}