package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.CartaYGO;
import com.example.practica5algoritmos.Modelo.TipoVista;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;

public class CartaGUI extends VBox {
    private static final double PROPORCION = 614.0 / 421.0;   // alto/ancho de una carta de Yu-Gi-Oh!
    private static final String CARPETA_IMAGENES = "/recursos/cartas/";
    private static final HashMap<String, Image> CACHE = new HashMap<>();

    private final double ancho;
    private final ImageView vistaImagen = new ImageView();
    private final Label lblNombre = new Label();
    private final Label lblDato = new Label();
    private CartaYGO carta;

    /**
     * @param ancho      Ancho en pixeles de la imagen de la carta (el alto se calcula solo)
     * @param esControl  true para las cartas pequeñas que sirven de control (cursor de mano)
     */
    public CartaGUI(double ancho, boolean esControl) {
        super(6);
        this.ancho = ancho;
        setAlignment(Pos.CENTER);
        getStyleClass().add(esControl ? "carta-control" : "carta-principal");
        if (esControl) setCursor(Cursor.HAND);

        vistaImagen.setFitWidth(ancho);
        vistaImagen.setPreserveRatio(true);
        vistaImagen.setSmooth(true);

        // Fondo que se ve mientras carga la imagen o si no se pudo cargar
        Region fondo = new Region();
        fondo.getStyleClass().add("carta-placeholder");
        StackPane marco = new StackPane(fondo, vistaImagen);
        double alto = ancho * PROPORCION;
        marco.setMinSize(ancho, alto);
        marco.setPrefSize(ancho, alto);
        marco.setMaxSize(ancho, alto);

        lblNombre.getStyleClass().add("carta-nombre");
        lblNombre.setFont(Font.font("System", FontWeight.BOLD, esControl ? 12 : 20));
        lblNombre.setWrapText(true);
        lblNombre.setTextAlignment(TextAlignment.CENTER);
        lblNombre.setAlignment(Pos.TOP_CENTER);
        lblNombre.setMaxWidth(ancho);
        lblNombre.setMinHeight(esControl ? 34 : 54);   // reserva 2 líneas para que el layout no salte

        lblDato.getStyleClass().add("carta-dato");
        lblDato.setFont(Font.font("System", FontWeight.BOLD, esControl ? 12 : 16));

        getChildren().addAll(marco, lblNombre, lblDato);
    }

    /**
     * Muestra la carta junto con el dato que corresponde a la vista activa.
     * Si la carta es null (lista vacía) limpia el componente.
     */
    public void mostrar(CartaYGO nueva, TipoVista modo) {
        if (nueva == null) {
            limpiar();
            return;
        }
        boolean cambioCarta = nueva != carta;
        carta = nueva;

        vistaImagen.setImage(cargarImagen(nueva.getImagen()));
        lblNombre.setText(nueva.getNombre());
        lblDato.setText(textoDato(nueva, modo));
        setVisible(true);

        if (cambioCarta) {
            FadeTransition fade = new FadeTransition(Duration.millis(200), vistaImagen);
            fade.setFromValue(0.15);
            fade.setToValue(1.0);
            fade.play();
        }
    }

    public void limpiar() {
        carta = null;
        vistaImagen.setImage(null);
        lblNombre.setText("");
        lblDato.setText("");
        setVisible(false);   // sigue ocupando espacio para que el layout no se mueva
    }

    public CartaYGO getCarta() {
        return carta;
    }

    private String textoDato(CartaYGO c, TipoVista modo) {
        switch (modo) {
            case NIVEL:    return c.getEtiquetaNivel();
            case ATRIBUTO: return c.getAtributo();
            case ATAQUE:   return "ATK " + valor(c.getAtaque());
            case DEFENSA:  return "DEF " + valor(c.getDefensa());
            default:       return c.getTipo();   // TODAS y HECHIZOS_TRAMPAS
        }
    }

    private String valor(int numero) {
        return numero < 0 ? "N/A" : String.valueOf(numero);
    }

    private Image cargarImagen(String ruta) {
        String url = resolverUrl(ruta);
        if (url == null) return null;

        String clave = url + "@" + ancho;
        Image imagen = CACHE.get(clave);
        if (imagen == null) {
            // Se carga al doble de tamaño para que se vea nítida en pantallas HiDPI
            imagen = new Image(url, ancho * 2, 0, true, true, true);
            imagen.errorProperty().addListener((obs, antes, error) -> {
                if (error) CACHE.remove(clave);   // para reintentar si falló la red
            });
            CACHE.put(clave, imagen);
        }
        return imagen;
    }

    /** Acepta URLs completas (http, https, file, jar) o nombres/rutas de recursos locales. */
    private String resolverUrl(String ruta) {
        if (ruta == null || ruta.isBlank()) return null;
        if (ruta.matches("(?i)^(https?|file|jar):.*")) return ruta;

        String recurso = ruta.startsWith("/") ? ruta : CARPETA_IMAGENES + ruta;
        URL url = getClass().getResource(recurso);
        return url == null ? null : url.toExternalForm();
    }
}