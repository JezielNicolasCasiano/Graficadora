package jeziel.graficadora.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OperacionesController {

    @FXML
    private Button btnCerrar;

    @FXML
    private void cerrarVenta(ActionEvent event){

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
