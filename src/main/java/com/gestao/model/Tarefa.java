package com.gestao.model;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtarefa")
    private int id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StatusTarefa status;

    @ManyToOne
    @JoinColumn(name = "idprojeto", nullable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "idresponsavel")
    private Usuario responsavel;

    @Column(name = "data_inicio_prevista")
    private Date dataInicioPrevista;

    @Column(name = "data_termino_prevista")
    private Date dataTerminoPrevista;

    @Column(name = "data_inicio_real")
    private Date dataInicioReal;

    @Column(name = "data_termino_real")
    private Date dataTerminoReal;

    // Construtor padrão
    public Tarefa() {
    }

    // Construtor completo
    public Tarefa(String titulo, String descricao, StatusTarefa status, Projeto projeto, Usuario responsavel, Date dataInicioPrevista, Date dataTerminoPrevista) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.projeto = projeto;
        this.responsavel = responsavel;
        this.dataInicioPrevista = dataInicioPrevista;
        this.dataTerminoPrevista = dataTerminoPrevista;
    }

    // Getters e Setters
    public int getIdtarefa() {
        return id;
    }

    public void setIdtarefa(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Date getDataInicioPrevista() {
        return dataInicioPrevista;
    }

    public void setDataInicioPrevista(Date dataInicioPrevista) {
        this.dataInicioPrevista = dataInicioPrevista;
    }

    public Date getDataTerminoPrevista() {
        return dataTerminoPrevista;
    }

    public void setDataTerminoPrevista(Date dataTerminoPrevista) {
        this.dataTerminoPrevista = dataTerminoPrevista;
    }

    public Date getDataInicioReal() {
        return dataInicioReal;
    }

    public void setDataInicioReal(Date dataInicioReal) {
        this.dataInicioReal = dataInicioReal;
    }

    public Date getDataTerminoReal() {
        return dataTerminoReal;
    }

    public void setDataTerminoReal(Date dataTerminoReal) {
        this.dataTerminoReal = dataTerminoReal;
    }
}