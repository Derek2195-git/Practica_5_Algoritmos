package com.example.practica5algoritmos.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class LibreriaYGO {
    private ListaDobleCircular<CartaYGO> listaCartas, listaHechizosTrampas, listaAtaque, listaDefensa;
    private HashMap<Integer, ListaDobleCircular<CartaYGO>> porNivel;
    private HashMap<String, ListaDobleCircular<CartaYGO>> porAtributo;
    private boolean listaAtaqueAscendente = true;
    private boolean listaDefensaAscendente = true;
    private ListaDobleCircular<CartaYGO> listaActiva;
    private TipoVista tipoDeVista;

    public LibreriaYGO(ArrayList<CartaYGO> cartas){
        ArrayList<CartaYGO> cartasOrdenadas = new ArrayList<>(cartas);
        Collections.sort(cartasOrdenadas);

        ArrayList<CartaYGO> hechizosTrampas = new ArrayList<>();
        ArrayList<CartaYGO> monstruosPorAtaque = new ArrayList<>();
        ArrayList<CartaYGO> monstruosPorDefensa = new ArrayList<>();
        porNivel = new HashMap<>();
        porAtributo = new HashMap<>();

        for (CartaYGO carta : cartasOrdenadas) {
            if (!carta.esMonstruo()) {
                hechizosTrampas.add(carta);
                continue;
            }
            porNivel.computeIfAbsent(carta.getNivel(), k -> new ListaDobleCircular<>()).insertaFin(carta);
            porAtributo.computeIfAbsent(carta.getAtributo(), k -> new ListaDobleCircular<>()).insertaFin(carta);
            monstruosPorAtaque.add(carta);
            if (carta.tieneDefensa()) monstruosPorDefensa.add(carta);   // los Link no tienen DEF
        }

        Collections.sort(monstruosPorAtaque, CartaYGO::compararAtaque);
        Collections.sort(monstruosPorDefensa, CartaYGO::compararDefensa);

        listaCartas = convertirAListaDC(cartasOrdenadas);
        listaHechizosTrampas = convertirAListaDC(hechizosTrampas);
        listaAtaque = convertirAListaDC(monstruosPorAtaque);
        listaDefensa = convertirAListaDC(monstruosPorDefensa);

        listaActiva = listaCartas;
        tipoDeVista = TipoVista.TODAS;

    }

    private ListaDobleCircular<CartaYGO> convertirAListaDC(ArrayList<CartaYGO> cartas) {
        ListaDobleCircular<CartaYGO> lista = new ListaDobleCircular<>();
        for (CartaYGO carta : cartas) {
            lista.insertaFin(carta);
        }
        return lista;
    }

    private void pasarListaAActiva(ListaDobleCircular<CartaYGO> lista, TipoVista nuevoModo) {
        lista.devolverActualAlInicio();
        listaActiva = lista;
        tipoDeVista = nuevoModo;
    }

    public void mostrarTodas() {
        pasarListaAActiva(listaCartas, TipoVista.TODAS);
    }

    public void mostrarHechizosTrampas() {
        pasarListaAActiva(listaHechizosTrampas, TipoVista.HECHIZOS_TRAMPAS);
    }

    public void mostrarPorNivel(int nivel) {
        ListaDobleCircular<CartaYGO> lista = porNivel.get(nivel);
        if (lista != null) pasarListaAActiva(lista, TipoVista.NIVEL);
    }

    public void mostrarPorAtributo(String atributo) {
        ListaDobleCircular<CartaYGO> lista = porAtributo.get(atributo);
        if (lista != null) pasarListaAActiva(lista, TipoVista.ATRIBUTO);
    }

    public void mostrarPorAtaque(boolean descendente) {
        if (descendente == listaAtaqueAscendente) {
            listaAtaque.invertirLista();
            listaAtaqueAscendente = !descendente;
        }
        pasarListaAActiva(listaAtaque, TipoVista.ATAQUE);
    }

    public void mostrarPorDefensa(boolean descendente) {
        if (descendente == listaDefensaAscendente) {
            listaDefensa.invertirLista();
            listaDefensaAscendente = !descendente;
        }
        pasarListaAActiva(listaDefensa, TipoVista.DEFENSA);
    }

    public void avanzar() {
        listaActiva.avanzarActual();
    }

    public void retroceder() {
        listaActiva.retrocederActual();
    }

    public CartaYGO getActual() {
        return listaActiva.peekActual();
    }

    public CartaYGO getSiguiente() {
        return listaActiva.peekSiguiente();
    }

    public CartaYGO getAnterior() {
        return listaActiva.peekAnterior();
    }

    public int getPosicion() {
        return listaActiva.getPosicionActual();
    }

    public int getTotal() {
        return listaActiva.getTamano();
    }

    public TipoVista getModo() {
        return tipoDeVista;
    }

    public ArrayList<Integer> getNivelesDisponibles() {
        ArrayList<Integer> niveles = new ArrayList<>(porNivel.keySet());
        Collections.sort(niveles);
        return niveles;
    }

    public ArrayList<String> getAtributosDisponibles() {
        ArrayList<String> atributos = new ArrayList<>(porAtributo.keySet());
        Collections.sort(atributos);
        return atributos;
    }






}
