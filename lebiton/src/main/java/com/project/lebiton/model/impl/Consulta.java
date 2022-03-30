package com.project.lebiton.model.impl;

import javafx.beans.property.StringProperty;

public class Consulta {
    private Long id;
    private Medico medico;
    private Paciente paciente;
    private Agenda agenda;

    public Consulta(Paciente paciente, Medico medico, Agenda agenda) {
        this.medico = medico;
        this.paciente = paciente;
        this.agenda = agenda;
    }

    public Consulta() {
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /* Getters e Setters oriundos da associação */

    public StringProperty getNomeMedico() {
        return medico.getNome();
    }

    public StringProperty getEspecialidade() {
        return medico.getEspecialidade();
    }

    public StringProperty getNomePaciente() {
        return paciente.getNome();
    }

    public String getEmailPaciente() {
        return paciente.getEmail();
    }

    public StringProperty getDia() {
        return agenda.getDia();
    }

    public StringProperty getHorario() {
        return agenda.getHorario();
    }

    public static class Builder {
        protected Paciente paciente;
        protected Medico medico;
        protected Agenda agenda;

        public Consulta.Builder paciente(final Paciente paciente) {
            this.paciente = paciente;
            return this;
        }

        public Consulta.Builder medico(final Medico medico) {
            this.medico = medico;
            return this;
        }

        public Consulta.Builder agenda(final Agenda agenda) {
            this.agenda = agenda;
            return this;
        }

        public Consulta build() {
            return new Consulta(paciente, medico, agenda);
        }
    }
}
