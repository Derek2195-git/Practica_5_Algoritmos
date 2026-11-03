package com.example.practica5algoritmos.Modelo;

import java.util.Comparator;

public class CartaYGO {
    private String nombre;
    private String atributo;
    private int nivel;
    private String tipo;
    private int ataque;
    private int defensa;
    private String rareza;
    private String descripcion;
    private String imagen;

    public static final Comparator<CartaYGO> POR_NOMBRE =
            Comparator.comparing(CartaYGO::getNombre, String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<CartaYGO> POR_ATAQUE =
            Comparator.comparingInt(CartaYGO::getAtaque).thenComparing(POR_NOMBRE);
    public static final Comparator<CartaYGO> POR_DEFENSA =
            Comparator.comparingInt(CartaYGO::getDefensa).thenComparing(POR_NOMBRE);

    public CartaYGO(String nombre, String atributo, int nivel,
                    String tipo, int ataque, int defensa,
                    String rareza, String descripcion, String imagen) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.nivel = nivel;
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.rareza = rareza;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public boolean tieneDefensa() {
        return defensa != -1;
    }

    public boolean esMonstruo() {
        return nivel != -1;
    }

    public boolean esLink() {
        return tipo.contains("Link");
    }

    public boolean esXYZ() {
        return tipo.contains("Xyz");
    }

    public String getNombre() {
        return nombre;
    }

    public String getAtributo() {
        return atributo;
    }

    public int getNivel() {
        return nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public String getRareza() {
        return rareza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEtiquetaNivel() {
        if (!esMonstruo()) return "N/A";
        if (esLink()) return "Link " + nivel;
        if (esXYZ()) return "Rank " + nivel;
        return "Nivel " + nivel;
    }

    @Override
    public String toString() {
        return nombre + " (" + atributo + ", " + getEtiquetaNivel()
                + ", ATK " + ataque + ", DEF " + defensa + ")";
    }
}
