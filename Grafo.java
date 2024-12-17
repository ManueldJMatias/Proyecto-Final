public class Grafo {
    private  NodoGrafo primero;
    private  NodoGrafo ultimo;

    public Grafo() {
        this.primero=null;
        this.ultimo=null;
    }

    public  boolean estaVacio(){
        return  this.primero==null && this.ultimo==null;
    }

    public  boolean existeVertice(Object dato){
        if (estaVacio())
            return  false;
        NodoGrafo actual= primero;
        while (actual!=null){
            if(actual.getDato().toString().equals(dato.toString()))
                return  true;
            actual= actual.getSiguiente();
        }
        return false;
    }

    public  boolean agregarArista(Object origen, Object destino){
        if(!existeVertice(origen) || !existeVertice(destino)){
            System.out.println("No se puede agregar");
            return false;
        }

        NodoGrafo actual= primero;
        while(!actual.getDato().toString().equals(origen.toString())){
            actual= actual.getSiguiente();
        }
        actual.getListaAdyacencia().agregarAdyacencia(destino);
        return  true;
    }
    public  boolean agregarArista(Object origen, Object destino, float peso){
        if(!existeVertice(origen) || !existeVertice(destino))
            return false;
        NodoGrafo actual= primero;
        while(!actual.getDato().toString().equals(origen.toString())){
            actual= actual.getSiguiente();
        }
        actual.getListaAdyacencia().agregarAdyacencia(destino,peso);
        return  true;
    }

    public  boolean agregarNodo(Object dato){
        if(existeVertice(dato))
            return  false;
        NodoGrafo nodo= new NodoGrafo(dato);
        if(estaVacio()){
            this.primero=nodo;
            this.ultimo=nodo;
            return  true;
        }
        this.ultimo.setSiguiente(nodo);
        this.ultimo=nodo;
        return  true;
    }

    @Override
    public String toString() {
        String cadena="";
        NodoGrafo actual=primero;
        while (actual!=null){
            cadena += actual.getDato().toString()+ "->"+ actual.getListaAdyacencia().toString()+"\n";
            actual=actual.getSiguiente();
        }
        return cadena;
    }
}
