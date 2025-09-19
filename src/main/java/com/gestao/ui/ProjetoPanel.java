package com.gestao.ui;

import com.gestao.model.Projeto;
import com.gestao.model.Usuario;
import com.gestao.service.ProjetoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProjetoPanel extends JPanel {
    private final JTable tabela = new JTable();
    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Nome", "Descrição", "Prazo"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final Usuario usuario;
    private final ProjetoService projetoService = new ProjetoService();

    public ProjetoPanel(Usuario usuario) {
        this.usuario = usuario;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        criarUI();
        carregarProjetos();
    }

    private void criarUI() {
        tabela.setModel(modelo);

        JButton btnNovo = new JButton("Novo Projeto");
        btnNovo.addActionListener(e -> novoProjeto());

    JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topo.setBorder(BorderFactory.createEmptyBorder(0,0,8,0));
        topo.add(btnNovo);

        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    private void carregarProjetos() {
        modelo.setRowCount(0);
        List<Projeto> projetos = projetoService.listarProjetos();
        for (Projeto p : projetos) {
            String prazo = p.getPrazo() != null ? p.getPrazo().toString() : "";
            modelo.addRow(new Object[]{p.getNome(), p.getDescricao(), prazo});
        }
    }

    private void novoProjeto() {
        CadastroProjetoForm dialog = new CadastroProjetoForm(SwingUtilities.getWindowAncestor(this), usuario, projetoService, this::carregarProjetos);
        dialog.setVisible(true);
    }
}
