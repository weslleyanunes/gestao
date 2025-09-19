package com.gestao.service;

import com.gestao.model.Projeto;
import com.gestao.repository.ProjetoRepositorio;
import java.util.List;

public class ProjetoService {
    private ProjetoRepositorio projetoRepositorio = new ProjetoRepositorio();

    public boolean criarProjeto(Projeto projeto) {
        if (projeto.getNome() == null || projeto.getNome().isEmpty()) {
            return false;
        }
        projetoRepositorio.adicionar(projeto);
        com.gestao.service.DebugLogger.info("Projeto criado: " + projeto.getNome());
        return true;
    }

    public List<Projeto> listarProjetos() {
        return projetoRepositorio.listar();
    }

    public void adicionarTarefaAoProjeto(Projeto projeto, com.gestao.model.Tarefa tarefa) {
        if (projeto == null || tarefa == null) return;
        projeto.getTarefas().add(tarefa);
        projetoRepositorio.adicionar(projeto); // regrava projeto atualizado
    }
}
