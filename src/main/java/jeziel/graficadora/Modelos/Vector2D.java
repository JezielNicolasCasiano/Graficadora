package jeziel.graficadora.Modelos;

public class Vector2D extends Vector<Vector2D>{

    public Vector2D(double vectorX, double vectorY) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }

    public double getVectorX() {
        return vectorX;
    }

    public void setVectorX(double vectorX) {
        this.vectorX = vectorX;
    }

    public double getVectorY() {
        return vectorY;
    }

    public void setVectorY(double vectorY) {
        this.vectorY = vectorY;
    }

    @Override
    public double obtenerMagnitud(){
        return (double) Math.sqrt(vectorX*vectorX + vectorY * vectorY);
    }

    @Override
    public Vector2D sumar(Vector2D v){
        return new Vector2D(this.vectorX + v.vectorX, this.vectorY + v.vectorY);
    }

    @Override
    public Vector2D restar(Vector2D v){
        return new Vector2D(this.vectorX -v.vectorX, this.vectorY - v.vectorY);
    }

    @Override
    public Vector2D multEscalar(double k){
        return new Vector2D(this.vectorX*k,this.vectorY*k);
    }

    @Override
    public double obtenerProductoPunto(Vector2D v){
        return (this.vectorX * v.vectorX) + (this.vectorY * v.vectorY);
    }

    @Override
    public Vector2D normalizar(){
        double mag=obtenerMagnitud();
        if (mag==0){
            throw new ArithmeticException("No se puede normalizar vector nulo");
        }
        return new Vector2D(vectorX/mag,vectorY/mag);
    }
}