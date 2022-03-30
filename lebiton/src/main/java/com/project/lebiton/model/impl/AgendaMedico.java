package com.project.lebiton.model.impl;

import javafx.beans.property.StringProperty;

public class AgendaMedico {
    private Medico medico;
    private Agenda agenda;

    public AgendaMedico(final Medico medico, final Agenda agenda) {
        this.medico = medico;
        this.agenda = agenda;
    }

    public AgendaMedico() {
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(final Medico medico) {
        this.medico = medico;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(final Agenda agenda) {
        this.agenda = agenda;
    }

    public StringProperty getEspecialidade() {
        return medico.getEspecialidade();
    }

    public StringProperty getDia() {
        return agenda.getDia();
    }

    public StringProperty getHorario() {
        return agenda.getHorario();
    }

    public static class Builder {
        protected Medico medico;
        protected Agenda agenda;

        public Builder medico(final Medico medico) {
            this.medico = medico;
            return this;
        }

        public Builder agenda(final Agenda agenda) {
            this.agenda = agenda;
            return this;
        }

        public AgendaMedico build() {
            return new AgendaMedico(medico, agenda);
        }
    }
}