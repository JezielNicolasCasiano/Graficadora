package jeziel.graficadora.Modelos;

public abstract class Vector<T extends Vector<T>> {
    protected float vectorX;
    protected float vectorY;
    protected float vectorZ;

    public abstract T sumar(T v);
    public abstract T restar(T v);
    public abstract T multEscalar(float k);
    public abstract float obtenerProductoPunto(T v);
    public abstract float obtenerMagnitud();
    public abstract T normalizar();
}
