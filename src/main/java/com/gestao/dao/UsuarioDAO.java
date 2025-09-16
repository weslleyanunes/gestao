package com.gestao.dao;

import com.gestao.model.Usuario;
import com.gestao.util.SenhaUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public void adicionar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, cpf, cargo, login, senha, perfil) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getCargo());
            stmt.setString(4, usuario.getLogin());
            String senhaHash = SenhaUtil.hashSenha(usuario.getSenhaHash());
            stmt.setString(5, senhaHash);
            stmt.setString(6, usuario.getPerfil());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
            throw e;
        }
    }

    public Usuario validarLogin(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaHashDoBanco = rs.getString("senha_hash");
                    if (SenhaUtil.verificarSenha(senha, senhaHashDoBanco)) {
                        Usuario usuario = new Usuario(0, senhaHashDoBanco, senhaHashDoBanco, senhaHashDoBanco, senhaHashDoBanco, senhaHashDoBanco, senhaHashDoBanco);
                        usuario.setIdusuario(rs.getInt("idusuario"));
                        usuario.setNome(rs.getString("nome"));
                        usuario.setLogin(rs.getString("login"));
                        usuario.setSenhaHash(rs.getString("senha_hash"));
                        usuario.setPerfil(rs.getString("perfil"));
                        usuario.setCargo(rs.getString("cargo"));
                        usuario.setCpf(rs.getString("cpf"));
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
            throw e;
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE idusuario = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(id, sql, sql, sql, sql, sql, sql);
                    usuario.setIdusuario(rs.getInt("idusuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenhaHash(rs.getString("senha_hash"));
                    usuario.setPerfil(rs.getString("perfil"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }
}