module com.example.software1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.software1 to javafx.fxml;
    exports com.example.software1;
}