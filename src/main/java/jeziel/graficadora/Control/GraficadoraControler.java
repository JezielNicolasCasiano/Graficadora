package jeziel.graficadora.Control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;

import java.net.URL;
import java.util.ResourceBundle;

public class GraficadoraControler implements Initializable {
    @FXML
    private Pane containerPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Group subSceneRoot = new Group();

        Box box = new Box(100, 100, 100);
        box.setTranslateX(150);
        box.setTranslateY(150);
        subSceneRoot.getChildren().add(box);

        SubScene subScene = new SubScene(
                subSceneRoot,
                400,
                400,
                true,
                SceneAntialiasing.BALANCED
        );
        subScene.setFill(Color.web("#2b2b2b"));

        PerspectiveCamera camera = new PerspectiveCamera(false);
        subScene.setCamera(camera);

        subScene.widthProperty().bind(containerPane.widthProperty());
        subScene.heightProperty().bind(containerPane.heightProperty());

        containerPane.getChildren().add(subScene);
    }
}
