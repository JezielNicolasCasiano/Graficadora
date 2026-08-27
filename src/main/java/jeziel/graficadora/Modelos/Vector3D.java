package jeziel.graficadora.Modelos;

public class Vector3D extends Vector{

    float vectorX;
    float vectorY;
    float vectorZ;
    float magnitud;

    public Vector3D(float vectorX,float vectorY,float vectorZ) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
        this.vectorZ = vectorZ;
        this.magnitud = (float) Math.sqrt(vectorX * vectorX + vectorY*vectorY + vectorZ*vectorZ);

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

    public float getVectorZ() {
        return vectorZ;
    }

    public void setVectorZ(float vectorZ) {
        this.vectorZ = vectorZ;
    }
    @Override
    public float obtenerMagnitud(){

        return this.magnitud;

    }
}
