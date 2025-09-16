package com.gestao.dao;

import com.gestao.model.Equipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {

    public boolean adicionar(Equipe equipe) {
        String sql = "INSERT INTO equipes (nome, descricao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipe.getNome());
            stmt.setString(2, equipe.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar equipe: " + e.getMessage());
            return false;
        }
    }

    public List<Equipe> buscarTodasEquipes() {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipes ORDER BY idequipe DESC";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Equipe equipe = new Equipe();
                equipe.setIdequipe(rs.getInt("idequipe"));
                equipe.setNome(rs.getString("nome"));
                equipe.setDescricao(rs.getString("descricao"));
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar equipes: " + e.getMessage());
        }
        return equipes;
    }

    public boolean atualizar(Equipe equipe) {
        String sql = "UPDATE equipes SET nome = ?, descricao = ? WHERE idequipe = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipe.getNome());
            stmt.setString(2, equipe.getDescricao());
            stmt.setInt(3, equipe.getIdequipe());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar equipe: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int idequipe) {
        String sql = "DELETE FROM equipes WHERE idequipe = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idequipe);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir equipe: " + e.getMessage());
            return false;
        }
    }
}