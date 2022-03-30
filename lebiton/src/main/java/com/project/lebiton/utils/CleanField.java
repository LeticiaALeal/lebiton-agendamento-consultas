package com.project.lebiton.utils;

import java.util.List;

public class CleanField {

    public static void cleanFieldList(List<RequestField> lista) {
        for (final RequestField item : lista) {
            item.getValue().setText("");
        }
    }
}
