package jeziel.graficadora.Modelos;

public class Punto2D extends Punto<Punto2D, Vector2D>{

    public Punto2D(float ordenadaX, float ordenadaY) {
        this.ordenadaX = ordenadaX;
        this.ordenadaY = ordenadaY;
    }

    public float getOrdenadaY() {
        return ordenadaY;
    }

    public void setOrdenadaY(float ordenadaY) {
        this.ordenadaY = ordenadaY;
    }

    public float getOrdenadaX() {return ordenadaX;}

    public void setOrdenadaX(float ordenadaX) {
        this.ordenadaX = ordenadaX;
    }

    @Override
    public float obtenerDistanciaPunto(Punto2D p) {
        float dx = this.ordenadaX - p.ordenadaX;
        float dy = this.ordenadaY - p.ordenadaY;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public Vector2D vectorHastaPunto(Punto2D p){
        return new Vector2D(p.ordenadaX - this.ordenadaX,p.ordenadaY - this.ordenadaY);
    }

    @Override
    public Punto2D trasladarPunto(Vector2D v){
        return new Punto2D(this.ordenadaX + v.getVectorX(), this.ordenadaY + v.getVectorY());
    }
}
