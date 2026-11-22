package com.example.practica5algoritmos.Modelo.LibreriaCartas;

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

    /**
     * Constructor de una carta de Yu-Gi-Oh!
     * @param nombre Nombre de la carta
     * @param atributo Atributo elemental que posee la carta
     * @param nivel Nivel que tiene la carta, en caso de que no posea uno, se debe asignar -1
     * @param tipo Tipo que posea la carta, esta sección es denotada por corchetes "[]"
     * @param ataque Ataque que posee la carta, en caso de no tener, se debe asignar -1
     * @param defensa Defensa que posee la carta, en caso de no tener, se debe asignar -1
     * @param rareza Rareza que posee la carta
     * @param descripcion Descripción de los efectos de la carta
     * @param imagen Ruta a la imagen donde se ubica la carta
     */
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


    /**
     * Metodo que revisa si una carta tiene defensa
     * @return booleano que indica si la carta no es una trampa o hechizo
     */
    public boolean tieneDefensa() {
        return defensa != -1;
    }

    /**
     * Metodo que revisa si una carta tiene ataque
     * @return booleano que indica si la carta no es una trampa o hechizo
     */
    public boolean esMonstruo() {
        return nivel != -1;
    }

    /**
     * Getter del nombre de la carta
     * @return Nombre de la carta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del atributo de la carta
     * @return Atributo de la carta
     */
    public String getAtributo() {
        return atributo;
    }

    /**
     * Getter del nivel de la carta
     * @return Nivel que posee la carta
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Getter del tipo de la carta
     * @return Tipos que posee la carta
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Getter del ataque de la carta
     * @return Puntos de ataque de la carta
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Getter de la defensa de la carta
     * @return Puntos de defensa de la carta
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Getter de la rareza de la carta
     * @return Rareza de la carta
     */
    public String getRareza() {
        return rareza;
    }

    /**
     * Getter de la descripción de la carta
     * @return Descripción de la carta
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Getter de la ruta donde se ubica la imagen de la carta
     * @return String que denota el id de la carta, la cual es usada para mostrarla en la vista
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Metodo que compara el ataque de una carta con otra
     * @param carta Carta la cual se comparará
     * @param o Carta a comparar con la primera
     * @return Valor entero que indica si la primera carta es menor, igual o mayor que la otra
     */
    public static int compararAtaque(CartaYGO carta, CartaYGO o) {
        int resultado = Integer.compare(carta.getAtaque(), o.getAtaque());
        return resultado != 0 ? resultado : carta.compareTo(o);   // Por nombre si son iguales
    }

    /**
     * Metodo que compara la defensa de una carta con otra
     * @param carta Carta la cual se comparará
     * @param o Carta a comparar con la primera
     * @return Valor entero que indica si la primera carta es menor, igual o mayor que la otra
     */
    public static int compararDefensa(CartaYGO carta, CartaYGO o) {
        int resultado = Integer.compare(carta.getDefensa(), o.getDefensa());
        return resultado != 0 ? resultado : carta.compareTo(o); // Por nombre si son iguales
    }

    /**
     * Metodo toString de la carta
     * @return Cadena que contiene el nombre, atributo, nivel, ataque y defensa de la carta
     */
    @Override
    public String toString() {
        return nombre + " (" + atributo + ", " + nivel
                + ", ATK " + ataque + ", DEF " + defensa + ")";
    }

    /**
     * Metodo compareTo de la carta
     * @param o Carta a comparar con la que poseemos
     * @return Valor entero que indica si la carta poseída es menor, igual o mayor que la comparada
     */
    @Override
    public int compareTo(CartaYGO o) {
        return nombre.compareToIgnoreCase(o.nombre);
    }


}
