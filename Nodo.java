
/**
 * Write a description of class Nodo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nodo
{
   private LugarTuristico valor;
   private Nodo siguiente;
   private Nodo anterior;
   public Nodo(LugarTuristico valor){
       this.valor=valor;
       this.siguiente=siguiente;
       
   }
   public LugarTuristico getValor(){
       return this.valor;
   }
   public void setValor(LugarTuristico v){
       this.valor=v;
   }
   public Nodo getSiguiente(){
       return this.siguiente;
   }
   public void setSiguiente(Nodo s){
       this.siguiente=s;
   }
   
   //DOblemente
    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}

