package com.project.lebiton.model.impl;

import com.project.lebiton.dao.GeneralUsuarioDaoInterface;
import com.project.lebiton.dao.factory.FactoryGeneralUsuarioDAO;
import com.project.lebiton.model.UsuarioInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Paciente extends Usuario implements UsuarioInterface {
    private String cpf;

    public Paciente() {
    }

    public Paciente(final String email, final String senha) {
        super(email, senha);
    }

    public Paciente(final StringProperty nome, final String cpf, final String telefone,
                    final String senha, final String email) {

        super(nome, email, telefone, senha);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public boolean logar() {
        final GeneralUsuarioDaoInterface dao = FactoryGeneralUsuarioDAO.criarGeneralUsuariodao();
        return dao.login(this.email, this.senha);
    }

    @Override
    public boolean createUser(UsuarioInterface usuario) throws Exception {
        final GeneralUsuarioDaoInterface dao = FactoryGeneralUsuarioDAO.criarGeneralUsuariodao();
        return dao.createUser(usuario);
    }

    public static class Builder {

        protected StringProperty nome;
        protected String email;
        protected String telefone;
        protected String senha;
        protected String cpf;

        public Builder nome(final String nome) {
            this.nome = new SimpleStringProperty("");
            this.nome.set(nome);
            return this;
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }

        public Builder telefone(final String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Builder senha(final String senha) {
            this.senha = senha;
            return this;
        }

        public Builder cpf(final String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Paciente build() {
            return new Paciente(nome, cpf, telefone, senha, email);
        }
    }
}