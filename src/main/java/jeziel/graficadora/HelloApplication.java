package jeziel.graficadora;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
 public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Graficadora.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Graficadora");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void abrirOperaciones(){
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

    public static void main(String[] args) {
        launch(args);
    }
}
