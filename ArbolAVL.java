public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
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

        if (valor.getCosto() < nodo.getValor().getCosto()) {
            nodo.setIzq(generarNodo(valor, nodo.getIzq()));
        } else if (valor.getCosto() > nodo.getValor().getCosto()) {
            nodo.setDer(generarNodo(valor, nodo.getDer()));
        }

        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzq()), obtenerAltura(nodo.getDer())));

        int balance = obtenerAltura(nodo.getDer()) - obtenerAltura(nodo.getIzq());

        if (balance < -1 && valor.getCosto() < nodo.getIzq().getValor().getCosto()) {
            return RotacionDer(nodo);
        }
        if (balance > 1 && valor.getCosto() > nodo.getDer().getValor().getCosto()) {
            return RotacionIzq(nodo);
        }
        if (balance < -1 && valor.getCosto() > nodo.getIzq().getValor().getCosto()) {
            return RotacionIzqDer(nodo);
        }
        if (balance > 1 && valor.getCosto() < nodo.getDer().getValor().getCosto()) {
            return RotacionDerIzq(nodo);
        }

        return nodo;
    }

    public void eliminar(LugarTuristico valor) {
        this.raiz = eliminarNodo(valor, raiz);
    }

    private NodoAVL eliminarNodo(LugarTuristico valor, NodoAVL nodo) {
        if (nodo == null) {
            return null;
        }

        if (valor.getCosto() < nodo.getValor().getCosto()) {
            nodo.setIzq(eliminarNodo(valor, nodo.getIzq()));
        } else if (valor.getCosto() > nodo.getValor().getCosto()) {
            nodo.setDer(eliminarNodo(valor, nodo.getDer()));
        } else {
            if (nodo.getIzq() == null || nodo.getDer() == null) {
                NodoAVL temp = (nodo.getIzq() != null) ? nodo.getIzq() : nodo.getDer();
                return temp;
            } else {
                NodoAVL sucesor = obtenerMinimo(nodo.getDer());
                nodo.setValor(sucesor.getValor());
                nodo.setDer(eliminarNodo(sucesor.getValor(), nodo.getDer()));
            }
        }
        return nodo;
    }

    private NodoAVL obtenerMinimo(NodoAVL nodo) {
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo;
    }

    public LugarTuristico buscarCosto(int costo) {
        return buscarNodos(costo, raiz);
    }

    private LugarTuristico buscarNodos(int costoBuscado, NodoAVL nodoActual) {
        if (nodoActual == null) {
            return null;
        }

        LugarTuristico costoActual = nodoActual.getValor();

        if (costoBuscado == costoActual.getCosto()) {
            return nodoActual.getValor();
        }

        if (costoBuscado < costoActual.getCosto()) {
            return buscarNodos(costoBuscado, nodoActual.getIzq());
        } else {
            return buscarNodos(costoBuscado, nodoActual.getDer());
        }
    }

    private int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }

    private NodoAVL RotacionIzq(NodoAVL x) {
        NodoAVL y = x.getDer();
        NodoAVL z = y.getIzq();

        y.setIzq(x);
        x.setDer(z);

        x.setAltura(1 + Math.max(obtenerAltura(x.getIzq()), obtenerAltura(x.getDer())));
        y.setAltura(1 + Math.max(obtenerAltura(y.getIzq()), obtenerAltura(y.getDer())));

        return y;
    }

    private NodoAVL RotacionDer(NodoAVL y) {
        NodoAVL x = y.getIzq();
        NodoAVL z = x.getDer();

        x.setDer(y);
        y.setIzq(z);

        y.setAltura(1 + Math.max(obtenerAltura(y.getIzq()), obtenerAltura(y.getDer())));
        x.setAltura(1 + Math.max(obtenerAltura(x.getIzq()), obtenerAltura(x.getDer())));

        return x;
    }

    private NodoAVL RotacionIzqDer(NodoAVL nodo) {
        nodo.setIzq(RotacionIzq(nodo.getIzq()));
        return RotacionDer(nodo);
    }

    private NodoAVL RotacionDerIzq(NodoAVL nodo) {
        nodo.setDer(RotacionDer(nodo.getDer()));
        return RotacionIzq(nodo);
    }

    public void recorrerInOrden() {
        recorrerInOrdenRecursivo(raiz);
    }

    private void recorrerInOrdenRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            recorrerInOrdenRecursivo(nodo.getIzq());
            System.out.println(nodo.getValor());
            recorrerInOrdenRecursivo(nodo.getDer());
        }
    }
}



