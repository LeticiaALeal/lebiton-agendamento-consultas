package com.project.lebiton.factory;

import com.project.lebiton.model.UsuarioInterface;
import com.project.lebiton.model.impl.Administrador;
import com.project.lebiton.model.impl.Medico;
import com.project.lebiton.model.impl.Paciente;

public class UsuarioFactory {

    public static UsuarioInterface criar(final String email, final String senha) {

        if (email.contains("@medico.com")) return new Medico(email, senha);
        else if (email.contains("adm")) return new Administrador(email, senha);
        else return new Paciente(email, senha);
    }
}
