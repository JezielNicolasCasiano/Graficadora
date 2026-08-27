module jeziel.graficadora {
    requires javafx.controls;
    requires javafx.fxml;


    opens jeziel.graficadora to javafx.fxml;
    exports jeziel.graficadora;
}