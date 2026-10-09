package jeziel.graficadora.Control;

import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import jeziel.graficadora.Modelos.Plano;

public class planoCartesianoController {
    //Objetos de javaFX implementadas en la vista e inyectadas aca.
    @FXML
    private AnchorPane contenedorPrincipal;
    @FXML
    private Canvas lienzo;

    private GraphicsContext g; //Clase ya implementada para modelar un sistema de coordenadas en 2D
    private Plano planoMatematico; //Ya parte del modelo, seria la representación abstracta del plano carteiano
    private double escala = 50;
    private final double pasoDeseado = 80;
    private static final double escalaMin = 1, escalaMax = 1e6;

    //variables para el funcionamiento del arrastre del mouse
    private double ultimoMouseX = 0;
    private double ultimoMouseY = 0;

    //Origenes para que saber en el mundo matematico a que corresponde
    private double origenX = 0;
    private double origenY = 0;



    //Variables relacionadas con el canvas y el graphscene
    private double anchoCanva;
    private double altoCanva;

    @FXML
    private void initialize() {
        g = lienzo.getGraphicsContext2D(); //inicializacion de la clase dedicada
        planoMatematico = new Plano(); //Instanciacion del plano del modelo

        lienzo.widthProperty().bind(contenedorPrincipal.widthProperty());
        lienzo.heightProperty().bind(contenedorPrincipal.heightProperty());

        lienzo.widthProperty().addListener((o, a, b) -> redibujarAjustando()); //Como lienzo es un canvas es necesario añadir listener para escuchar el cambio de posicion cuando se hace zoom out o in
        lienzo.heightProperty().addListener((o, a, b) -> redibujarAjustando());

        setScroll();
        setDrag();

        dibujar();
    }


    //Método principipal para dibujar en el plano.
    private void dibujar() {
        altoCanva = lienzo.getHeight();
        anchoCanva = lienzo.getWidth();
        g.clearRect(0, 0, anchoCanva, altoCanva);
        dibujarCuadricula(calcularPaso());
        dibujarEjes();
        dibujarNumeros();


    }

    public void dibujarEjes() {
        double xEjeY = nitido(xMatematicoAPixel(0));
        double yEjeX = nitido(yMatematicoAPixel(0));
        g.setLineWidth(1);
        g.setStroke(Color.BLACK);
        g.strokeLine(xEjeY, 0, xEjeY, altoCanva);  // Eje y
        g.strokeLine(0, yEjeX, anchoCanva, yEjeX);  //Eje x
    }

    public void dibujarCuadricula(double paso) {
        g.setLineWidth(0.5);
        g.setStroke(Color.rgb(126, 126, 126)); //color gris para cuadricula
        double xMin = xPixelAMatematico(0); //Se calcula el minimoX visible
        double xMax = xPixelAMatematico(anchoCanva); //Se calcula el maximoX visible
        double yMin = yPixelAMatematico(altoCanva); //Se calcula el minimoY visible
        double yMax = yPixelAMatematico(0); // Se calcula el maximoY visible
        long primeroX = (long) Math.ceil(xMin / paso);
        long ultimoX = (long) Math.floor(xMax / paso);
        long primeroY = (long) Math.ceil(yMin / paso);
        long ultimoY = (long) Math.floor(yMax / paso);
        for (long i = primeroX; i <= ultimoX; i++) {
            double x = i * paso;
            double px = nitido(xMatematicoAPixel(x));
            g.strokeLine(px, 0, px, altoCanva);
        }
        for (long i = primeroY; i <= ultimoY; i++) {
            double y = i * paso;
            double py = nitido(yMatematicoAPixel(y));
            g.strokeLine(0, py, anchoCanva, py);
        }
    }

    public double calcularPaso() {
        double pasoBruto = pasoDeseado / escala;
        double exponente = Math.floor(Math.log10(pasoBruto));
        double base = Math.pow(10, exponente);
        double fraccion = pasoBruto / base;
        double factor;
        if (fraccion <= 1) factor = 1;
        else if (fraccion <= 2) factor = 2;
        else if (fraccion <= 5) factor = 5;
        else factor = 10;
        return factor * base;
    }


