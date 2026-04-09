module com.example.perspekt_wuerfel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.perspekt_wuerfel to javafx.fxml;
    exports com.example.perspekt_wuerfel;
}