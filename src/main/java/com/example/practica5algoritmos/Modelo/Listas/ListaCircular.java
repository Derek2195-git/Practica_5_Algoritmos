package com.example.practica5algoritmos.Modelo.Listas;

public class ListaCircular<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;

    public ListaCircular() {
        inicio = null;
        fin = null;
    }

    public void insertaInicio(T dato) {
        Nodo<T> n = new Nodo<>(dato);

        if (inicio == null) {
            inicio = fin = n;
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
            inicio = fin = n;
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
                inicio = fin = null;
            } else {
                fin.setSiguiente(inicio.getSiguiente());
                inicio = inicio.getSiguiente();
            }
            return dato;
        }
    }

    public T eliminaFin() {
        T dato = null;
        if (inicio == null) {
            System.out.println("Lista vacia");
            return dato;
        } else {
            if (inicio == fin) {
                inicio = fin = null;
                dato = inicio.getInfo();
            } else {
                Nodo<T> r = inicio;
                while (r.getSiguiente() != null) {
                    r = r.getSiguiente();
                }
                fin = r;
                fin.setSiguiente(inicio);
            }
        }
        return dato;
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
        if (nodo.getSiguiente() == null || nodo.getSiguiente() == inicio) return false;
        else if (nodo.getInfo().equals(info)) return true;
        else {
            return buscar(nodo.getSiguiente(), info);
        }
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
}
