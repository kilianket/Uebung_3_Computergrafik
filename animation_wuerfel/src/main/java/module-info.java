module com.example.animation_wuerfel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.animation_wuerfel to javafx.fxml;
    exports com.example.animation_wuerfel;
}