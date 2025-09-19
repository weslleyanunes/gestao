package com.gestao.repository;

import com.gestao.model.Projeto;
import com.gestao.model.Relatorio;
import com.gestao.model.Tarefa;
import com.gestao.model.Usuario;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<Integer, Usuario> usuarios = new HashMap<>();
    Map<Integer, Projeto> projetos = new HashMap<>();
    Map<Integer, Tarefa> tarefas = new HashMap<>();
    Map<Integer, Relatorio> relatorios = new HashMap<>();
    Map<String, Integer> tokensRecuperacao = new HashMap<>();

    int nextUsuarioId = 1;
    int nextProjetoId = 1;
    int nextTarefaId = 1;
    int nextRelatorioId = 1;
}
