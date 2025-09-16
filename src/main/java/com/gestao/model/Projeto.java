package com.gestao.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projetos")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprojeto")
    private int id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim_prevista")
    private Date dataTerminoPrevisto;

    @Column(length = 50)
    private String status;

    @Column(name = "idgerente")
    private int idGerente;

    @ManyToMany
    @JoinTable(
        name = "projeto_usuarios",
        joinColumns = @JoinColumn(name = "projeto_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> membros = new HashSet<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();

    // Construtores
    public Projeto() {
    }

    public Projeto(String nome, String descricao, Date dataInicio, Date dataTerminoPrevisto, String status, int idGerente) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevisto = dataTerminoPrevisto;
        this.status = status;
        this.idGerente = idGerente;
    }

    // Getters e Setters
    public int getIdprojeto() {
        return id;
    }

    public void setIdprojeto(int id) {
        this.id = id;
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

    public Date getDatainicio() {
        return dataInicio;
    }

    public void setDatainicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }



    public Date getDataterminoprevisto() {
        return dataTerminoPrevisto;
    }

    public void setDataterminoprevisto(Date dataTerminoPrevisto) {
        this.dataTerminoPrevisto = dataTerminoPrevisto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdgerente() {
        return idGerente;
    }

    public void setIdgerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public Set<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(Set<Usuario> membros) {
        this.membros = membros;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}