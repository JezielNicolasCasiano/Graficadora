package jeziel.graficadora.Control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraficadoraControler implements Initializable {
    @FXML
    private Pane containerPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private void abrirOperaciones(){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Operadores.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Operaciones");
            stage.setScene(new Scene(root));

            stage.show();
        }catch (IOException e){

            e.printStackTrace();

        }
    }
}
