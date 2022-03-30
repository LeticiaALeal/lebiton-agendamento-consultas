package com.project.lebiton.dao;

import com.project.lebiton.model.impl.AgendaMedico;

import java.util.List;

public interface AgendarEspecialistaDaoInterface {

    List<AgendaMedico> listar();
    List<AgendaMedico> listaFiltrada(final String especialidade);
}
