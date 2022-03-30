package com.project.lebiton.handleError;

import javafx.scene.control.TextField;

import java.util.List;

public class MarkFieldError {
    public static void markInvalidField(final List<TextField> fields) {
        for (final TextField field : fields) {
            field.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
    }
}