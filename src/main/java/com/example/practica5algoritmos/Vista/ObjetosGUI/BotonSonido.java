package com.example.practica5algoritmos.Vista.ObjetosGUI;

import com.example.practica5algoritmos.Modelo.Listas.ListaCircular;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;

public class BotonSonido extends ImageButton {
    private static final String ICONO_SONANDO = "/recursos/iconos/sonidoActivado.png";
    private static final String ICONO_SILENCIO = "/recursos/iconos/silencio.png";
    private static final double VOLUMEN = 0.35;

    private ListaCircular<String> rutasCanciones;
    private MediaPlayer reproductor;
    private Timeline fadeout;
    private boolean sonando;

    /**
     * Crea el botón de la bocina e inicia la música de fondo en bucle.
     * @param rutasCanciones Ruta del recurso de audio (mp3, wav o aac), por ejemplo "/recursos/audio/musica.mp3"
     */
    public BotonSonido(ListaCircular<String> rutasCanciones) {
        super(ICONO_SONANDO, 32, 32);
        getStyleClass().add("boton-sonido");
        this.rutasCanciones = rutasCanciones;
        setOnAction(e -> alternar());

        if (rutasCanciones == null || rutasCanciones.isEmpty()) {
            System.out.println("Musicas no inicializadas.");
            setDisable(true);
            return;
        }

        rutasCanciones.retornarAlInicio();
        sonando = true;
        cargarCancionActual();
    }

    private void cargarCancionActual() {
        for (int i = rutasCanciones.getTamano(); i > 0; i--) {
            if (cargarMusica(rutasCanciones.peekActual())) {
                if (sonando) reproductor.play();
                return;
            }
            rutasCanciones.avanzar();
        }
        System.out.println("No se pudo cargar ninguna cancion.");
        sonando = false;
        setDisable(true);
    }

    private boolean cargarMusica(String rutaMusica) {
        try {
            Media media = new Media(getClass().getResource(rutaMusica).toExternalForm());
            MediaPlayer nuevo = new MediaPlayer(media);
            nuevo.setOnEndOfMedia(this::siguienteCancion);
            nuevo.setVolume(VOLUMEN);
            nuevo.setOnError(() ->
                    System.out.println("Error de reproducción: " + nuevo.getError()));

            nuevo.currentTimeProperty().addListener((obs, antes, ahora) -> revisarFadeout(nuevo, ahora));

            liberarReproductor();
            reproductor = nuevo;
            return true;
        } catch (RuntimeException e) {
            System.out.println("No se pudo cargar la música con esta ruta: " + rutaMusica);
            return false;
        }
    }

    private void revisarFadeout(MediaPlayer jugador, Duration tiempoActual) {
        if (fadeout != null) return;   // ya está en curso

        Duration total = jugador.getTotalDuration();
        if (total == null || total.isUnknown() || total.isIndefinite()) return;
        if (total.toSeconds() <= 3 * 2) return;

        double restante = total.subtract(tiempoActual).toSeconds();
        if (restante <= 3) {
            bajarVolumenLentamente(jugador, restante);
        }
    }

    public void bajarVolumenLentamente(MediaPlayer mediaPlayer, double duracion) {
        fadeout = new Timeline(
                new KeyFrame(Duration.seconds(duracion),
                        new KeyValue(mediaPlayer.volumeProperty(), 0))
        );
        fadeout.play();
    }

    public void liberarReproductor() {
        if (fadeout != null) {
            fadeout.stop();
            fadeout = null;
        }
        if (reproductor != null) {
            reproductor.stop();
            reproductor.dispose();
            reproductor = null;
        }
    }

    public void siguienteCancion() {
        if (rutasCanciones == null || rutasCanciones.isEmpty()) return;
        rutasCanciones.avanzar();
        cargarCancionActual();
    }

    public void alternar() {
        if (reproductor == null) return;
        if (sonando) {
            reproductor.pause();
            if (fadeout != null) fadeout.pause();
            cambiarImagen(ICONO_SILENCIO);
        } else {
            reproductor.play();
            if (fadeout != null) fadeout.play();
            cambiarImagen(ICONO_SONANDO);
        }
        sonando = !sonando;
    }

    public boolean isSonando() {
        return sonando;
    }


    public void detener() {
        liberarReproductor();
        sonando = false;
    }
}