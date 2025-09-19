package com.gestao.service;

import com.gestao.model.Usuario;
import com.gestao.repository.UsuarioRepositorio;
import com.gestao.repository.DatabaseManager;
import java.util.UUID;

public class RecuperacaoSenhaService {
    private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public String gerarTokenRecuperacao(String email) {
        Usuario usuario = usuarioRepositorio.findByLogin(email);
        if (usuario != null) {
            String token = UUID.randomUUID().toString();
            DatabaseManager.putResetToken(token, usuario.getId());
            return token;
        }
        return null;
    }

    public boolean redefinirSenha(String token, String novaSenha) {
        Integer userId = DatabaseManager.consumeResetToken(token);
        if (userId == null) return false;

        Usuario usuario = usuarioRepositorio.findById(userId);
        if (usuario == null) return false;

        usuario.setSenhaHash(PasswordUtils.hash(novaSenha));
        DatabaseManager.persist();
        return true;
    }
}
