package jeziel.graficadora.Modelos;

public class Vector2D extends Vector{
    float vectorX;
    float vectorY;
    float magnitud;

    public Vector2D(float vectorX, Float vectorY) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
        this.magnitud = (float) Math.sqrt(vectorX*vectorX + vectorY * vectorY);
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
        return this.magnitud;
    }
    public Vector2D sumar(Vector2D v){

        return new Vector2D(this.vectorX + v.vectorX, this.vectorY + v.vectorY);
    }
    public Vector2D restar(Vector2D v){

        return new Vector2D(this.vectorX -v.vectorX, this.vectorY - v.vectorY);

    }
    public Vector2D multEscalar(float k){

        return new Vector2D(this.vectorX*k,this.vectorY*k);

    }
    public float obtenerProductoPunto(Vector2D v){

        return (this.vectorX * v.vectorX) + (this.vectorY * v.vectorY);

    }
}
