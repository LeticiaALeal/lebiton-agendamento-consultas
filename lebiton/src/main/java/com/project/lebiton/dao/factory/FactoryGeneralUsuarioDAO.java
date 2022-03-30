package com.project.lebiton.dao.factory;

import com.project.lebiton.dao.GeneralUsuarioDaoInterface;
import com.project.lebiton.dao.impl.GeneralUsuarioDao;

public class FactoryGeneralUsuarioDAO {

    public static GeneralUsuarioDaoInterface criarGeneralUsuariodao() {
        return new GeneralUsuarioDao();
    }
}
