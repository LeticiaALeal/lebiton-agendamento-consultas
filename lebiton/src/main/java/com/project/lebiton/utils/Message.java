package com.project.lebiton.utils;

import javafx.scene.control.Alert;

public class Message {

    public static void showAlert(final String title, final String header, final String content, final Alert.AlertType alertType) {
        final Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setResizable(true);
        alert.showAndWait();
    }
}
