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
    private static final double pixelesPunto = 5;
    private static final double pasosEscala = 5;
    private static final double ESCALA_MINIMA = 1;
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

    private boolean fueraDeRango(double xPixel, double yPixel){
        return xPixel < 0 || xPixel > lienzo.getWidth() || yPixel < 0 || yPixel > lienzo.getHeight();
    }

    private void dibujarPunto2D(double ordenadaX, double ordenadaY){
        double ordenadaXPixel = xmatematicoAPixelX(ordenadaX);
        double ordenadaYPixel = ymatematicoAPixelY(ordenadaY);

        while (fueraDeRango(ordenadaXPixel, ordenadaYPixel) && escala > ESCALA_MINIMA){
            escala = Math.max(escala - pasosEscala, ESCALA_MINIMA);
            dibujar();
            ordenadaXPixel = xmatematicoAPixelX(ordenadaX);
            ordenadaYPixel = ymatematicoAPixelY(ordenadaY);
        }

        if (!fueraDeRango(ordenadaXPixel, ordenadaYPixel)){
            g.setFill(Color.BLACK); //¿Tal vez poner para que vaya cambiando de color conforme los puntos que se agreguen?
            g.fillOval(ordenadaXPixel - pixelesPunto, ordenadaYPixel - pixelesPunto, 2*pixelesPunto, 2*pixelesPunto);
        }else{
            System.out.println("No se puede representar, supera la escala minima permitida");//Temporal, despues agregar una alerta
        }
    }

    private void dibujarVector2D(double ordenadaX, double ordenadaY){
        double ordenadaXPixel = xmatematicoAPixelX(ordenadaX);
        double ordenadaYPixel = ymatematicoAPixelY(ordenadaY);

        while (fueraDeRango(ordenadaXPixel, ordenadaYPixel) && escala > ESCALA_MINIMA){
            escala = Math.max(escala - pasosEscala, ESCALA_MINIMA);
            dibujar();
            ordenadaXPixel = xmatematicoAPixelX(ordenadaX);
            ordenadaYPixel = ymatematicoAPixelY(ordenadaY);
        }

        if (!fueraDeRango(ordenadaXPixel, ordenadaYPixel)){
            g.setStroke(Color.BLACK); //¿Tal vez poner para que vaya cambiando de color conforme los puntos que se agreguen?
            g.setLineWidth(1);
            g.strokeLine(origenX, origenY, ordenadaXPixel, ordenadaYPixel);
        }else {
            System.out.println("No se puede representar, supera la escala minima permitida");//Temporal, despues agregar una alerta
        }
    }

    private void dibujarCabezaFlecha(double ordenadaX, double ordenadaY) {
        if (ordenadaX == 0 && ordenadaY == 0) return;
        double anguloVector = Math.atan2(ordenadaY, ordenadaX);
        double longitudCabeza = 0.35; //Longitud de las aletas me da pereza hacerlo dinamico xdd
        double anguloApertura = Math.toRadians(45);
        double anguloAleta1 = anguloVector + Math.PI - anguloApertura; // 180° - 45°
        double anguloAleta2 = anguloVector + Math.PI + anguloApertura; // 180° + 45°

        double xAleta1 = ordenadaX + longitudCabeza * Math.cos(anguloAleta1);
        double yAleta1 = ordenadaY + longitudCabeza * Math.sin(anguloAleta1);

        double xAleta2 = ordenadaX + longitudCabeza * Math.cos(anguloAleta2);
        double yAleta2 = ordenadaY + longitudCabeza * Math.sin(anguloAleta2);

        double xPuntaPixel = xmatematicoAPixelX(ordenadaX);
        double yPuntaPixel = ymatematicoAPixelY(ordenadaY);

        double xAleta1Pixel = xmatematicoAPixelX(xAleta1);
        double yAleta1Pixel = ymatematicoAPixelY(yAleta1);

        double xAleta2Pixel = xmatematicoAPixelX(xAleta2);
        double yAleta2Pixel = ymatematicoAPixelY(yAleta2);

        g.strokeLine(xPuntaPixel, yPuntaPixel, xAleta1Pixel, yAleta1Pixel);
        g.strokeLine(xPuntaPixel, yPuntaPixel, xAleta2Pixel, yAleta2Pixel);
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
        dibujarCabezaFlecha(ordenadaX,ordenadaY);
    }

    //Metodos auxiliares para transformar coordenadas cartesianas a Pixeles
    private double xmatematicoAPixelX(double xMatematico){
        return (xMatematico * escala) + origenX;
    }

    private double ymatematicoAPixelY(double yMatematico){
        return origenY - (yMatematico * escala);
    }

}
