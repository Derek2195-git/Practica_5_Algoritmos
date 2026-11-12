package com.example.practica5algoritmos.Modelo;

public class Nodo<T> {
    private T info;
    private Nodo<T> siguiente;
    private Nodo<T> ant;

    public Nodo(T info) {
        ant = null;
        this.info = info;
        siguiente = null;
    }

    public T getInfo() {
        return info;
    }

    public Nodo<T> getAnt() {
        return ant;
    }

    public void setAnt(Nodo ant) { this.ant = ant; }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "Objeto en la lista: " + getInfo() +
                "Dirección del siguiente objeto: " + getSiguiente();
    }

}
