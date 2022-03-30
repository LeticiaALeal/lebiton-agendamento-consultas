package com.project.lebiton.facade;

import com.project.lebiton.model.UsuarioInterface;

public class LoginFacade {

    public boolean logar(final UsuarioInterface usuario) {
        return usuario.logar();
    }
}
