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

    }
}