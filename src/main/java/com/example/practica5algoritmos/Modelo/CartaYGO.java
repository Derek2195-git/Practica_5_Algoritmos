package com.example.practica5algoritmos.Modelo;

public class CartaYGO implements Comparable<CartaYGO> {
    private String nombre;
    private String atributo;
    private int nivel;
    private String tipo;
    private int ataque;
    private int defensa;
    private String rareza;
    private String descripcion;
    private String imagen;

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

    public String getImagen() {
        return imagen;
    }

    public String getEtiquetaNivel() {
        if (!esMonstruo()) return "N/A";
        if (esLink()) return "Link " + nivel;
        if (esXYZ()) return "Rank " + nivel;
        return "Nivel " + nivel;
    }

    public static int compararAtaque(CartaYGO carta, CartaYGO o) {
        int resultado = Integer.compare(carta.getAtaque(), o.getAtaque());
        return resultado != 0 ? resultado : carta.compareTo(o);   // empate: por nombre
    }

    public static int compararDefensa(CartaYGO carta, CartaYGO o) {
        int resultado = Integer.compare(carta.getDefensa(), o.getDefensa());
        return resultado != 0 ? resultado : carta.compareTo(o);
    }

    @Override
    public String toString() {
        return nombre + " (" + atributo + ", " + getEtiquetaNivel()
                + ", ATK " + ataque + ", DEF " + defensa + ")";
    }

    @Override
    public int compareTo(CartaYGO o) {
        return nombre.compareToIgnoreCase(o.nombre);
    }


}
