public class ColaDinamica
{
    private Nodo primero;
    private Nodo ultimo;
    
    public ColaDinamica(){
        this.primero=null;
        this.ultimo=null;
    }
    
    public boolean estaVacio(){
        return primero==null && ultimo==null;
    }
    
    public boolean agregar(LugarTuristico valor){
        Nodo nuevo=new Nodo (valor);
        if(estaVacio())
        {
            this.primero=nuevo;
            this.ultimo=nuevo;
            return true;
        }
        this.ultimo.setSiguiente(nuevo);
        this.ultimo=nuevo;
        return true;
    }
    
    public LugarTuristico eliminar() throws Exception {
        if(estaVacio()){
          throw new Exception("cola vacia");
        }
        LugarTuristico valor=primero.getValor();
        this.primero=this.primero.getSiguiente();
        return valor;
    }
    
    public LugarTuristico VerPrimero() throws Exception {
        if(estaVacio()){
          throw new Exception("cola vacia");
        }
        LugarTuristico dato=this.primero.getValor();
        return dato;
    }
     public void imprimir() {
        if (estaVacio()) {
            System.out.println("La cola está vacía.");
            return;
        }
        
        Nodo actual = primero;
        System.out.println("Contenido de la cola:");
        while (actual != null) {
            System.out.println(actual.getValor()); // Imprime el LugarTuristico usando su método toString()
            actual = actual.getSiguiente();
        }
    }
}