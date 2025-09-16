package com.gestao.model;

public class Equipe {

    private int idequipe;
    private String nome;
    private String descricao;

    // Construtor padrão
    public Equipe() {
    }

    // Construtor com todos os campos
    public Equipe(int idequipe, String nome, String descricao) {
        this.idequipe = idequipe;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getIdequipe() {
        return idequipe;
    }

    public void setIdequipe(int idequipe) {
        this.idequipe = idequipe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}