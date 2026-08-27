package jeziel.graficadora.Modelos;

public class Punto3D extends Punto {
    float ordenadaX;
    float ordenadaY;
    float ordenadaZ;

    public Punto3D(float ordenadaX, float ordenadaY, float ordenadaZ) {
        this.ordenadaX = ordenadaX;
        this.ordenadaY = ordenadaY;
        this.ordenadaZ = ordenadaZ;
    }

    public float getOrdenadaX() {
        return ordenadaX;
    }

    public void setOrdenadaX(float ordenadaX) {
        this.ordenadaX = ordenadaX;
    }

    public float getOrdenadaY() {
        return ordenadaY;
    }

    public void setOrdenadaY(float ordenadaY) {
        this.ordenadaY = ordenadaY;
    }

    public float getOrdenadaZ() {
        return ordenadaZ;
    }

    public void setOrdenadaZ(float ordenadaZ) {
        this.ordenadaZ = ordenadaZ;
    }
    @Override
    public float obtenerDistanciaPunto(Punto otro) {
        if(otro instanceof Punto3D){

            Punto3D p = (Punto3D) otro;
            float dx = this.ordenadaX - p.ordenadaX;
            float dy = this.ordenadaY - p.ordenadaY;
            float dz = this.ordenadaZ - p.ordenadaZ;

        }
        return 0;
    }
}
