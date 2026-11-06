package com.example.practica5algoritmos.Vista.ObjetosGUI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BotonSonido extends ImageButton {
    private static final String ICONO_SONANDO = "/recursos/iconos/sonido.png";
    private static final String ICONO_SILENCIO = "/recursos/iconos/silencio.png";
    private static final double VOLUMEN = 0.35;

    private MediaPlayer reproductor;   // se guarda como campo para que el GC no lo detenga
    private boolean sonando;

    /**
     * Crea el botón de la bocina e inicia la música de fondo en bucle.
     * @param rutaMusica Ruta del recurso de audio (mp3, wav o aac), por ejemplo "/recursos/audio/musica.mp3"
     */
    public BotonSonido(String rutaMusica) {
        super(ICONO_SONANDO, 32, 32);
        getStyleClass().add("boton-sonido");
        cargarMusica(rutaMusica);
        setOnAction(e -> alternar());
    }

    private void cargarMusica(String rutaMusica) {
        try {
            Media media = new Media(getClass().getResource(rutaMusica).toExternalForm());
            reproductor = new MediaPlayer(media);
            reproductor.setCycleCount(MediaPlayer.INDEFINITE);
            reproductor.setVolume(VOLUMEN);
            reproductor.setOnError(() ->
                    System.out.println("Error de reproducción: " + reproductor.getError()));
            reproductor.play();
            sonando = true;
        } catch (RuntimeException e) {   // NPE si no existe el recurso, MediaException si el formato no es válido
            System.out.println("No se pudo cargar la música con esta ruta: " + rutaMusica);
            setDisable(true);
        }
    }

    /** Pausa la música si está sonando, o la reanuda si estaba pausada. */
    public void alternar() {
        if (reproductor == null) return;
        if (sonando) {
            reproductor.pause();
            cambiarImagen(ICONO_SILENCIO);
        } else {
            reproductor.play();
            cambiarImagen(ICONO_SONANDO);
        }
        sonando = !sonando;
    }

    public boolean isSonando() {
        return sonando;
    }

    /** Libera el reproductor. Conviene llamarlo al cerrar la ventana. */
    public void detener() {
        if (reproductor != null) {
            reproductor.stop();
            reproductor.dispose();
        }
    }
}