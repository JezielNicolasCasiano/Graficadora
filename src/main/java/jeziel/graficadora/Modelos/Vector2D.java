package jeziel.graficadora.Modelos;

public class Vector2D extends Vector<Vector2D>{

    public Vector2D(float vectorX, float vectorY) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }

    public float getVectorX() {
        return vectorX;
    }

    public void setVectorX(float vectorX) {
        this.vectorX = vectorX;
    }

    public float getVectorY() {
        return vectorY;
    }

    public void setVectorY(float vectorY) {
        this.vectorY = vectorY;
    }

    @Override
    public float obtenerMagnitud(){
        return (float) Math.sqrt(vectorX*vectorX + vectorY * vectorY);
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
    public Vector2D multEscalar(float k){
        return new Vector2D(this.vectorX*k,this.vectorY*k);
    }

    @Override
    public float obtenerProductoPunto(Vector2D v){
        return (this.vectorX * v.vectorX) + (this.vectorY * v.vectorY);
    }

    @Override
    public Vector2D normalizar(){
        float mag=obtenerMagnitud();
        if (mag==0){
            throw new ArithmeticException("No se puede normalizar vector nulo");
        }
        return new Vector2D(vectorX/mag,vectorY/mag);
    }
}