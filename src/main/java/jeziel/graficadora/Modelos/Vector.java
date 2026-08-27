package jeziel.graficadora.Modelos;

public abstract class Vector<V extends Vector<V>> {
    protected float vectorX;
    protected float vectorY;
    protected float vectorZ;

    public abstract V sumar(V v);
    public abstract V restar(V v);
    public abstract V multEscalar(float k);
    public abstract float obtenerProductoPunto(V v);
    public abstract float obtenerMagnitud();
    public abstract V normalizar();
}
