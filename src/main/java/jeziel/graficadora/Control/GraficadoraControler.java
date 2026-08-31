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
        //Graficacion en 3D, no tocar.
        Group subSceneRoot = new Group();
        Box box = new Box(100, 100, 100);
        box.setTranslateX(150);
        box.setTranslateY(150);
        subSceneRoot.getChildren().add(box);
        SubScene subScene = new SubScene(
                subSceneRoot,
                600,
                600,
                true,
                SceneAntialiasing.BALANCED
        );
        subScene.setFill(Color.web("#2b2b2b"));
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        subScene.setCamera(camera);
        subScene.widthProperty().bind(containerPane.widthProperty());
        subScene.heightProperty().bind(containerPane.heightProperty());
        containerPane.getChildren().add(subScene);
    }
}
