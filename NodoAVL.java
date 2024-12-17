

public class NodoAVL {
    private LugarTuristico valor;
    private NodoAVL izq;
    private NodoAVL der;
    private int altura;

    public NodoAVL(LugarTuristico valor) {
        this.valor = valor;
        this.izq = null;
        this.der = null;
        this.altura = 1;
    }

    public LugarTuristico getValor() {
        return valor;
    }

    public void setValor(LugarTuristico valor) {
        this.valor = valor;
    }

    public NodoAVL getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL izq) {
        this.izq = izq;
    }

    public NodoAVL getDer() {
        return der;
    }

    public void setDer(NodoAVL der) {
        this.der = der;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}