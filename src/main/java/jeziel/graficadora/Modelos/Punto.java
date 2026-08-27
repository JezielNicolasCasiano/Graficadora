package jeziel.graficadora.Modelos;

public abstract class Punto <P extends Punto<P,V>, V extends Vector<V>>{
    protected float ordenadaX;
    protected float ordenadaY;
    protected float ordenadaZ;

    public abstract float obtenerDistanciaPunto(P p);
    public abstract V vectorHastaPunto(P p);
    public abstract P trasladarPunto(V v);
}
