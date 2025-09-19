package com.gestao.repository;

import com.gestao.model.Projeto;

import java.util.ArrayList;
import java.util.List;

public class ProjetoRepositorio {
    private final DataStore store = DatabaseManager.getStore();

    public Projeto findById(int id) {
        return store.projetos.get(id);
    }

    public List<Projeto> listar() {
        return new ArrayList<>(store.projetos.values());
    }

    public void adicionar(Projeto projeto) {
        if (projeto.getId() == 0) {
            projeto.setId(store.nextProjetoId++);
        }
        store.projetos.put(projeto.getId(), projeto);
        DatabaseManager.persist();
    }

    public void delete(int id) {
        store.projetos.remove(id);
        DatabaseManager.persist();
    }
}
