package com.gestao.repository;

import com.gestao.model.Relatorio;

import java.util.ArrayList;
import java.util.List;

public class RelatorioRepositorio {
    private final DataStore store = DatabaseManager.getStore();

    public Relatorio findById(int id) {
        return store.relatorios.get(id);
    }

    public List<Relatorio> listar() {
        return new ArrayList<>(store.relatorios.values());
    }

    public void adicionar(Relatorio relatorio) {
        if (relatorio.getId() == 0) {
            relatorio.setId(store.nextRelatorioId++);
        }
        store.relatorios.put(relatorio.getId(), relatorio);
        DatabaseManager.persist();
    }

    public void delete(int id) {
        store.relatorios.remove(id);
        DatabaseManager.persist();
    }
}
