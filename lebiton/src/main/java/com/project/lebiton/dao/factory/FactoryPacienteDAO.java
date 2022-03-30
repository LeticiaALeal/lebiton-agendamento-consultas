package com.project.lebiton.dao.factory;

import com.project.lebiton.dao.PacienteDaoInterface;
import com.project.lebiton.dao.impl.PacienteDao;

public class FactoryPacienteDAO {

    public static PacienteDaoInterface criarPacientendao() {
        return new PacienteDao();
    }
}
