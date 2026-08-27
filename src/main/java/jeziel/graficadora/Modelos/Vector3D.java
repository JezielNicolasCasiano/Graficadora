package jeziel.graficadora.Modelos;

public class Vector3D extends Vector<Vector3D>{

    public Vector3D(float vectorX,float vectorY,float vectorZ) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
        this.vectorZ = vectorZ;
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
        return (float) Math.sqrt(vectorX*vectorX+vectorY*vectorY+vectorZ*vectorZ);
    }

    @Override
    public Vector3D sumar(Vector3D v){
        return new Vector3D(this.vectorX+v.vectorX,this.vectorY+v.vectorY,this.vectorZ+v.vectorZ);
    }

    @Override
    public Vector3D restar(Vector3D v){
        return new Vector3D(this.vectorX-v.vectorX,this.vectorY-v.vectorY,this.vectorZ-v.vectorZ);
    }

    @Override
    public Vector3D multEscalar(float k){
        return new Vector3D(this.vectorX*k,this.vectorY*k,this.vectorZ*k);
    }

    @Override
    public float obtenerProductoPunto(Vector3D v){
        return (this.vectorX*v.vectorX)+(this.vectorY*v.vectorY)+(this.vectorZ*v.vectorZ);
    }

    @Override
    public Vector3D normalizar(){
        return new Vector3D(vectorX/obtenerMagnitud(),vectorY/obtenerMagnitud(),vectorZ/obtenerMagnitud());
    }
}
