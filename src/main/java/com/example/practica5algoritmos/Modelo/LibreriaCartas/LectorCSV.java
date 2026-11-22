package com.example.practica5algoritmos.Modelo.LibreriaCartas;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class LectorCSV {
    private final int COLUMNAS = 10;
    private final int NOMBRE = 0, ATRIBUTO = 1, NIVEL = 2, TIPO = 3, ATK = 4, DEF = 5,
                rareza = 6, DESCRIPCION = 7, IMAGEN = 9;
    String ruta = "/recursos/cartas_yugioh.csv";

    /**
     * Constructor del gestor y manejador del archivo csv de cartas
     * @return ArrayList con todas las cartas del archivo csv obtenidas
     * @throws IOException Excepción de entrada y salida en caso de que no se encuentre el archivo
     */
    public ArrayList<CartaYGO> leerCartas() throws IOException {
        ArrayList<String[]> filas = leerFilas();
        ArrayList<CartaYGO> cartas = new ArrayList<>();
        for (int i = 1; i < filas.size(); i++) {
            String[] celdas = filas.get(i);
            if (celdas.length != COLUMNAS) {
                System.err.println("Fila " + i + " omitida: " + celdas.length + " columnas en vez de " + COLUMNAS);
                continue;
            }
            try {
                cartas.add(filaACarta(celdas));
            } catch (NumberFormatException e) {
                System.err.println("Fila " + i + " omitida (" + celdas[NOMBRE] + "): " + e.getMessage());
            }
        }
        return cartas;
    }

    /**
     * Metodo que lee una fila del csv
     * @return ArrayList de arreglos de tipo string que indican todas las filas del archivo csv
     * @throws IOException Excepción de entrada y salida en caso de que no se encuentre el archivo
     */
    private ArrayList<String[]> leerFilas() throws IOException {
        InputStream in = LectorCSV.class.getResourceAsStream(ruta);
        if (in == null) throw new IOException("No se encontró el recurso " + ruta);

        ArrayList<String[]> filas = new ArrayList<>();
        try (
                BufferedReader lector = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))
                ) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.isBlank()) {
                    filas.add(linea.split(",", -1));
                }
            }
        }
        return filas;
    }

    /**
     * Metodo que lee una fila y la convierte a una carta
     * @param c Fila a convertir
     * @return Carta con los valores dados de la fila
     */
    private CartaYGO filaACarta(String[] c) {
        return new CartaYGO(
                c[NOMBRE].trim(),
                c[ATRIBUTO].trim(),
                convertirValoresNumericos(c[NIVEL]),
                c[TIPO].trim(),
                convertirValoresNumericos(c[ATK]),
                convertirValoresNumericos(c[DEF]),
                c[rareza].trim(),
                c[DESCRIPCION].trim(),
                c[IMAGEN].trim()
        );
    }

    /**
     * Metodo que convierte un texto a un valor númerico, empleado para los atributos de nivel, ataque y defensa
     * @param texto Cadena a convertir
     * @return Valor entero que puede tomar el valor dado, o un -1 en caso de que dicho número no estuviera disponible
     */
    private int convertirValoresNumericos(String texto) {
        String limpio = texto.trim();
        return limpio.equalsIgnoreCase("N/A") ?
                -1 : Integer.parseInt(limpio);
    }


}
