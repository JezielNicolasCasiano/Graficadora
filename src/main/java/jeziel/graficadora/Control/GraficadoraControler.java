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
    @FXML
    private Pane containerPane;
    @FXML private TextField txtX;
    @FXML private TextField txtY;
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
    @FXML private ComboBox <Vector2D>cboxVectorB;
    @FXML private ComboBox <Punto2D> cBoxPuntoA;
    @FXML private ComboBox <Punto2D> cBoxPuntoB;

    private final ObservableList <Vector2D> listaVectores = FXCollections.observableArrayList();
    private final ObservableList <Punto2D> listaPuntos = FXCollections.observableArrayList();

    @FXML
    private void handleCrearVector(){

        try{

            double x = Double.parseDouble(txtX.getText());
            double y = Double.parseDouble(txtY.getText());

            Vector2D vector = new Vector2D(x,y);
            listaVectores.add(vector);

            txtX.clear();
            txtY.clear();
            if(cboxVectorA.getValue() == null) cboxVectorA.setValue(vector);
            if(cboxVectorB.getValue() == null) cboxVectorB.setValue(vector);

        }catch (NumberFormatException e){

            txtError.setText("Error al ingresar los componentes del vector");

        }

    }

    @FXML
    private void handleSumar(){

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cboxVectorB.getValue();

        if(validarSeleccion(vA,vB)){

            Vector2D res = vA.sumar(vB);
            txtSumar.setText(res.toString());
        }

    }

    @FXML
    private void handleRestar(){

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cboxVectorB.getValue();
        if(validarSeleccion(vA,vB)){

            Vector2D res = vA.restar(vB);
            txtRestar.setText(res.toString());

        }

    }

    @FXML
    private void handleProductoPunto(){

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cboxVectorB.getValue();
        if(validarSeleccion(vA,vB)){

            double res = vA.obtenerProductoPunto(vB);
            txtPPunto.setText(String.format(".2f",res));

        }

    }

    @FXML
    private void handleProyeccion() {

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cboxVectorB.getValue();
        if (validarSeleccion(vA, vB)) {

            try {

                Vector2D res = vA.obtenerProyeccion(vA, vB);
                txtProyeccion.setText(res.toString());

            } catch (ArithmeticException e) {

                txtError.setText("No se puede proyectar un vector nulo.");

            }

        }

    }
    private void handleAngulo(){

        Vector2D vA = cboxVectorA.getValue();
        Vector2D vB = cboxVectorB.getValue();

        if (validarSeleccion(vA,vB)){

            double res = Math.toDegrees(vA.obtenerAngulo(vA,vB));
            txtAngulo.setText(String.format(".2f",res));
        }

    }

    @FXML
    private void handleMagnitud(){

        Vector2D vA = cboxVectorA.getValue();
        if(validarSeleccionIndividual(vA)){

            double res = vA.obtenerMagnitud();
            txtMagnitud.setText(String.format(".2f",res));

        }

    }

    @FXML
    private void handleNormalizar(){

        Vector2D vA = cboxVectorA.getValue();
        if (validarSeleccionIndividual(vA)){

            try{

                Vector2D res = vA.normalizar();
                txtNormalizar.setText(String.format(".2f",res.toString()));

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
                txtMultEscalar.setText(String.format("%.2f",res));

            }catch (NumberFormatException e){

                txtError.setText("Error al ingresar escalar");

            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    cboxVectorA.setItems(listaVectores);
    cboxVectorB.setItems(listaVectores);


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
}
