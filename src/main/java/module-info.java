module com.example.codecademy {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;
    requires junit;

    opens com.example.codecademy.Domain to javafx.base;
    opens com.example.codecademy to javafx.fxml;
    exports com.example.codecademy;
}
