package com.example.practica5algoritmos.Modelo.Listas;

public class ListaCircular<T> {
    private Nodo<T> inicio;
    private Nodo<T> actual;
    private Nodo<T> fin;

    public ListaCircular() {
        inicio = fin = actual = null;
    }

    public void insertaInicio(T dato) {
        Nodo<T> n = new Nodo<>(dato);

        if (inicio == null) {
            inicio = fin = actual = n;
            n.setSiguiente(inicio);
        } else {
            n.setSiguiente(inicio);
            inicio = n;
            fin.setSiguiente(inicio);
        }

    }

    public void insertaFin(T dato) {
        Nodo<T> n = new Nodo<>(dato);
        if (inicio == null) {
            inicio = fin = actual = n;
            n.setSiguiente(inicio);
        } else {
            n.setSiguiente(inicio);
            fin.setSiguiente(n);
            fin = n;
        }
    }

    public T eliminaInicio(){
        if (inicio == null) {
            System.out.println("Lista vacia");
            return null;
        } else {
            T dato = inicio.getInfo();
            if (inicio == fin) {
                inicio = fin = actual = null;
            } else {
                fin.setSiguiente(inicio.getSiguiente());
                inicio = inicio.getSiguiente();
            }
            return dato;
        }
    }

    public T eliminaFin() {
        if (inicio == null) {
            System.out.println("Lista vacia");
            return null;
        } else {
            Nodo<T> dato = fin;
            if (inicio == fin) {
                inicio = fin = actual = null;
            } else {
                Nodo<T> r = inicio;
                while (r.getSiguiente() != fin) {
                    r = r.getSiguiente();
                }
                fin = r;
                if(actual == dato) actual = inicio;
                fin.setSiguiente(inicio);
            }
            return dato.getInfo();
        }

    }

    /*
        1. Mostrar la lista - gg
        2. Lo mismo pero recursivo - gg
        2.1 Buscar recursivo
        3. Eliminar por posición
        4. Eliminar dato x
        5. Insertar dato en la posicion ej. insertarPosicion(T dato, int posicion)
     */

    public String mostrarLista() {
        if (inicio == null) {
            return "La lista esta vacía";
        } else {
            Nodo<T> r = inicio;
            String cadena = "";
            do {
                cadena += r.getInfo() + ", ";
                r = r.getSiguiente();
            } while (r != inicio);
            return cadena;
        }
    }


    public String mostrarLista(Nodo<T> nodo) {
        if (nodo == null) {
            return "";
        } else if(nodo.getSiguiente() == inicio) {
            return nodo.getInfo() + "";
        } else {
            return nodo.getInfo() + ", " + mostrarLista(nodo.getSiguiente());
        }
    }

    public String mostrarListaRecursiva() {
        String cadena = mostrarLista(inicio);
        return cadena.isEmpty() ? "La lista esta vacia" : cadena;
    }

    public boolean buscar(Nodo<T> nodo, T info) {
        if (nodo == null) return false;
        else if (nodo.getInfo().equals(info)) return true;
        else if (nodo.getSiguiente() == inicio) return false;
        else return buscar(nodo.getSiguiente(), info);
    }

    public boolean buscarEnLaLista(T infoABuscar) {
        return buscar(inicio, infoABuscar);
    }

    public T eliminarPorPosicion(int posicion)  {
        if (posicion == 0) return eliminaInicio();
        else {
            if (inicio == null) return null;
            Nodo<T> r = inicio;
            for (int i = 0; i < posicion - 1; i++) {
                r = r.getSiguiente();
            }
            Nodo<T> nodoAEliminar = r.getSiguiente();
            T dato = nodoAEliminar.getInfo();
            r.setSiguiente(nodoAEliminar.getSiguiente());
            if (nodoAEliminar == fin) fin = r;
            return dato;
        }
    }

    public T eliminarDato(T datoAEliminar) {
        if (inicio == null) return null;

        if (inicio.getInfo().equals(datoAEliminar)) return eliminaInicio();

        Nodo<T> r = inicio;
        T dato = null;

        while (r.getSiguiente() != inicio) {
            if (r.getSiguiente().getInfo().equals(datoAEliminar)) {
                Nodo<T> nodoAEliminar = r.getSiguiente();
                dato = nodoAEliminar.getInfo();

                r.setSiguiente(nodoAEliminar.getSiguiente());

                if (nodoAEliminar == fin) fin = r;
                break;
            }
            r = r.getSiguiente();
        }
        return dato;
    }

    // 5. Insertar dato en la posicion ej. insertarPosicion(T dato, int posicion)

    public void insertarDatoEnPosicion(T dato, int posicion) {
        if (posicion == 0) insertaInicio(dato);
        else {
            Nodo<T> r = inicio;
            for (int i = 0; i < posicion - 1; i++) {
                r = r.getSiguiente();
            }
            Nodo<T> nodoAInsertar = new Nodo<>(dato);
            nodoAInsertar.setSiguiente(r.getSiguiente());
            r.setSiguiente(nodoAInsertar);
            if (r == fin) fin = nodoAInsertar;
        }
    }

    public int getTamano() {
        if (inicio == null) return 0;
        int tamano = 0;
        Nodo<T> r = inicio;
        do {
            tamano++;
            r = r.getSiguiente();
        } while (r != inicio);
        return tamano;
    }


    public boolean isEmpty() {
        return inicio == null;
    }

    public T peekActual() {
        return actual == null ? null : actual.getInfo();
    }

    public T peekSiguiente() {
        return actual == null ? null : actual.getSiguiente().getInfo();
    }

    public void avanzar() {
        if (actual != null) actual = actual.getSiguiente();
    }

    public void retornarAlInicio() {
        actual = inicio;
    }
}
