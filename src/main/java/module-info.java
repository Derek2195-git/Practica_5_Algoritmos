module com.example.practica5algoritmos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.practica5algoritmos to javafx.fxml;
    exports com.example.practica5algoritmos;
}