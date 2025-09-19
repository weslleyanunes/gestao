package com.gestao.model;

import java.io.Serializable;

public class Relatorio implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String titulo;
    private String conteudo;
    private Tarefa tarefa;

    // getters e setters
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public Tarefa getTarefa() { return tarefa; }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}
