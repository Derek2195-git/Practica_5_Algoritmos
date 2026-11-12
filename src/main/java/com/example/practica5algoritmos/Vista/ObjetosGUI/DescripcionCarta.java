package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.CartaYGO;
import com.example.practica5algoritmos.Modelo.ListaCircular;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DescripcionCarta extends VBox {
    private Label labelNombre ;
    private Label labelAtributo ;
    private Label labelNivel ;
    private Label labelTipo ;
    private Label labelAtaque ;
    private Label labelDefensa ;
    private Label labelRareza ;
    private Label labelDescripcion ;
    private ListaCircular<Label> listaDetalles;

    public DescripcionCarta() {
        listaDetalles = new ListaCircular<>();
        labelNombre = new Label();
        labelAtributo = new Label();
        labelNivel = new Label();
        labelTipo = new Label();
        labelAtaque = new Label();
        labelDefensa = new Label();
        labelRareza = new Label();
        labelDescripcion = new Label();


        listaDetalles.insertaFin(labelNombre);
        listaDetalles.insertaFin(labelAtributo);
        listaDetalles.insertaFin(labelNivel);
        listaDetalles.insertaFin(labelTipo);
        listaDetalles.insertaFin(labelAtaque);
        listaDetalles.insertaFin(labelDefensa);
        listaDetalles.insertaFin(labelRareza);
        listaDetalles.insertaFin(labelDescripcion);


    }

    public void actualizarDetalles(String nombre, String atributo, String nivel, String tipo, String ataque,
                                   String defensa, String rareza, String descripcion) {
        labelNombre.setText(nombre);
        labelAtributo.setText(atributo);
        labelNivel.setText(nivel);
        labelTipo.setText(tipo);
        labelAtaque.setText(ataque);
        labelDefensa.setText(defensa);
        labelRareza.setText(rareza);
        labelDescripcion.setText(descripcion);
    }

    public VBox crearContenedor() {
        VBox contenedorDescripcion = new VBox();

        contenedorDescripcion.getChildren().addAll(labelNombre, labelAtributo, labelNivel, labelTipo,
                labelAtaque, labelDefensa, labelRareza, labelDescripcion);

        return contenedorDescripcion;
    }

    public void meterEstilos() {

        while (listaDetalles.eliminaFin() != null) {
            Label labelActual = listaDetalles.eliminaFin();
            labelActual.getStyleClass().add("label");
        }


    }
}