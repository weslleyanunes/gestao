package com.gestao.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private String cpf;
    private String cargo;
    private String login;
    private String senhaHash;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    // Helpers de cargo
    public boolean isAdministrador() { return "Administrador".equalsIgnoreCase(cargo); }
    public boolean isGerente() { return "Gerente".equalsIgnoreCase(cargo); }
    public boolean isMembro() { return "Membro".equalsIgnoreCase(cargo); }
    public boolean isDesenvolvedor() { return "Desenvolvedor".equalsIgnoreCase(cargo); }
}
