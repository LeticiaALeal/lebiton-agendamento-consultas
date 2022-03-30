package com.project.lebiton.dao;

import com.project.lebiton.model.impl.*;

import java.util.List;

public interface AdministradorDaoInterface {

    List<Medico> listarMedicos();
    List<Agenda> listarAgenda();
    List<AgendaMedico> listarAgendaMedico();
    List<Paciente> listarPacientes();
    List<Consulta> listarConsultas();

    boolean createAgendaMedico(final AgendaMedico agMedico);
}
