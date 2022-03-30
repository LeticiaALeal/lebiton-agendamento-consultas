package com.project.lebiton.utils;

import javafx.scene.control.TextField;

public class RequestField {
    private String key;
    private TextField value;

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public TextField getValue() {
        return value;
    }

    public void setValue(final TextField value) {
        this.value = value;
    }
}
