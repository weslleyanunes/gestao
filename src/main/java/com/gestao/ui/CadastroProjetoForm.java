package com.gestao.ui;

import com.gestao.model.Projeto;
import com.gestao.model.Usuario;
import com.gestao.service.ProjetoService;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.time.ZoneId;
import java.util.Date;

public class CadastroProjetoForm extends JDialog {
    private final JTextField txtNome = new JTextField(20);
    private final JTextArea txtDescricao = new JTextArea(5, 20);
    private final JSpinner prazoSpinner = new JSpinner(new SpinnerDateModel());
    private final Usuario usuario;
    private final ProjetoService projetoService;
    private final Runnable onSaved;

    public CadastroProjetoForm(Window owner, Usuario usuario, ProjetoService projetoService, Runnable onSaved) {
    super(owner, "Sistema de Gestão - Novo Projeto", ModalityType.APPLICATION_MODAL);
        this.usuario = usuario;
        this.projetoService = projetoService;
        this.onSaved = onSaved;

    setSize(480, 360);
        setLocationRelativeTo(owner);
    setResizable(true);

    JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);

        panel.add(new JLabel("Descrição:"));
    panel.add(new JScrollPane(txtDescricao));

    panel.add(new JLabel("Prazo:"));
    prazoSpinner.setEditor(new JSpinner.DateEditor(prazoSpinner, "dd/MM/yyyy"));
    panel.add(prazoSpinner);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarProjeto());
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btnSalvar);

        add(panel, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    private void salvarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome(txtNome.getText());
        projeto.setDescricao(txtDescricao.getText());
        projeto.setResponsavel(usuario);
    Date d = (Date) prazoSpinner.getValue();
    if (d != null) projeto.setPrazo(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        if (projetoService.criarProjeto(projeto)) {
            if (onSaved != null) onSaved.run();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar projeto");
        }
    }
}
