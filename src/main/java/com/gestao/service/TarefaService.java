package com.gestao.service;

import com.gestao.model.Tarefa;
import com.gestao.repository.TarefaRepositorio;
import java.util.List;
import com.gestao.model.Projeto;
import com.gestao.repository.ProjetoRepositorio;

public class TarefaService {
    private TarefaRepositorio tarefaRepositorio = new TarefaRepositorio();
    private ProjetoRepositorio projetoRepositorio = new ProjetoRepositorio();

    public boolean criarTarefa(Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
            return false;
        }
        Projeto projeto = tarefa.getProjeto();
        if (projeto == null || projeto.getId() == 0 || projetoRepositorio.findById(projeto.getId()) == null) {
            return false;
        }
        tarefaRepositorio.adicionar(tarefa);
        // vincular ao projeto e regravar
        Projeto p = projetoRepositorio.findById(projeto.getId());
        p.getTarefas().add(tarefa);
        projetoRepositorio.adicionar(p);
    com.gestao.service.DebugLogger.info("Tarefa criada: " + tarefa.getTitulo() + " (Projeto: " + p.getNome() + ")");
        return true;
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepositorio.listar();
    }
}
