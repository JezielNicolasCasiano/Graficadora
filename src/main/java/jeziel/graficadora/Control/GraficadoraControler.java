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
    private Pane containerPane; // Inyectado desde el FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Crear el grafo de escena interno para el SubScene
        Group subSceneRoot = new Group();

        // 2. Agregar nodos al contenido del SubScene (ej. un cubo 3D)
        Box box = new Box(100, 100, 100);
        box.setTranslateX(150);
        box.setTranslateY(150);
        subSceneRoot.getChildren().add(box);

        // 3. Instanciar el SubScene (root, ancho, alto, depthBuffer, antialiasing)
        SubScene subScene = new SubScene(
                subSceneRoot,
                400,
                400,
                true,
                SceneAntialiasing.BALANCED
        );
        subScene.setFill(Color.web("#2b2b2b"));

        // Opcional: Asignar cámara si renderizas contenido 3D
        PerspectiveCamera camera = new PerspectiveCamera(false);
        subScene.setCamera(camera);

        // 4. Vincular el tamaño del SubScene al Pane contenedor (Responsive)
        subScene.widthProperty().bind(containerPane.widthProperty());
        subScene.heightProperty().bind(containerPane.heightProperty());

        // 5. Agregar el SubScene a la lista de hijos del Pane
        containerPane.getChildren().add(subScene);
    }
}
