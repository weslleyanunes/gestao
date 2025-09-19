package com.gestao.ui;

import com.gestao.model.Projeto;
import com.gestao.model.Tarefa;
import com.gestao.model.Usuario;
import com.gestao.repository.ProjetoRepositorio;
import com.gestao.repository.TarefaRepositorio;
import com.gestao.repository.UsuarioRepositorio;
import com.gestao.service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.*;

public class UsuariosPanel extends JPanel {
    private final JTable tabela = new JTable();
    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Login", "Cargo"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return column == 3; }
    };
    private final UsuarioService usuarioService = new UsuarioService();

    // Atribuições
    private final JComboBox<Usuario> usuarioCombo = new JComboBox<>();
    private final JList<Projeto> projetosDisponiveis = new JList<>();
    private final JList<Projeto> projetosAtribuidos = new JList<>();
    private final JList<Tarefa> tarefasDisponiveis = new JList<>();
    private final JList<Tarefa> tarefasAtribuidas = new JList<>();

    public UsuariosPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        // Topo: tabela de usuários com edição de cargo
        tabela.setModel(modelo);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JComboBox<>(new String[]{
                "Administrador", "Gerente", "Membro", "Desenvolvedor"
        })));
        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JButton salvarBtn = new JButton("Salvar alterações de cargos");
        salvarBtn.addActionListener(e -> salvarAlteracoes());
        top.add(new JScrollPane(tabela), BorderLayout.CENTER);
        top.add(salvarBtn, BorderLayout.SOUTH);

        // Centro: atribuições de projetos/tarefas
        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.add(new JLabel("Usuário:"));
        usuarioCombo.setRenderer(new DefaultListCellRenderer(){
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Usuario) setText(((Usuario) value).getNome());
                return this;
            }
        });
        usuarioCombo.addActionListener(e -> carregarAtribuicoes());
        header.add(usuarioCombo);
        center.add(header, BorderLayout.NORTH);

        JPanel lists = new JPanel(new GridLayout(2, 1, 8, 8));
        // Projetos
        JPanel projPanel = new JPanel(new BorderLayout(8,8));
        projPanel.add(new JLabel("Projetos"), BorderLayout.NORTH);
        JPanel projLists = new JPanel(new GridLayout(1,3,8,8));
        projLists.add(new JScrollPane(projetosDisponiveis));
        JPanel projBtns = new JPanel(); projBtns.setLayout(new BoxLayout(projBtns, BoxLayout.Y_AXIS));
        JButton addProj = new JButton("→"); JButton remProj = new JButton("←");
        addProj.addActionListener(e -> moverProjeto(projetosDisponiveis, projetosAtribuidos));
        remProj.addActionListener(e -> moverProjeto(projetosAtribuidos, projetosDisponiveis));
        projBtns.add(Box.createVerticalGlue()); projBtns.add(addProj); projBtns.add(Box.createVerticalStrut(8)); projBtns.add(remProj); projBtns.add(Box.createVerticalGlue());
        projLists.add(projBtns);
        projLists.add(new JScrollPane(projetosAtribuidos));
        projPanel.add(projLists, BorderLayout.CENTER);

        // Tarefas
        JPanel tarPanel = new JPanel(new BorderLayout(8,8));
        tarPanel.add(new JLabel("Tarefas"), BorderLayout.NORTH);
        JPanel tarLists = new JPanel(new GridLayout(1,3,8,8));
        tarLists.add(new JScrollPane(tarefasDisponiveis));
        JPanel tarBtns = new JPanel(); tarBtns.setLayout(new BoxLayout(tarBtns, BoxLayout.Y_AXIS));
        JButton addTar = new JButton("→"); JButton remTar = new JButton("←");
        addTar.addActionListener(e -> moverTarefa(tarefasDisponiveis, tarefasAtribuidas));
        remTar.addActionListener(e -> moverTarefa(tarefasAtribuidas, tarefasDisponiveis));
        tarBtns.add(Box.createVerticalGlue()); tarBtns.add(addTar); tarBtns.add(Box.createVerticalStrut(8)); tarBtns.add(remTar); tarBtns.add(Box.createVerticalGlue());
        tarLists.add(tarBtns);
        tarLists.add(new JScrollPane(tarefasAtribuidas));
        tarPanel.add(tarLists, BorderLayout.CENTER);

        lists.add(projPanel);
        lists.add(tarPanel);
        center.add(lists, BorderLayout.CENTER);

        JButton salvarAtrib = new JButton("Salvar atribuições");
        salvarAtrib.addActionListener(e -> salvarAtribuicoes());
        center.add(salvarAtrib, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        carregar();
        carregarUsuariosCombo();
    }

    private void carregar() {
        modelo.setRowCount(0);
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getId(), u.getNome(), u.getLogin(), u.getCargo()});
        }
    }

    private void carregarUsuariosCombo() {
        usuarioCombo.removeAllItems();
        for (Usuario u : usuarioService.listarUsuarios()) usuarioCombo.addItem(u);
        carregarAtribuicoes();
    }

    private void carregarAtribuicoes() {
        Usuario u = (Usuario) usuarioCombo.getSelectedItem();
        if (u == null) return;
        ProjetoRepositorio pr = new ProjetoRepositorio();
        TarefaRepositorio tr = new TarefaRepositorio();
        DefaultListModel<Projeto> dispP = new DefaultListModel<>();
        DefaultListModel<Projeto> atribP = new DefaultListModel<>();
        for (Projeto p : pr.listar()) {
            if (p.getMembros() != null && p.getMembros().stream().anyMatch(m -> m.getId() == u.getId())) atribP.addElement(p);
            else dispP.addElement(p);
        }
        projetosDisponiveis.setModel(dispP);
        projetosAtribuidos.setModel(atribP);

        DefaultListModel<Tarefa> dispT = new DefaultListModel<>();
        DefaultListModel<Tarefa> atribT = new DefaultListModel<>();
        for (Tarefa t : tr.listar()) {
            if (t.getMembros() != null && t.getMembros().stream().anyMatch(m -> m.getId() == u.getId())) atribT.addElement(t);
            else dispT.addElement(t);
        }
        tarefasDisponiveis.setModel(dispT);
        tarefasAtribuidas.setModel(atribT);

        projetosDisponiveis.setCellRenderer(new DefaultListCellRenderer(){
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Projeto) setText(((Projeto) value).getNome());
                return this;
            }
        });
        projetosAtribuidos.setCellRenderer(projetosDisponiveis.getCellRenderer());
        tarefasDisponiveis.setCellRenderer(new DefaultListCellRenderer(){
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Tarefa) setText(((Tarefa) value).getTitulo());
                return this;
            }
        });
        tarefasAtribuidas.setCellRenderer(tarefasDisponiveis.getCellRenderer());
    }

    private static <T> void moverProjeto(JList<Projeto> origem, JList<Projeto> destino) {
        List<Projeto> sel = origem.getSelectedValuesList();
        DefaultListModel<Projeto> mOrig = (DefaultListModel<Projeto>) origem.getModel();
        DefaultListModel<Projeto> mDest = (DefaultListModel<Projeto>) destino.getModel();
        for (Projeto p : sel) { mDest.addElement(p); mOrig.removeElement(p); }
    }

    private static void moverTarefa(JList<Tarefa> origem, JList<Tarefa> destino) {
        List<Tarefa> sel = origem.getSelectedValuesList();
        DefaultListModel<Tarefa> mOrig = (DefaultListModel<Tarefa>) origem.getModel();
        DefaultListModel<Tarefa> mDest = (DefaultListModel<Tarefa>) destino.getModel();
        for (Tarefa t : sel) { mDest.addElement(t); mOrig.removeElement(t); }
    }

    private void salvarAlteracoes() {
        UsuarioRepositorio repo = new UsuarioRepositorio();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int id = (int) modelo.getValueAt(i, 0);
            String cargo = (String) modelo.getValueAt(i, 3);
            Usuario u = repo.findById(id);
            if (u != null) {
                u.setCargo(cargo);
                repo.adicionar(u);
            }
        }
        JOptionPane.showMessageDialog(this, "Cargos atualizados.");
        carregar();
        carregarUsuariosCombo();
    }

    private void salvarAtribuicoes() {
        Usuario u = (Usuario) usuarioCombo.getSelectedItem();
        if (u == null) return;
        ProjetoRepositorio pr = new ProjetoRepositorio();
        TarefaRepositorio tr = new TarefaRepositorio();

        // Persistir membros de projetos
        for (Projeto p : pr.listar()) {
            List<Usuario> membros = p.getMembros();
            if (membros == null) membros = new java.util.ArrayList<>();
            boolean deveEstar = contains(projetosAtribuidos.getModel(), p);
            boolean esta = membros.stream().anyMatch(m -> m.getId() == u.getId());
            if (deveEstar && !esta) { membros.add(u); p.setMembros(membros); pr.adicionar(p); }
            if (!deveEstar && esta) { membros.removeIf(m -> m.getId() == u.getId()); p.setMembros(membros); pr.adicionar(p); }
        }

        // Persistir membros de tarefas
        for (Tarefa t : tr.listar()) {
            List<Usuario> membros = t.getMembros();
            if (membros == null) membros = new java.util.ArrayList<>();
            boolean deveEstar = contains(tarefasAtribuidas.getModel(), t);
            boolean esta = membros.stream().anyMatch(m -> m.getId() == u.getId());
            if (deveEstar && !esta) { membros.add(u); t.setMembros(membros); tr.adicionar(t); }
            if (!deveEstar && esta) { membros.removeIf(m -> m.getId() == u.getId()); t.setMembros(membros); tr.adicionar(t); }
        }

        JOptionPane.showMessageDialog(this, "Atribuições salvas.");
        carregarAtribuicoes();
    }

    private static <T> boolean contains(ListModel<T> model, T value) {
        for (int i = 0; i < model.getSize(); i++) {
            T el = model.getElementAt(i);
            if (value instanceof Projeto && el instanceof Projeto) {
                if (((Projeto) el).getId() == ((Projeto) value).getId()) return true;
            } else if (value instanceof Tarefa && el instanceof Tarefa) {
                if (((Tarefa) el).getId() == ((Tarefa) value).getId()) return true;
            } else if (Objects.equals(el, value)) {
                return true;
            }
        }
        return false;
    }
}
