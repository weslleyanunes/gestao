package com.gestao.service;

import com.gestao.dao.ProjetoDAO;
import com.gestao.dao.TarefaDAO;
import com.gestao.model.Projeto;
import com.gestao.model.Tarefa;

import java.util.List;

public class PainelService {

    private ProjetoDAO projetoDao;
    private TarefaDAO tarefaDao;

    public PainelService() {
        this.projetoDao = new ProjetoDAO();
        this.tarefaDao = new TarefaDAO();
    }

    // Altera para retornar a lista de projetos, em vez de apenas imprimir
    public List<Projeto> buscarProjetosComTarefas() {
        List<Projeto> projetos = projetoDao.buscarTodosProjetos();

        for (Projeto projeto : projetos) {
            List<Tarefa> tarefas = tarefaDao.buscarTarefasPorProjeto(projeto.getIdprojeto());
            // Anexa a lista de tarefas ao objeto Projeto para que a interface possa usá-la
            projeto.setTarefas(tarefas);
        }

        return projetos;
    }
}