    private void setScroll() {
        lienzo.setOnScroll(e -> {
            if (e.getDeltaY() == 0) return;

            double factor = Math.pow(1.1, e.getDeltaY() / 40.0);

            double nuevaEscala = Math.clamp(escala * factor, escalaMin, escalaMax);
            factor = nuevaEscala / escala; // factor real tras el límite

            double px = e.getX(), py = e.getY();
            origenX = (px - (px - calcularPosicionOrigenX()) * factor) - anchoCanva / 2;
            origenY = (py - (py - calcularPosicionOrigenY()) * factor) - altoCanva / 2;
            escala = nuevaEscala;

            dibujar();
            e.consume();
        });
    }

    private void setDrag(){
        lienzo.setOnMousePressed(e -> {
            ultimoMouseX = e.getX();
            ultimoMouseY = e.getY();
        });

        lienzo.setOnMouseDragged(e -> {
            origenX += (e.getX() - ultimoMouseX);
            origenY += (e.getY() - ultimoMouseY);
            ultimoMouseX = e.getX();
            ultimoMouseY = e.getY();
            dibujar();
        });
    }

    private void dibujarNumeros(){
        double posicionEjeY = yMatematicoAPixel(0) + 3;
        posicionEjeY = Math.clamp(posicionEjeY, -altoCanva, altoCanva);
        double posicionEjeX = xMatematicoAPixel(0) + 3;
        posicionEjeX = Math.clamp(posicionEjeX, -anchoCanva, anchoCanva);
        double paso = calcularPaso();
        double xMin = xPixelAMatematico(0); //Se calcula el minimoX visible
        double xMax = xPixelAMatematico(anchoCanva); //Se calcula el maximoX visible
        double yMin = yPixelAMatematico(altoCanva); //Se calcula el minimoY visible
        double yMax = yPixelAMatematico(0); // Se calcula el maximoY visible
        long primeroX = (long) Math.ceil(xMin / paso);
        long ultimoX = (long) Math.floor(xMax / paso);
        long primeroY = (long) Math.ceil(yMin / paso);
        long ultimoY = (long) Math.floor(yMax / paso);
        g.setTextAlign(TextAlignment.CENTER);
        g.setTextBaseline(VPos.TOP);
        for (long i = primeroX; i <= ultimoX; i++) {
            double x = i * paso;
            g.fillText(String.valueOf(x),xMatematicoAPixel(x) ,posicionEjeY);
        }
        for (long i = primeroY; i <= ultimoY; i++) {
            double y = i * paso;
            g.fillText(String.valueOf(y), posicionEjeX, yMatematicoAPixel(y));
        }

    }


    //Más metodos auxiliares
    private double calcularPosicionOrigenX() {
        return origenX + anchoCanva / 2;
    }

    private double calcularPosicionOrigenY() {
        return origenY + altoCanva / 2;
    }


    /*Metodos para agregar puntos y vectores al plano.
    Primero se calcula si cabe y con que escala; solo si cabe se guarda y se dibuja.
    Asi, cuando no se puede representar, la escala nunca llego a modificarse y no hay que restaurarla.*/
    public boolean agregarPunto2D(double ordenadaX, double ordenadaY) {
        double escalaNecesaria = calcularEscalaPara(ordenadaX, ordenadaY);
        if (escalaNecesaria < 0) return false; //no cabe ni con la escala minima: no se cambia nada

        planoMatematico.crearPunto2D(ordenadaX, ordenadaY);
        escala = escalaNecesaria;
        dibujar();
        return true;
    }

    public boolean agregarVector2D(double ordenadaX, double ordenadaY) {
        double escalaNecesaria = calcularEscalaPara(ordenadaX, ordenadaY);
        if (escalaNecesaria < 0) return false; //no cabe ni con la escala minima: no se cambia nada

        planoMatematico.crearVector2D(ordenadaX, ordenadaY);
        escala = escalaNecesaria;
        dibujar();
        return true;
    }

    //Metodos auxiliares para transformar coordenadas cartesianas a Pixeles
    private double xMatematicoAPixel(double xMatematico) {
        return calcularPosicionOrigenX() + (xMatematico * escala);
    }

    private double yMatematicoAPixel(double yMatematico) {
        return calcularPosicionOrigenY() - (yMatematico * escala);
    }


    //metodo para evitar aliasing
    private double nitido(double p) {
        return Math.floor(p) + 0.5;
    }


    //Metodos auxiliares para transformar coordenadas Pixeles a cartesianas
    private double xPixelAMatematico(double px) {
        return (px - calcularPosicionOrigenX()) / escala;
    }

    private double yPixelAMatematico(double py) {
        return (calcularPosicionOrigenY() - py) / escala;
    }

    public Canvas getLienzo() {
        return lienzo;
    }
}
