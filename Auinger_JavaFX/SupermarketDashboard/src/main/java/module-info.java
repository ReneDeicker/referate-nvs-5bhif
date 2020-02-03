module at.htl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens at.htl to javafx.fxml;
    exports at.htl;
}