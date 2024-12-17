
public class ArbolDos {
    private NodoAVL raiz;

    public ArbolDos() {
        this.raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void insertar(LugarTuristico valor) {
        this.raiz = generarNodo(valor, raiz);
    }

    private NodoAVL generarNodo(LugarTuristico valor, NodoAVL nodo) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }
        if (valor.getNombre().compareTo(nodo.getValor().getNombre()) < 0) { 
            nodo.setIzq(generarNodo(valor, nodo.getIzq()));
        } else if (valor.getNombre().compareTo(nodo.getValor().getNombre()) > 0) {
            nodo.setDer(generarNodo(valor, nodo.getDer()));
        }

        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzq()), obtenerAltura(nodo.getDer())));

        int balance = obtenerAltura(nodo.getDer()) - obtenerAltura(nodo.getIzq());
        if (balance < -1 && valor.getNombre().compareTo(nodo.getIzq().getValor().getNombre()) < 0) {
            return rotacionDer(nodo);
        }
        if (balance > 1 && valor.getNombre().compareTo(nodo.getDer().getValor().getNombre()) > 0) {
            return rotacionIzq(nodo);
        }
        if (balance < -1 && valor.getNombre().compareTo(nodo.getIzq().getValor().getNombre()) > 0) {
            return rotacionIzqDer(nodo);
        }
        if (balance > 1 && valor.getNombre().compareTo(nodo.getDer().getValor().getNombre()) < 0) {
            return rotacionDerIzq(nodo);
        }

        return nodo;
    }

    public LugarTuristico buscarNombre(String nombre) {
        long inicio = System.nanoTime();
        int[] nodosRecorridos = {0}; 
        LugarTuristico resultado = buscarNodo(nombre, raiz, nodosRecorridos);
        long fin = System.nanoTime();

        long tiempo = fin - inicio; 

        if (resultado != null) {
            System.out.println("Lugar turístico encontrado: " + resultado.getNombre());
        } else {
            System.out.println("No se encontró ningún lugar turístico con el nombre: " + nombre);
        }
        System.out.println("Nodos recorridos: " + nodosRecorridos[0]);
        System.out.println("Tiempo de búsqueda: " + tiempo + " nanosegundos (" + (tiempo / 1_000_000.0) + " ms)");

        return resultado;
    }

    private LugarTuristico buscarNodo(String nombre, NodoAVL nodo, int[] nodosRecorridos) {
        if (nodo == null) {
            return null; 
        }

        nodosRecorridos[0]++; 

        if (nombre.equals(nodo.getValor().getNombre())) {
            return nodo.getValor(); 
        }

        if (nombre.compareTo(nodo.getValor().getNombre()) < 0) {
            return buscarNodo(nombre, nodo.getIzq(), nodosRecorridos);
        } else {
            return buscarNodo(nombre, nodo.getDer(), nodosRecorridos);
        }
    }
    
public void eliminar(LugarTuristico lugar) {
    this.raiz = eliminarNodo(lugar, raiz);
}

private NodoAVL eliminarNodo(LugarTuristico lugar, NodoAVL nodo) {
    if (nodo == null) {
        return null; 
    }

    if (lugar.getNombre().compareTo(nodo.getValor().getNombre()) < 0) {
        nodo.setIzq(eliminarNodo(lugar, nodo.getIzq()));
    } else if (lugar.getNombre().compareTo(nodo.getValor().getNombre()) > 0) {
        nodo.setDer(eliminarNodo(lugar, nodo.getDer()));
    } else { 
        // Caso 1: Nodo con uno o ningún hijo
        if (nodo.getIzq() == null || nodo.getDer() == null) {
            NodoAVL temp = (nodo.getIzq() != null) ? nodo.getIzq() : nodo.getDer();
            nodo = temp; 
        } else { 
            // Caso 2: Nodo con dos hijos
            NodoAVL sucesor = obtenerMinimo(nodo.getDer());
            nodo.setValor(sucesor.getValor());
            nodo.setDer(eliminarNodo(sucesor.getValor(), nodo.getDer()));
        }
    }

    if (nodo == null) {
        return null;
    }

    // Actualizar la altura del nodo actual
    nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzq()), obtenerAltura(nodo.getDer())));

    // Calcular el balance del nodo actual
    int balance = obtenerAltura(nodo.getDer()) - obtenerAltura(nodo.getIzq());

    // Caso de rotación
    if (balance < -1 && obtenerBalance(nodo.getIzq()) <= 0) {
        return rotacionDer(nodo); // Rotación derecha
    }
    if (balance < -1 && obtenerBalance(nodo.getIzq()) > 0) {
        nodo.setIzq(rotacionIzq(nodo.getIzq()));
        return rotacionDer(nodo); // Rotación izquierda-derecha
    }
    if (balance > 1 && obtenerBalance(nodo.getDer()) >= 0) {
        return rotacionIzq(nodo); // Rotación izquierda
    }
    if (balance > 1 && obtenerBalance(nodo.getDer()) < 0) {
        nodo.setDer(rotacionDer(nodo.getDer()));
        return rotacionIzq(nodo); // Rotación derecha-izquierda
    }

    return nodo;
}

    private NodoAVL obtenerMinimo(NodoAVL nodo) {
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo;
    }

    private int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }

    private NodoAVL rotacionIzq(NodoAVL x) {
        NodoAVL y = x.getDer();
        NodoAVL z = y.getIzq();

        y.setIzq(x);
        x.setDer(z);

        x.setAltura(1 + Math.max(obtenerAltura(x.getIzq()), obtenerAltura(x.getDer())));
        y.setAltura(1 + Math.max(obtenerAltura(y.getIzq()), obtenerAltura(y.getDer())));

        return y;
    }

    private NodoAVL rotacionDer(NodoAVL y) {
        NodoAVL x = y.getIzq();
        NodoAVL z = x.getDer();

        x.setDer(y);
        y.setIzq(z);

        y.setAltura(1 + Math.max(obtenerAltura(y.getIzq()), obtenerAltura(y.getDer())));
        x.setAltura(1 + Math.max(obtenerAltura(x.getIzq()), obtenerAltura(x.getDer())));

        return x;
    }

    private NodoAVL rotacionIzqDer(NodoAVL nodo) {
        nodo.setIzq(rotacionIzq(nodo.getIzq()));
        return rotacionDer(nodo);
    }

    private NodoAVL rotacionDerIzq(NodoAVL nodo) {
        nodo.setDer(rotacionDer(nodo.getDer()));
        return rotacionIzq(nodo);
    }

    public void imprimirArbol() {
        imprimirArbolRecursivo(raiz, "", true);
    }

    private void imprimirArbolRecursivo(NodoAVL nodo, String indentacion, boolean esIzq) {
        if (nodo != null) {
            if (nodo.getDer() != null) {
                imprimirArbolRecursivo(nodo.getDer(), indentacion + (esIzq ? "|   " : "    "), false);
            }
            System.out.println(indentacion + (esIzq ? "└── " : "┌── ") + nodo.getValor());
            if (nodo.getIzq() != null) {
                imprimirArbolRecursivo(nodo.getIzq(), indentacion + (esIzq ? "|   " : "    "), true);
            }
        }
    }
    private int obtenerBalance(NodoAVL nodo) {
    if (nodo == null) {
        return 0;
    }
    return obtenerAltura(nodo.getDer()) - obtenerAltura(nodo.getIzq());
}
}

