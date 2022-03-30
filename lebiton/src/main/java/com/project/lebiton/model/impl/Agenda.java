package com.project.lebiton.model.impl;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Agenda {
    private final StringProperty horario;
    private final StringProperty dia;

    public Agenda(final StringProperty horario, final StringProperty dia) {
        this.horario = horario;
        this.dia = dia;
    }

    public Agenda() {
        this.dia = new SimpleStringProperty("");
        this.horario = new SimpleStringProperty("");
    }

    public StringProperty getHorario() {
        return horario;
    }

    public void setHorario(final String horario) {
        this.horario.set(horario);
    }

    public StringProperty getDia() {
        return dia;
    }

    public void setDia(final String dia) {
        this.dia.set(dia);
    }


    public static class Builder {

        protected StringProperty horario;
        protected StringProperty dia;

        public Builder horario(final String horario) {
            this.horario = new SimpleStringProperty("");
            this.horario.set(horario);
            return this;
        }

        public Builder dia(final String dia) {
            this.dia = new SimpleStringProperty("");
            this.dia.set(dia);
            return this;
        }

        public Agenda build() {
            return new Agenda(horario, dia);
        }

    }
}