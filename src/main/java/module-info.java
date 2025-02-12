module com.pizzarialollipop.pizzarialollipop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.pizzarialollipop.pizzarialollipop to javafx.fxml;
    exports com.pizzarialollipop.pizzarialollipop;
}