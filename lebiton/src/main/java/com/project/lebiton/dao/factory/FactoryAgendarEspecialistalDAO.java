package com.project.lebiton.dao.factory;

import com.project.lebiton.dao.AgendarEspecialistaDaoInterface;
import com.project.lebiton.dao.impl.AgendaEspecialistaDao;

public class FactoryAgendarEspecialistalDAO {

    public static AgendarEspecialistaDaoInterface criarAgendarEspecialistaDao() {
        return new AgendaEspecialistaDao();
    }
}
