package com.gestao.dao;

import com.gestao.model.Projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public List<Projeto> buscarTodosProjetos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projetos";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdprojeto(rs.getInt("idprojeto"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDatainicio(rs.getDate("data_inicio"));
                projeto.setDataterminoprevisto(rs.getDate("data_fim_prevista"));
                projeto.setStatus(rs.getString("status"));
                projeto.setIdgerente(rs.getInt("idgerente"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os projetos: " + e.getMessage());
        }
        return projetos;
    }

    public boolean adicionar(Projeto projeto) {
        String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_fim_prevista, status, idgerente) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, new java.sql.Date(projeto.getDatainicio().getTime()));
            stmt.setDate(4, new java.sql.Date(projeto.getDataterminoprevisto().getTime()));
            stmt.setString(5, projeto.getStatus());
            stmt.setInt(6, projeto.getIdgerente());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar projeto: " + e.getMessage());
            return false;
        }
    }
}