module com.pizzarialollipop.pizzarialollipop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.pizzarialollipop.pizzarialollipop to javafx.fxml;
    exports com.pizzarialollipop.pizzarialollipop;
}