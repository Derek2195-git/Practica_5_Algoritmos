package com.example.practica5algoritmos.Modelo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class LectorCSV {
    private final int COLUMNAS = 10;
    private final int NOMBRE = 0, ATRIBUTO = 1, NIVEL = 2, TIPO = 3, ATK = 4, DEF = 5,
                rareza = 6, DESCRIPCION = 7, IMAGEN = 9;

    public CartaYGO[] leerCartas(String ruta) throws IOException {
        ArrayList<String[]> filas = leerFilas(ruta);
        ArrayList<CartaYGO> cartas = new ArrayList<>();
        for (int i = 1; i < filas.size(); i++) {
            String[] celdas = filas.get(i);
            if (celdas.length != COLUMNAS) {
                System.out.println("Fila " + i + " omitida: " + celdas.length + " columnas");
                continue;
            }
            try {
                cartas.add(filaACarta(celdas));
            } catch (NumberFormatException e) {
                System.out.println("Error al abrir el CSV: " + e.getMessage());
            }
        }
        return cartas.toArray(new CartaYGO[0]);
    }

    private ArrayList<String[]> leerFilas(String ruta) throws IOException {
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

    private int convertirValoresNumericos(String texto) {
        String limpio = texto.trim();
        return limpio.equalsIgnoreCase("N/A") ?
                -1 : Integer.parseInt(limpio);
    }


}
