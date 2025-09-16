package com.gestao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    private String login;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Column(nullable = false, length = 50)
    private String perfil;

    @Column(length = 100)
    private String nome;

    @Column(length = 14)
    private String cpf;

    @Column(length = 100)
    private String cargo;

    @JsonIgnore
    @ManyToMany(mappedBy = "membros")
    private Set<Projeto> projetos = new HashSet<>();

    // Construtores
    public Usuario(long l, String nome2, String cpf2, String cargo2, String login2, String senha, String perfil2) {
    }

    public Usuario(String nome, String login, String senhaHash, String perfil, String cpf, String cargo) {
        this.nome = nome;
        this.login = login;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.cpf = cpf;
        this.cargo = cargo;
    }
    
    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senhaHash = senha; // Ajustado para o campo correto
    }

    public Usuario(int id, String nome, String cpf, String cargo, String login, String senha, String perfil) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.login = login;
        this.senhaHash = senha;
        this.perfil = perfil;
    }


    // Getters e Setters
    public int getIdusuario() {
        return id;
    }

    public void setIdusuario(int id) {
        this.id = id;
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
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

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }
}