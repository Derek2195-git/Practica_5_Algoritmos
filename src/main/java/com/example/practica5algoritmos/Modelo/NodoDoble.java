package com.example.practica5algoritmos.Modelo;

public class NodoDoble<T> {
    private T info;
    private NodoDoble<T> siguiente;
    private NodoDoble<T> ant;

    public NodoDoble(T info) {
        ant = null;
        this.info = info;
        siguiente = null;
    }

    public T getInfo() {
        return info;
    }

    public NodoDoble<T> getAnt() {
        return ant;
    }

    public void setAnt(NodoDoble<T> ant) { this.ant = ant; }

    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "Objeto en la lista: " + getInfo() +
                "Dirección del siguiente objeto: " + getSiguiente();
    }

}

