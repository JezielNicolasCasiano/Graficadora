package jeziel.graficadora.Modelos;

public class Punto3D extends Punto<Punto3D, Vector3D> {

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
    public float obtenerDistanciaPunto(Punto3D p) {
        float dx = this.ordenadaX - p.ordenadaX;
        float dy = this.ordenadaY - p.ordenadaY;
        float dz = this.ordenadaZ - p.ordenadaZ;
        return (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    @Override
    public Vector3D vectorHastaPunto(Punto3D p){
        return new Vector3D(p.ordenadaX - this.ordenadaX,p.ordenadaY - this.ordenadaY,p.ordenadaZ-this.ordenadaZ);
    }

    @Override
    public Punto3D trasladarPunto(Vector3D v){
        return new Punto3D(this.ordenadaX + v.getVectorX(), this.ordenadaY + v.getVectorY(),this.ordenadaZ+v.getVectorZ());
    }

}
