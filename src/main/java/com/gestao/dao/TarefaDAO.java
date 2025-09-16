package com.gestao.dao;

import com.gestao.model.StatusTarefa;
import com.gestao.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public List<Tarefa> buscarTarefasPorProjeto(int idprojeto) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE idprojeto = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idprojeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setIdtarefa(rs.getInt("idtarefa"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setStatus(StatusTarefa.valueOf(rs.getString("status")));
                    // O responsável será um objeto Usuario, a ser buscado separadamente se necessário
                    // tarefa.setResponsavel(rs.getInt("idresponsavel"));
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefas por projeto: " + e.getMessage());
        }
        return tarefas;
    }
}