package com.example.practica5algoritmos.Modelo.Listas;

public class Nodo<T> {
    private T info;
    private Nodo<T> siguiente;
    public Nodo(T info) {
        this.info = info;
        siguiente = null;
    }

    public T getInfo() {
        return info;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
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
