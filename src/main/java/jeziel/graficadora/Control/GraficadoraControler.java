package jeziel.graficadora.Control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import jeziel.graficadora.Modelos.Punto2D;
import jeziel.graficadora.Modelos.Vector2D;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraficadoraControler implements Initializable {
    @FXML private planoCartesianoController planoCartesianoController;
    @FXML
    private Pane containerPane;
    @FXML private TextField txtX;
    @FXML private TextField txtY;
    @FXML private TextField txtPX;
    @FXML private TextField txtPY;
    @FXML private TextField txtSumar;
    @FXML private TextField txtRestar;
    @FXML private TextField txtPPunto;
    @FXML private TextField txtNormalizar;
    @FXML private TextField txtVaPunto;
    @FXML private TextField txtTPunto;
    @FXML private TextField txtEscalar;
    @FXML private TextField txtMagnitud;
    @FXML private TextField txtAngulo;
    @FXML private TextField txtProyeccion;
    @FXML private TextField txtMultEscalar;
    @FXML private TextField txtDaPunto;
    @FXML private TextField txtError;
    @FXML private ComboBox <Vector2D> cboxVectorA;
    @FXML private ComboBox <Vector2D>cBoxVectorB;
    @FXML private ComboBox <Punto2D> cBoxPuntoA;
    @FXML private ComboBox <Punto2D> cBoxPuntoB;

    private final ObservableList <Vector2D> listaVectores = FXCollections.observableArrayList();
    private final ObservableList <Punto2D> listaPuntos = FXCollections.observableArrayList();
    private double escalarActual = 1.0;


    @FXML
    private void handleCrearPunto(){

        try {

            double x = Double.parseDouble(txtPX.getText());
            double y = Double.parseDouble(txtPY.getText());

            if (planoCartesianoController != null && !planoCartesianoController.agregarPunto2D(x, y)) {
                txtError.setText("El punto queda demasiado lejos para representarlo");
                return;
            }

            Punto2D punto = new Punto2D(x,y);
            listaPuntos.add(punto);
            txtError.setText("");

            txtPX.clear();
            txtPY.clear();
            if(cBoxPuntoA.getValue() == null) cBoxPuntoA.setValue(punto);
            if (cBoxPuntoB.getValue() == null) cBoxPuntoB.setValue(punto);

        }catch (NumberFormatException e){

        txtError.setText("error al ingresar coordenadas");

        }

    }
    @FXML
    private void handleCalcularDistancia(){

    Punto2D pA = cBoxPuntoA.getValue();
    Punto2D pB = cBoxPuntoB.getValue();
    if(validarPuntos(pA,pB)){

        double res = pA.obtenerDistanciaPunto(pB);
        txtDaPunto.setText(String.valueOf(res));

    }
    }
    @FXML
    private void handleVectorHastaPunto(){

        Punto2D pA = cBoxPuntoA.getValue();
        Punto2D pB = cBoxPuntoB.getValue();
        if (validarPuntos(pA,pB)){

            Vector2D vector = pA.vectorHastaPunto(pB);
            txtVaPunto.setText(vector.toString());

        }

    }
@FXML
private void handleTrasladarPunto(){

Punto2D pA = cBoxPuntoA.getValue();
Vector2D vA = cboxVectorA.getValue();

if(pA == null || vA==null){

    txtError.setText("selecciona un punto y un vector");

}
    assert pA != null;
    assert vA != null;
    Punto2D puntoTrasladado = pA.trasladarPunto(vA);
    txtTPunto.setText(puntoTrasladado.toString());
}
    @FXML
    private void handleCrearVector(){

        try{

            double x = Double.parseDouble(txtX.getText());
            double y = Double.parseDouble(txtY.getText());

            if (planoCartesianoController != null && !planoCartesianoController.agregarVector2D(x, y)) {
                txtError.setText("El vector queda demasiado lejos para representarlo");
                return;
            }

            Vector2D vector = new Vector2D(x,y);
            listaVectores.add(vector);
            txtError.setText("");

            txtX.clear();
            txtY.clear();
            if(cboxVectorA.getValue() == null) cboxVectorA.setValue(vector);
            if(cBoxVectorB.getValue() == null) cBoxVectorB.setValue(vector);

        }catch (NumberFormatException e){

            txtError.setText("Error al ingresar los componentes del vector");

        }

    }

    @FXML
    private void handleSumar(){

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cBoxVectorB.getValue();

        if(validarSeleccion(vA,vB)){

            Vector2D res = vA.sumar(vB);
            txtSumar.setText(res.toString());
        }

    }

    @FXML
    private void handleRestar(){

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cBoxVectorB.getValue();
        if(validarSeleccion(vA,vB)){

            Vector2D res = vA.restar(vB);
            txtRestar.setText(res.toString());

        }

    }

    @FXML
    private void handleProductoPunto(){

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cBoxVectorB.getValue();
        if(validarSeleccion(vA,vB)){

            double res = vA.obtenerProductoPunto(vB);
            txtPPunto.setText(String.valueOf(res));

        }

    }

    @FXML
    private void handleProyeccion() {

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cBoxVectorB.getValue();
        if (validarSeleccion(vA, vB)) {

            try {

                Vector2D res = vA.obtenerProyeccion(vA, vB);
                txtProyeccion.setText(res.toString());

            } catch (ArithmeticException e) {

                txtError.setText("No se puede proyectar un vector nulo.");

            }

        }

    }
    @FXML
    private void handleAngulo(){

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cBoxVectorB.getValue();

        if (validarSeleccion(vA,vB)){

            double res = Math.toDegrees(vA.obtenerAngulo(vA,vB));
            txtAngulo.setText(String.valueOf(res));
        }

    }

    @FXML
    private void handleMagnitud(){

        Vector2D vA = cboxVectorA.getValue();
        if(validarSeleccionIndividual(vA)){

            double res = vA.obtenerMagnitud();
            txtMagnitud.setText(String.valueOf(res));

        }

    }

    @FXML
    private void handleNormalizar(){

        Vector2D vA = cboxVectorA.getValue();
        if (validarSeleccionIndividual(vA)){

            try{

                Vector2D res = vA.normalizar();
                txtNormalizar.setText(res.toString());

            } catch (Exception e) {
                txtError.setText("Error al normalizar vector");
            }

        }

    }

    @FXML
    private void handleMultEscalar() {

        Vector2D vA = cboxVectorA.getValue();
        if(validarSeleccionIndividual(vA)){

            try{
                double k = Double.parseDouble(txtEscalar.getText());
                Vector2D res = vA.multEscalar(k);
                txtMultEscalar.setText(res.toString());
            }catch (NumberFormatException e){
                txtError.setText("Error al ingresar escalar");
            }
        }
    }
    @FXML
    private void handleGuardarEscalar(){
    try {
        this.escalarActual = Double.parseDouble(txtEscalar.getText());
    }catch (NumberFormatException e){
        txtError.setText("Error al guardar escalar");
    }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    cboxVectorA.setItems(listaVectores);
    cBoxVectorB.setItems(listaVectores);
    cBoxPuntoA.setItems(listaPuntos);
    cBoxPuntoB.setItems(listaPuntos);
    }

    private boolean validarSeleccion(Vector2D vA, Vector2D vB) {
        txtError.setText("");
        if (vA == null || vB == null) {
            txtError.setText("Error: Selecciona dos vectores de las listas.");
            return false;
        }
        return true;
    }
    private boolean validarSeleccionIndividual(Vector2D v) {
        txtError.setText("");
        if (v == null) {
            txtError.setText("Error: Selecciona un vector en la lista Vector A.");
            return false;
        }
        return true;
    }
    private boolean validarPuntos(Punto2D pA, Punto2D pB) {
        txtError.setText("");
        if (pA == null || pB == null) {
            txtError.setText("Error: Selecciona dos puntos de las listas.");
            return false;
        }
        return true;
    }
}
