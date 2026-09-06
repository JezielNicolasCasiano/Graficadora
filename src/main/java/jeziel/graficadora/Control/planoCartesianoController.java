package jeziel.graficadora.Control;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import jeziel.graficadora.Modelos.Plano;

public class planoCartesianoController {
    //Objetos de javaFX implementadas en la vista e inyectadas aca.
    @FXML
    private AnchorPane contenedorPrincipal;
    @FXML
    private Canvas lienzo;

    private GraphicsContext g; //Clase ya implementada para modelar un sistema de coordenadas en 2D
    private Plano planoMatematico;
    private static final double PIXELES_POR_DIVISION = 80;
    private static final double pixelesPunto = 10;
    private static final double pasosEscala = 5;
    private double escala = 50;
    private double origenX;
    private double origenY;
    private boolean inicializado = false;


    @FXML
    private void initialize() {
        g = lienzo.getGraphicsContext2D(); //inicializacion de la clase dedicada
        planoMatematico = new Plano(); //Instanciacion del plano del modelo

        lienzo.widthProperty().bind(contenedorPrincipal.widthProperty());
        lienzo.heightProperty().bind(contenedorPrincipal.heightProperty());

        lienzo.widthProperty().addListener((o, a, b) -> dibujar()); //Como lienzo es un canvas es necesario añadir listener para escuchar el cambio de posicion cuando se hace zoom out o in
        lienzo.heightProperty().addListener((o, a, b) -> dibujar());

        dibujar();
    }

    //metodo personalizado para recaclcular las posiciones
    private void dibujar() {
        double w = lienzo.getWidth();
        double h = lienzo.getHeight();
        if (w <= 0 || h <= 0) return;

        g.setFill(Color.WHITE);
        g.fillRect(0, 0, w, h);

        this.origenX = w / 2;
        this.origenY = h / 2;

        g.setStroke(Color.web("#d5dde3"));
        g.setLineWidth(1);
        for (double x = origenX % escala; x < w; x += escala) {
            g.strokeLine(Math.round(x) + 0.5, 0, Math.round(x) + 0.5, h);
        }
        for (double y = origenY % escala; y < h; y += escala) {
            g.strokeLine(0, Math.round(y) + 0.5, w, Math.round(y) + 0.5);
        }

        g.setStroke(Color.web("#3a4750"));
        g.setLineWidth(1.4);
        g.strokeLine(0, Math.round(origenY) + 0.5, w, Math.round(origenY) + 0.5);
        g.strokeLine(Math.round(origenX) + 0.5, 0, Math.round(origenX) + 0.5, h);
        for (int i = 0; i < planoMatematico.getPuntos2D().size(); i++){
            dibujarPunto2D(planoMatematico.getPuntos2D().get(i).getOrdenadaX(),planoMatematico.getPuntos2D().get(i).getOrdenadaY());
        }
        for (int i = 0; i < planoMatematico.getVectores2D().size(); i++){
            dibujarVector2D(planoMatematico.getVectores2D().get(i).getVectorX(),planoMatematico.getVectores2D().get(i).getVectorY());
        }
    }

    private void dibujarPunto2D(double ordenadaX, double ordenadaY){
        double ordenadaXPixel = xmatematicoAPixelX(ordenadaX);
        double ordenadaYPixel = ymatematicoAPixelY(ordenadaY);
        if(ordenadaXPixel>lienzo.getWidth() || ordenadaYPixel> lienzo.getHeight() || (ordenadaXPixel<0 && escala > 0) || (ordenadaYPixel<0 && escala > 0)){
            escala = escala - pasosEscala;
            dibujar();
            if (escala > 1){
                dibujarPunto2D(ordenadaX, ordenadaY);
            }
        }else if(ordenadaXPixel<lienzo.getWidth() && ordenadaYPixel< lienzo.getHeight() && (ordenadaXPixel>0) && (ordenadaYPixel>0)){
            g.setFill(Color.BLACK); //¿Tal vez poner para que vaya cambiando de color conforme los puntos que se agreguen?
            g.fillOval(ordenadaXPixel - pixelesPunto, ordenadaYPixel - pixelesPunto, 2*pixelesPunto, 2*pixelesPunto);
        }else{
            System.out.println("No se puede representar, supera la esclaa 1:1");//Temporal, despues agregar una alerta
        }
    }

    private void dibujarVector2D(double ordenadaX, double ordenadaY){
        double ordenadaXPixel = xmatematicoAPixelX(ordenadaX);
        double ordenadaYPixel = ymatematicoAPixelY(ordenadaY);
        if (ordenadaXPixel>lienzo.getWidth() || ordenadaYPixel> lienzo.getHeight() || (ordenadaXPixel<0 && escala > 0) || (ordenadaYPixel<0 && escala > 0)){
            escala = escala - pasosEscala;
            dibujar();
            if (escala > 1){
                dibujarVector2D(ordenadaX, ordenadaY);
            }
        }else if (ordenadaXPixel<lienzo.getWidth() && ordenadaYPixel<lienzo.getHeight()  && (ordenadaXPixel>0 ) && (ordenadaYPixel>0)){
            g.setStroke(Color.BLACK); //¿Tal vez poner para que vaya cambiando de color conforme los puntos que se agreguen?
            g.setLineWidth(1);
            g.strokeLine(origenX, origenY, ordenadaXPixel, ordenadaYPixel);
        }else {
            System.out.println("No se puede representar, supera la esclaa 1:1");//Temporal, despues agregar una alerta
        }
    }

    //Metodos para agregar puntos y vectores al plano
    public void agregarPunto2D(double ordenadaX, double ordenadaY){
        planoMatematico.crearPunto2D(ordenadaX, ordenadaY);
        //Algoritmo para que se vuelva a dibujar
        dibujarPunto2D(ordenadaX,ordenadaY);
    }

    public void agregarVector2D(double ordenadaX, double ordenadaY){
        planoMatematico.crearVector2D(ordenadaX, ordenadaY);
        //Algoritmo para que se vuelva a dibujar
        dibujarVector2D(ordenadaX, ordenadaY);
    }

    //Metodos auxiliares para transformar coordenadas cartesianas a Pixeles
    private double xmatematicoAPixelX(double xMatematico){
        return (xMatematico * escala) + origenX;
    }

    private double ymatematicoAPixelY(double yMatematico){
        return origenY - (yMatematico * escala);
    }

}
