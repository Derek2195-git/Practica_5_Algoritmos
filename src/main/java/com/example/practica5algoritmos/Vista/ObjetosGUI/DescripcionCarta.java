package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.CartaYGO;
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
    private final Label lblNombre = new Label();
    private final Label lblRareza = new Label();
    private final Label lblAtributo = new Label();
    private final Label lblNivel = new Label();
    private final Label lblTipo = new Label();
    private final Label lblAtaque = new Label();
    private final Label lblDefensa = new Label();
    private final Label lblTexto = new Label();
    private String claseAtributo;

    public DescripcionCarta() {
        super(10);
        setPadding(new Insets(16));
        getStyleClass().add("descripcion-carta");

        lblNombre.getStyleClass().add("descripcion-nombre");
        lblNombre.setWrapText(true);
        lblRareza.getStyleClass().add("descripcion-rareza");
        HBox encabezado = new HBox(8, lblNombre, nuevoEspaciador(), lblRareza);
        encabezado.setAlignment(Pos.CENTER_LEFT);

        lblAtributo.getStyleClass().add("chip");
        lblNivel.getStyleClass().add("chip");
        lblTipo.getStyleClass().add("descripcion-tipo");
        lblAtaque.getStyleClass().add("stat");
        lblDefensa.getStyleClass().add("stat");
        HBox datos = new HBox(10, lblAtributo, lblNivel, lblTipo, nuevoEspaciador(), lblAtaque, lblDefensa);
        datos.setAlignment(Pos.CENTER_LEFT);

        lblTexto.getStyleClass().add("descripcion-texto");
        lblTexto.setWrapText(true);
        ScrollPane scroll = new ScrollPane(lblTexto);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.getStyleClass().add("descripcion-scroll");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        getChildren().addAll(encabezado, new Separator(), datos, scroll);
        limpiar();
    }

    /** Muestra los datos de la carta. Si es null limpia el panel. */
    public void mostrar(CartaYGO c) {
        if (c == null) {
            limpiar();
            return;
        }
        lblNombre.setText(c.getNombre());

        lblRareza.setText(c.getRareza());
        mostrarSi(lblRareza, !c.getRareza().isBlank());

        String atributo = c.getAtributo();
        lblAtributo.setText(atributo);
        actualizarClaseAtributo(atributo);
        mostrarSi(lblAtributo, !atributo.isBlank() && !atributo.equalsIgnoreCase("N/A"));

        lblNivel.setText(c.getEtiquetaNivel());
        mostrarSi(lblNivel, c.esMonstruo());

        lblTipo.setText(c.getTipo());
        mostrarSi(lblTipo, !c.getTipo().isBlank());

        lblAtaque.setText("ATK " + c.getAtaque());
        mostrarSi(lblAtaque, c.getAtaque() >= 0);

        lblDefensa.setText("DEF " + c.getDefensa());
        mostrarSi(lblDefensa, c.tieneDefensa());   // las Link no tienen DEF

        lblTexto.setText(c.getDescripcion());
    }

    public void limpiar() {
        lblNombre.setText("");
        lblTexto.setText("");
        for (Node n : new Node[]{lblRareza, lblAtributo, lblNivel, lblTipo, lblAtaque, lblDefensa}) {
            mostrarSi(n, false);
        }
    }

    private void actualizarClaseAtributo(String atributo) {
        if (claseAtributo != null) lblAtributo.getStyleClass().remove(claseAtributo);
        claseAtributo = "atributo-" + atributo.toLowerCase().replaceAll("[^a-z]", "");
        lblAtributo.getStyleClass().add(claseAtributo);
    }

    private void mostrarSi(Node nodo, boolean visible) {
        nodo.setVisible(visible);
        nodo.setManaged(visible);
    }

    private Region nuevoEspaciador() {
        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);
        return espacio;
    }
}