package com.gestao.service;

import com.gestao.dao.ProjetoDAO;
import com.gestao.model.Projeto;

public class ProjetoService {
    private ProjetoDAO projetoDAO = new ProjetoDAO();

    // Lógica para criar um projeto
    public boolean criarProjeto(Projeto projeto) {
        if (projeto.getNome() == null || projeto.getNome().isEmpty()) {
            return false;
        }
        // Aqui poderia haver mais validações de regras de negócio
        return projetoDAO.adicionar(projeto);
    }

    // Outros métodos de negócio
    // ...
}