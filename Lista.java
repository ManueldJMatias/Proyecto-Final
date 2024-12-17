
/**
 * Write a description of class a here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */public class Lista {
    private Arista primero;
    private Arista ultimo;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
    }
    public  boolean estaVacia(){
        return this.primero==null && this.ultimo==null;
    }

    public void agregarAdyacencia(Object destino){
        if(!existe(destino)){
            Arista nodo = new Arista(destino);
            inserta(nodo);
        }
    }
    public void agregarAdyacencia(Object destino, float peso){
        if(!existe(destino)){
            Arista nodo = new Arista(destino,peso);
            inserta(nodo);
        }
    }

    private boolean inserta(Arista nodo) {
        if(estaVacia()){
            this.primero=nodo;
            this.ultimo=nodo;
            return true;
        }
        this.ultimo.setSiguiente(nodo);
        this.ultimo=nodo;
        return  true;
    }

    private boolean existe(Object destino) {
        Arista actual=primero;
        while(actual != null ){
            if(destino.toString().equals(actual.getDestino().toString()))
                return true;
            actual= actual.getSiguiente();
        }
        return  false;
    }

    @Override
    public String toString() {
        String cadena="";
        Arista actual= primero;
        while (actual!=null){
            cadena += actual.getDestino().toString()+";";
            actual=actual.getSiguiente();
        }
        return  cadena;
    }
}