package com.project.lebiton.dao;

import com.project.lebiton.model.impl.Agenda;
import com.project.lebiton.model.impl.Consulta;

import java.util.List;

public interface MedicoDaoInterface {

    List<Agenda> listarHorarios(final String email);
    List<Consulta> listarConsultas(final String email);

}
