package jeziel.graficadora.Modelos;
import java.util.ArrayList;

public class Plano {

    ArrayList<Punto> Puntos = new ArrayList<>();
    ArrayList<Vector> Vectores = new ArrayList<>();

    public Vector3D crearVector3D(float ordenadaX, float ordenadaY, float ordenadaZ){
        return new Vector3D(ordenadaX,ordenadaY, ordenadaZ);

    }

    public Vector2D crearVector32(float ordenadaX, float ordenadaY){
        return new Vector2D(ordenadaX,ordenadaY);
    }

    public Punto3D crearPunto3D(float ordenadaX, float ordenadaY, float ordenadaZ){
        return new Punto3D(ordenadaX, ordenadaY, ordenadaZ);
    }

    public Punto2D crearPunto2D(float ordenadaX, float ordenadaY){
        return new Punto2D(ordenadaX, ordenadaY);
    }

    //getters y setters

    public ArrayList<Punto> getPuntos() {
        return Puntos;
    }

    public void setPuntos(ArrayList<Punto> puntos) {
        Puntos = puntos;
    }

    public ArrayList<Vector> getVectores() {
        return Vectores;
    }

    public void setVectores(ArrayList<Vector> vectores) {
        Vectores = vectores;
    }
}
