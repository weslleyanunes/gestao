package com.gestao.repository;

import com.gestao.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaRepositorio {
    private final DataStore store = DatabaseManager.getStore();

    public Tarefa findById(int id) {
        return store.tarefas.get(id);
    }

    public List<Tarefa> listar() {
        return new ArrayList<>(store.tarefas.values());
    }

    public void adicionar(Tarefa tarefa) {
        if (tarefa.getId() == 0) {
            tarefa.setId(store.nextTarefaId++);
        }
        store.tarefas.put(tarefa.getId(), tarefa);
        DatabaseManager.persist();
    }

    public void delete(int id) {
        store.tarefas.remove(id);
        DatabaseManager.persist();
    }
}
