package com.project.lebiton.dao.factory;

import com.project.lebiton.dao.impl.AdministradorDao;
import com.project.lebiton.dao.AdministradorDaoInterface;

public class FactoryAdministradorDAO {

    public static AdministradorDaoInterface criarAdministradordao() { return new AdministradorDao(); }
}
