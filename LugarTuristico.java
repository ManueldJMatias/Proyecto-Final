public class LugarTuristico {

    private String nombre;
    private String clima;
    private String estado;
    private String tipo;
    private int hrs_recorrido;
    private int costo;
   
    public LugarTuristico(String n, String e, String c, String t, int h,int co) {
        this.nombre = n;
        this.clima = c;
        this.estado = e;
        this.tipo = t;
        this.hrs_recorrido = h;
        this.costo=co;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHrsRecorrido() {
        return hrs_recorrido;
    }

    public void setHrsRecorrido(int hrs_recorrido) {
        this.hrs_recorrido = hrs_recorrido;
    }
    public int getCosto(){
        return this.costo;
    }
    public void setCosto(int costo){
        this.costo=costo;
    }
     @Override
    public String toString() {
        return "LugarTuristico{" + 
                "nombre='" + nombre + '\'' + 
                ", clima='" + clima + '\'' + 
                ", estado='" + estado + '\'' + 
                ", tipo='" + tipo + '\'' + 
                ", hrs_recorrido=" + hrs_recorrido + 
                ", costo=" + costo + 
                '}';
    }
}
