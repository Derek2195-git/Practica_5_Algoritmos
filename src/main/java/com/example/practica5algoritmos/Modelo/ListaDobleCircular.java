package com.example.practica5algoritmos.Modelo;

public class ListaDobleCircular<T extends Comparable<? super T>> {
    private NodoDoble<T> inicio;
    private NodoDoble<T> fin;
    private NodoDoble<T> actual;

    public ListaDobleCircular() {
        inicio = null;
        fin = null;
        actual = null;
    }

    public void insertaInicio(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);

        if (inicio == null) {
            inicio = fin = actual = n;
            n.setSiguiente(inicio);
            n.setAnt(inicio);
        } else {
            n.setSiguiente(inicio);
            inicio.setAnt(n);
            inicio = n;
            fin.setSiguiente(inicio);
            n.setAnt(fin);
        }
    }

    public void insertaFin(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);
        if (inicio == null) {
            inicio = fin = actual = n;
            n.setSiguiente(inicio);
            n.setAnt(inicio);
        } else {
            n.setSiguiente(inicio);
            inicio.setAnt(n);
            fin.setSiguiente(n);
            n.setAnt(fin);
            fin = n;
        }
    }

    public T eliminaInicio() {
        if (inicio == null) {
            return null;
        } else {
            T dato = inicio.getInfo();
            if (inicio == fin) {
                inicio = fin = null;
            } else {
                fin.setSiguiente(inicio.getSiguiente());
                inicio = inicio.getSiguiente();
                inicio.setAnt(fin);
            }
            return dato;
        }
    }

    public T eliminaFin() {
        if (fin == null) {
            return null;
        } else {
            T dato = fin.getInfo();
            if (inicio == fin) {
                inicio = fin = null;
            } else {
                NodoDoble<T> r = fin.getAnt();
                r.setSiguiente(inicio);
                inicio.setAnt(r);
                fin = r;
            }
            return dato;
        }
    }

    public String recorrer() {
        if (inicio == null) {
            return "La lista esta vacía";
        } else {
            NodoDoble<T> r = inicio;
            String cadena = "";
            do {
                cadena += r.getInfo() + ", ";
                r = r.getSiguiente();
            } while (r != inicio);
            return cadena;
        }
    }

    /*
    1. Mostrar  GG
    2. Eliminar X GG
    3. Buscar X elemento GG
    3.1 Buscar X elemento de forma recursiva GG
    4. Eliminar posición GG
    5. ordenar lista GG
    6. insertar en X posición GG
    7. Eliminar pares GG
     */

    public String mostrarLista(NodoDoble<T> n) {
        if (n.getSiguiente() == inicio && n == fin) {
            return n.getInfo() + "";
        } else {
            return n.getInfo() + ", " + mostrarLista(n.getSiguiente());
        }
    }

    public String mostrarListaRecursiva() {
        if (inicio == null) return "La lista esta vacia";
        else return mostrarLista(inicio);
    }

    public T eliminarX(T datoABuscar) {
        if (inicio == null) return null;
        if (inicio.getInfo().equals(datoABuscar)) return eliminaInicio();

        NodoDoble<T> r = inicio;
        T dato = null;

        while(!r.getSiguiente().equals(inicio)) {
            if (r.getSiguiente().getInfo().equals(datoABuscar)) {
                NodoDoble<T> nodoAEliminar = r.getSiguiente();
                dato = nodoAEliminar.getInfo();

                r.setSiguiente(nodoAEliminar.getSiguiente());
                nodoAEliminar.getSiguiente().setAnt(r);

                if (nodoAEliminar == fin) fin = r;
                break;
            }
            r = r.getSiguiente();
        }

        return dato;
    }

    // Metodos no necesarios
    public T eliminarX(NodoDoble<T> nodo, T datoABuscar) {
        if (nodo.getInfo().equals(datoABuscar)) {
            // O defino los metodos, o uso los ifs para incluir todos los metodos de eliminar
            if (nodo == inicio) return eliminaInicio();
            if (nodo == fin) return eliminaFin();

            nodo.getAnt().setSiguiente(nodo.getSiguiente());
            nodo.getSiguiente().setAnt(nodo.getAnt());
            return datoABuscar;

        }
        else if (nodo.getSiguiente() == inicio) return null;
        else return eliminarX(nodo.getSiguiente(), datoABuscar);
    }
    public T eliminarXRecursivo(T datoABuscar) {
        if (inicio == null) return null;
        else return eliminarX(inicio, datoABuscar);
    }

    public boolean buscarX(T datoABuscar) {
        if (inicio == null) return false;
        if (inicio.getInfo().equals(datoABuscar)) return true;

        NodoDoble<T> r = inicio;
        while(r.getSiguiente() != inicio) {
            if (r.getSiguiente().getInfo().equals(datoABuscar)) return true;
            r = r.getSiguiente();
        }
        return false;
    }

    public boolean buscarX(NodoDoble<T> nodo, T datoABuscar) {
        if (nodo.getInfo().equals(datoABuscar)) return true;
        else if (nodo.getSiguiente() == inicio) return false;
        else return buscarX(nodo.getSiguiente(), datoABuscar);
    }

    public boolean buscarXRecursivo(T datoABuscar) {
        if (inicio == null) return false;
        else return buscarX(inicio, datoABuscar);
    }


    public void eliminarPares() {
        NodoDoble<T> r = inicio;
        while (r != fin) {
            if (r.getInfo() instanceof Integer && (Integer)(Object) r.getInfo() % 2 == 0) {
                if (r == inicio) eliminaInicio();
                else {
                    r.getAnt().setSiguiente(r.getSiguiente());
                    r.getSiguiente().setAnt(r.getAnt());
                }
            }

            r = r.getSiguiente();
        }

        if (r == fin) {
            if (r.getInfo() instanceof Integer && (Integer)(Object) r.getInfo() % 2 == 0) {
                r.getAnt().setSiguiente(r.getSiguiente());
                r.getSiguiente().setAnt(r.getAnt());
            }
        }
    }


    // Eliminar pos
    public T eliminaPosicion(int posicion) {
        if (inicio == null || posicion < 0) return null;
        else if (posicion == 0) return eliminaInicio();
        else {
            NodoDoble<T> r = inicio;
            int indiceActual = 0;

            while(r.getSiguiente() != inicio && indiceActual < posicion) {
                r = r.getSiguiente();
                indiceActual++;
            }

            if (indiceActual != posicion) return null;
            if (r == fin) return eliminaFin();

            T dato = r.getInfo();
            r.getAnt().setSiguiente(r.getSiguiente());
            r.getSiguiente().setAnt(r.getAnt());
            return dato;
        }
    }

    public void ordenarLista() {
        if (inicio == null || fin == null) return;
        else {
            int n = getTamano();
            for (int i = 0; i < n-1; i++) {
                NodoDoble<T> r = inicio;
                for (int j = 0; j < n-1; j++) {
                    NodoDoble<T> s = r.getSiguiente();
                    if (r.getInfo().compareTo(s.getInfo()) > 0) {
                        T temp = r.getInfo();
                        r.setInfo(s.getInfo());
                        s.setInfo(temp);
                    }
                    r = s;
                }

            }
        }
    }

    public void insertaEnPosicion(T dato, int posicion) {
        if (posicion < 0 || inicio == null) insertaInicio(dato);
        else if (posicion == 0) insertaInicio(dato);
        else {
            NodoDoble<T> r = inicio;
            int contador = 0;
            while(contador < posicion-1 && r.getSiguiente() != inicio) {
                r = r.getSiguiente();
                contador++;
            }
            if (r == fin) {
                insertaFin(dato);
                return;
            }
            NodoDoble<T> nodoAInsertar = new NodoDoble<>(dato);
            nodoAInsertar.setSiguiente(r.getSiguiente());
            nodoAInsertar.setAnt(r);
            r.getSiguiente().setAnt(nodoAInsertar);
            r.setSiguiente(nodoAInsertar);
        }
    }

    // Hice un metodo para calcular el tamaño de la lista para el de ordenar
    public int getTamano() {
        if (inicio == null) return 0;
        else {
            int contador = 0;
            NodoDoble<T> r = inicio;
            do {
                contador++;
                r = r.getSiguiente();
            } while (r != inicio);
            return contador;
        }
    }

    public void eliminarPar() {
        if (inicio == null) return;
        int tamanoArreglo = getTamano();
        NodoDoble<T> r = inicio;
        for (int i = 0; i < tamanoArreglo; i++) {
            NodoDoble<T> s = r.getSiguiente();

            if (r.getInfo() instanceof Integer && (Integer) r.getInfo() % 2 == 0) {
                if (r == inicio) eliminaInicio();
                else if (r == fin) eliminaFin();
                else {
                    r.getAnt().setSiguiente(r.getSiguiente());
                    r.getSiguiente().setAnt(r.getAnt());
                }
            }
            r = s;
        }
    }

    public int getPosicionActual() {
        if (actual == null) return 0;
        int posicion = 1;
        NodoDoble<T> r = inicio;
        while (r != actual) {
            r = r.getSiguiente();
            posicion++;
        }
        return posicion;
    }

    public boolean isEmpty() {
        return (inicio == null);
    }

    public T peekInicio() {
        return inicio == null ? null :  inicio.getInfo();
    }

    public T peekFin() {
        return inicio == null ? null : fin.getInfo();
    }

    public T peekActual() {
        return actual == null ? null : actual.getInfo();
    }

    public T peekSiguiente() {
        return actual == null ? null : actual.getSiguiente().getInfo();
    }

    public T peekAnterior() {
        return actual == null ? null : actual.getAnt().getInfo();
    }

    public void avanzarActual() {
        if (actual != null) actual = actual.getSiguiente();
    }

    public void retrocederActual() {
        if (actual != null) actual = actual.getAnt();
    }

    public void devolverActualAlInicio() {
        actual = inicio;
    }

}
