package com.project.lebiton.model.impl;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Usuario {

    protected StringProperty nome;
    protected String email;
    protected String telefone;
    protected String senha;

    public Usuario() {
    }

    public Usuario(final String email, final String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(final StringProperty nome, final String email, final String telefone, final String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Usuario(final StringProperty nome) {
        this.nome = nome;
    }

    public StringProperty getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = new SimpleStringProperty("");
        this.nome.set(nome);
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

}