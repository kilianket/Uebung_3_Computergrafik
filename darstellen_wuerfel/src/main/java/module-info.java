module com.example.darstellen_wuerfel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.darstellen_wuerfel to javafx.fxml;
    exports com.example.darstellen_wuerfel;
}