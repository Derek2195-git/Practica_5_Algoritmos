package com.example.practica5algoritmos.Vista;

import com.example.practica5algoritmos.Modelo.Listas.ListaCircular;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class VentanaFiltros {

    private String sin_nivel = "N/A";
    private final int NIVEL_MAXIMO = 12;

    private double separacion = 10;
    private double ancho_estimado = 280;

    private Button botonTodos;
    private Button botonNiveles;
    private Button botonAtributos;
    private Button botonAtaque;
    private Button botonDefensa;

    private ComboBox<String> recuadroNiveles;
    private ComboBox<String> recuadroAtributos;
    private CheckBox checkAtaque;
    private CheckBox checkDefensa;

    private GridPane contenedor;
    private Stage ventana;

    public VentanaFiltros() {
        botonTodos = crearBoton("Mostrar todos");
        botonNiveles = crearBoton("Mostrar por Niveles");
        botonAtributos = crearBoton("Mostrar por Atributos");
        botonAtaque = crearBoton("Mostrar por Ataque");
        botonDefensa = crearBoton("Mostrar por Defensa");
        
        
        recuadroNiveles = new ComboBox<>();
        for (int i = 1; i <= NIVEL_MAXIMO; i++) {
            recuadroNiveles.getItems().add(String.valueOf(i));
        }
        recuadroNiveles.getItems().add(sin_nivel);
        recuadroNiveles.getSelectionModel().selectFirst();
        recuadroNiveles.setMaxWidth(Double.MAX_VALUE);

        recuadroAtributos = new ComboBox<>();
        recuadroAtributos.setPromptText("Elige atributo");
        recuadroAtributos.setMaxWidth(Double.MAX_VALUE);

        checkAtaque = new CheckBox("Menor a mayor");
        checkDefensa = new CheckBox("Menor a mayor");


        contenedor = new GridPane();
        contenedor.setHgap(12);
        contenedor.setVgap(10);
        contenedor.setPadding(new Insets(16));

        agregarFila(0, botonTodos, null);
        agregarFila(1, botonNiveles, recuadroNiveles);
        agregarFila(2, botonAtributos, recuadroAtributos);
        agregarFila(3, botonAtaque, checkAtaque);
        agregarFila(4, botonDefensa, checkDefensa);


    }

    public Button crearBoton(String nombreBoton) {
        Button boton = new Button(nombreBoton);
        boton.getStyleClass().add("boton-filtro");
        boton.setMaxWidth(Double.MAX_VALUE);
        return boton;
    }

    public void agregarFila(int fila, Button boton, Node control) {
        contenedor.add(boton, 0, fila);
        GridPane.setValignment(boton, VPos.CENTER);
        if (control != null) {
            contenedor.add(control, 1, fila);
            GridPane.setValignment(control, VPos.CENTER);
        }
    }

    public void cargarAtributos(List<String> atributos) {
        recuadroAtributos.getItems().setAll(atributos);
        if (!atributos.isEmpty()) {
            recuadroAtributos.getSelectionModel().selectFirst();
        }
    }

    public String getNivelSeleccionado() {
        return recuadroNiveles.getValue();
    }

    public String getAtributoSeleccionado() {
        return recuadroAtributos.getValue();
    }

    public boolean isAtaqueAscendente() {
        return checkAtaque.isSelected();
    }

    public boolean isDefensaAscendente() {
        return checkDefensa.isSelected();
    }

    public void abrirVentana(Window ventanaPrincipal) {
        if (ventana == null) {
            crearVentana(ventanaPrincipal);
        }
        if (!ventana.isShowing()) {
            ventana.show();
        }
    }

    public boolean ventanaEstaAbierta() {
        return ventana != null && ventana.isShowing();
    }

    public void crearVentana(Window ventanaPrincipal) {
        Scene escena = new Scene(contenedor);
        escena.getStylesheets().add(getClass().getResource("/recursos/estilos.css").toExternalForm());


        ventana = new Stage();
        ventana.initOwner(ventanaPrincipal);
        ventana.setTitle("Filtros de visuaizacon");
        ventana.setResizable(false);
        ventana.setScene(escena);

        Rectangle2D pantalla = Screen.getPrimary().getVisualBounds();
        double x = ventanaPrincipal.getX() + ventanaPrincipal.getWidth() + separacion;
        if (x + ancho_estimado > pantalla.getMaxX()) {
            x = pantalla.getMaxX() - ancho_estimado;
        }
        ventana.setX(x);
        ventana.setY(ventanaPrincipal.getY());
    }

    public void alMostrarTodos(Runnable accion) {
        botonTodos.setOnAction(e -> accion.run());
    }

    public void alMostrarPorNiveles(Runnable accion) {
        botonNiveles.setOnAction(e->accion.run());
    }

    public void alMostrarPorAtributos(Runnable accion) {
        botonAtributos.setOnAction(e-> accion.run());
    }

    public void alMostrarPorAtaque(Runnable accion){
        botonAtaque.setOnAction(e->accion.run());
    }

    public void alMostrarPorDefensa(Runnable accion) {
        botonDefensa.setOnAction(e-> accion.run());
    }
}
