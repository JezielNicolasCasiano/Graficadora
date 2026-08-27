package jeziel.graficadora.Modelos;

public class Punto2D extends Punto{
float ordenadaX;
float ordenadaY;

    public Punto2D(float x, float ordenadaX) {
        this.ordenadaX = ordenadaX;
    }

    public float getOrdenadaY() {
        return ordenadaY;
    }

    public void setOrdenadaY(float ordenadaY) {
        this.ordenadaY = ordenadaY;
    }

    public void setOrdenadaX(float ordenadaX) {
        this.ordenadaX = ordenadaX;
    }

    @Override
    public float obtenerDistanciaPunto(Punto otro) {
        if(otro instanceof Punto2D){
            Punto2D p = (Punto2D) otro;
            float dx = this.ordenadaX - p.ordenadaX;
            float dy = this.ordenadaY - p.ordenadaY;
            return (float) Math.sqrt(dx*dx + dy*dy);
        }
        return 0;
    }
    public Vector2D vectorHastaPunto(Punto2D p){
        return new Vector2D(p.ordenadaX - this.ordenadaX,p.ordenadaY - this.ordenadaY);
    }
    public Punto2D trasladarPunto(Vector2D v){

        return new Punto2D(this.ordenadaX + v.getVectorX(), this.ordenadaY + v.getVectorY());

    }
}
