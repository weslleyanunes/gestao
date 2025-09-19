package com.gestao.ui;

import com.gestao.model.Tarefa;
import com.gestao.model.Projeto;
import com.gestao.service.TarefaService;
import com.gestao.service.ProjetoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.ZoneId;
import java.util.List;
import java.util.Date;

public class CadastroTarefaForm extends JDialog {
    private final JTextField tituloField = new JTextField(20);
    private final JTextArea descricaoArea = new JTextArea(5, 20);
    private final JComboBox<Projeto> projetoCombo = new JComboBox<>();
    private final JSpinner prazoSpinner = new JSpinner(new SpinnerDateModel());
    private final Runnable onSaved;

    public CadastroTarefaForm(Runnable onSaved) {
    super((Window) null, "Sistema de Gestão - Cadastro de Tarefa", ModalityType.APPLICATION_MODAL);
        this.onSaved = onSaved;
    setSize(460, 360);
    setLocationRelativeTo(null);
    setResizable(true);

    JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        panel.add(new JLabel("Projeto:"));
        projetoCombo.setRenderer(new DefaultListCellRenderer(){
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Projeto) setText(((Projeto) value).getNome());
                return this;
            }
        });
        carregarProjetos();
        panel.add(projetoCombo);

        panel.add(new JLabel("Título:"));
        panel.add(tituloField);

        panel.add(new JLabel("Descrição:"));
        panel.add(new JScrollPane(descricaoArea));

        panel.add(new JLabel("Prazo:"));
        prazoSpinner.setEditor(new JSpinner.DateEditor(prazoSpinner, "dd/MM/yyyy"));
        panel.add(prazoSpinner);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(this::cadastrarTarefa);
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(cadastrarButton);

        add(panel, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    private void cadastrarTarefa(ActionEvent e) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tituloField.getText());
        tarefa.setDescricao(descricaoArea.getText());
    Object p = projetoCombo.getSelectedItem();
        if (p instanceof Projeto) tarefa.setProjeto((Projeto) p);
    Date d = (Date) prazoSpinner.getValue();
    if (d != null) tarefa.setPrazo(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        TarefaService tarefaService = new TarefaService();
        if (tarefaService.criarTarefa(tarefa)) {
            JOptionPane.showMessageDialog(this, "Tarefa cadastrada com sucesso!");
            if (onSaved != null) onSaved.run();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao cadastrar tarefa.");
        }
    }

    private void carregarProjetos() {
        projetoCombo.removeAllItems();
        List<Projeto> projetos = new ProjetoService().listarProjetos();
        for (Projeto p : projetos) projetoCombo.addItem(p);
    }
}
