package com.gestao.ui;

import com.gestao.model.Tarefa;
import com.gestao.model.Usuario;
import com.gestao.service.TarefaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.gestao.model.Projeto;
import com.gestao.service.ProjetoService;

public class TarefaPanel extends JPanel {
    private final JTable tabela = new JTable();
    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Projeto", "Título", "Status", "Prazo"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final TarefaService tarefaService = new TarefaService();
    private final ProjetoService projetoService = new ProjetoService();
    private final JComboBox<Projeto> filtroProjeto = new JComboBox<>();

    private final Usuario usuario;

    public TarefaPanel(Usuario usuario) {
        this.usuario = usuario;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        criarUI();
        carregarTarefas();
    }

    private void criarUI() {
        tabela.setModel(modelo);

        JButton btnNova = new JButton("Nova Tarefa");
        btnNova.addActionListener(e -> novaTarefa());

        filtroProjeto.setRenderer(new DefaultListCellRenderer(){
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Projeto) setText(((Projeto) value).getNome());
                if (value == null) setText("Todos os projetos");
                return this;
            }
        });
        carregarProjetos();
        filtroProjeto.addActionListener(e -> carregarTarefas());

    JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topo.setBorder(BorderFactory.createEmptyBorder(0,0,8,0));
        topo.add(new JLabel("Projeto:"));
        topo.add(filtroProjeto);
        topo.add(btnNova);

        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    private void carregarTarefas() {
        modelo.setRowCount(0);
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        Projeto sel = (Projeto) filtroProjeto.getSelectedItem();
        for (Tarefa t : tarefas) {
            if (sel != null && t.getProjeto() != null && t.getProjeto().getId() != sel.getId()) continue;
            // Se usuário é Membro, só pode ver tarefas nas quais está como membro
            if (usuario != null && usuario.isMembro()) {
                boolean participa = t.getMembros() != null && t.getMembros().stream().anyMatch(u -> u.getId() == usuario.getId());
                if (!participa) continue;
            }
            String projNome = t.getProjeto() != null ? t.getProjeto().getNome() : "";
            String status = t.getStatus() != null ? t.getStatus().name() : "";
            String prazo = t.getPrazo() != null ? t.getPrazo().toString() : "";
            modelo.addRow(new Object[]{projNome, t.getTitulo(), status, prazo});
        }
    }

    private void novaTarefa() {
        CadastroTarefaForm form = new CadastroTarefaForm(this::carregarTarefas);
        Window owner = SwingUtilities.getWindowAncestor(this);
        form.setLocationRelativeTo(owner);
        form.setVisible(true);
    }

    private void carregarProjetos() {
        filtroProjeto.removeAllItems();
        filtroProjeto.addItem(null); // todos
        for (Projeto p : projetoService.listarProjetos()) filtroProjeto.addItem(p);
    }
}
