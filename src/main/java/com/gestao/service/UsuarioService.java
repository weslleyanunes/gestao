package com.gestao.service;

import com.gestao.model.Usuario;
import com.gestao.repository.UsuarioRepositorio;

public class UsuarioService {
    private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public boolean criarUsuario(Usuario usuario) {
        if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
            return false;
        }
        if (usuarioRepositorio.findByLogin(usuario.getLogin()) != null) {
            return false;
        }
        if (usuario.getSenhaHash() != null && !usuario.getSenhaHash().isEmpty()) {
            usuario.setSenhaHash(PasswordUtils.hash(usuario.getSenhaHash()));
        }
        usuarioRepositorio.adicionar(usuario);
        return true;
    }

    public Usuario validarLogin(String login, String senha) {
        Usuario usuario = usuarioRepositorio.findByLogin(login);
        if (usuario != null && PasswordUtils.verify(senha, usuario.getSenhaHash())) {
            // Normaliza cargo padrão para usuários antigos sem cargo definido
            if (usuario.getCargo() == null || usuario.getCargo().isEmpty()) {
                usuario.setCargo("Membro");
                usuarioRepositorio.adicionar(usuario); // upsert
            }
            return usuario;
        }
        return null;
    }

    public java.util.List<Usuario> listarUsuarios() {
        return usuarioRepositorio.listar();
    }
}
