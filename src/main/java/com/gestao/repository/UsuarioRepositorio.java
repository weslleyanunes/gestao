package com.gestao.repository;

import com.gestao.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {
    private final DataStore store = DatabaseManager.getStore();

    public Usuario findById(int id) {
        return store.usuarios.get(id);
    }

    public Usuario findByLogin(String login) {
        return store.usuarios.values().stream()
                .filter(u -> u.getLogin() != null && u.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listar() {
        return new ArrayList<>(store.usuarios.values());
    }

    public void adicionar(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setId(store.nextUsuarioId++);
        }
        store.usuarios.put(usuario.getId(), usuario);
        DatabaseManager.persist();
    }

    public void delete(int id) {
        store.usuarios.remove(id);
        DatabaseManager.persist();
    }
}
