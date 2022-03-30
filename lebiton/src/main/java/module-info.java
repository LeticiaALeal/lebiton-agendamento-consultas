module com.project.lebiton {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.project.lebiton to javafx.fxml;
    exports com.project.lebiton;
    exports com.project.lebiton.controller;
    exports com.project.lebiton.model;
    opens com.project.lebiton.controller to javafx.fxml;
    exports com.project.lebiton.model.impl;
}