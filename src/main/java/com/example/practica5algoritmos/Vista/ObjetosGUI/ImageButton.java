package com.example.practica5algoritmos.Vista.ObjetosGUI;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class ImageButton extends Button {
    private int alto;
    private int ancho;
    private Image imagen;
    /**
     * Crea un boton el cual es representado por una imagen
     * @param rutaImagen Ruta a la imagen, sea un archivo .png, .jpg, .jpeg, etc.
     * @param alto Altura de la imagen
     * @param ancho Anchura de la imagen
     */
    public ImageButton(String rutaImagen, int alto, int ancho) {
        super();
        this.alto = alto;
        this.ancho = ancho;
        try {
            imagen = new Image(getClass().getResource(rutaImagen).toExternalForm());
            ImageView iconView = new ImageView(imagen);

            iconView.setFitHeight(alto);
            iconView.setFitWidth(ancho);
            iconView.setPreserveRatio(true);

            setBackground(Background.fill(Color.TRANSPARENT));
            setGraphic(iconView);
            setCursor(Cursor.HAND);
        } catch (RuntimeException e) {
            System.out.println("No se pudo cargar la imagen con esta ruta:" + rutaImagen);
        }
        getStyleClass().add("boton-imagen");

    }
    public ImageButton(String texto, String rutaImagen) {
        super(texto);
        ancho = 40;
        alto = 40;
        try {
            imagen = new Image(getClass().getResource(rutaImagen).toExternalForm());
            ImageView iconView = new ImageView(imagen);

            iconView.setFitHeight(alto);
            iconView.setFitWidth(ancho);
            iconView.setPreserveRatio(true);

            setBackground(Background.fill(Color.TRANSPARENT));
            setGraphic(iconView);
            setCursor(Cursor.HAND);
        } catch (RuntimeException e) {
            System.out.println("No se pudo cargar la imagen con esta ruta:" + rutaImagen);
        }
        getStyleClass().add("boton-imagen");
    }
    public ImageButton() {
        super("PLACEHOLDER/NO USAR");
        String rutaPlaceholder = "/recursos/iconos/placeholder.png";
        try {
            imagen = new Image(getClass().getResource(rutaPlaceholder).toExternalForm());
            ImageView iconView = new ImageView(imagen);

            iconView.setFitHeight(alto);
            iconView.setFitWidth(ancho);
            iconView.setPreserveRatio(true);

            setBackground(Background.fill(Color.TRANSPARENT));
            setGraphic(iconView);
            setCursor(Cursor.HAND);
        } catch (RuntimeException e) {
            System.out.println("No se pudo cargar la imagen con esta ruta: " + rutaPlaceholder);
        }
        getStyleClass().add("boton-imagen");
    }

    public void cambiarImagen(String nuevaRuta) {

        try {
            imagen = new Image(getClass().getResource(nuevaRuta).toExternalForm());
            ImageView iconoNuevo = new ImageView(imagen);

            iconoNuevo.setFitHeight(alto);
            iconoNuevo.setFitWidth(ancho);
            iconoNuevo.setPreserveRatio(true);

            setBackground(Background.fill(Color.TRANSPARENT));
            setGraphic(iconoNuevo);
            setCursor(Cursor.HAND);
//            setStyle("-fx-cursor: hand");
        } catch (RuntimeException e) {
            System.out.println("No se pudo cargar la imagen con esta ruta:" + nuevaRuta);
        }

    }

    public Image getImagen() {
        return imagen;
    }
}
