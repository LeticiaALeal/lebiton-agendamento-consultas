package com.project.lebiton.model.impl;

import com.project.lebiton.dao.GeneralUsuarioDaoInterface;
import com.project.lebiton.dao.factory.FactoryGeneralUsuarioDAO;
import com.project.lebiton.model.UsuarioInterface;

public class Administrador extends Usuario implements UsuarioInterface {

    public Administrador() {
    }

    public Administrador(final String email, final String senha) {
        super(email, senha);
    }

    @Override
    public boolean logar() {
        final GeneralUsuarioDaoInterface dao = FactoryGeneralUsuarioDAO.criarGeneralUsuariodao();
        return dao.login(this.email, this.senha);
    }

    @Override
    public boolean createUser(UsuarioInterface usuario) {
        return false;
    }

}