package com.gestao.ui;

import com.gestao.service.RecuperacaoSenhaService; // Importação adicionada
import javax.swing.*;
import java.awt.event.ActionEvent;

public class RecuperacaoSenhaForm extends JFrame {
    private JTextField emailField;
    private JButton enviarButton;
    private JTextField tokenField;
    private JPasswordField novaSenhaField;
    private JButton redefinirButton;

    public RecuperacaoSenhaForm() {
    setTitle("Sistema de Gestão - Recuperação de Senha");
        setSize(300, 200);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        panel.add(emailField);

    enviarButton = new JButton("Enviar Token");
        enviarButton.addActionListener(this::enviarToken);
        panel.add(enviarButton);

    panel.add(new JLabel("Token:"));
    tokenField = new JTextField(20);
    panel.add(tokenField);

    panel.add(new JLabel("Nova Senha:"));
    novaSenhaField = new JPasswordField(20);
    panel.add(novaSenhaField);

    redefinirButton = new JButton("Redefinir Senha");
    redefinirButton.addActionListener(this::redefinirSenha);
    panel.add(redefinirButton);

        add(panel);
    }

    private void enviarToken(ActionEvent e) {
        String email = emailField.getText();
        RecuperacaoSenhaService service = new RecuperacaoSenhaService();
        String token = service.gerarTokenRecuperacao(email);
        if (token != null) {
            JOptionPane.showMessageDialog(this, "Token enviado para o email: " + email);
        } else {
            JOptionPane.showMessageDialog(this, "Email não encontrado.");
        }
    }

    private void redefinirSenha(ActionEvent e) {
        String token = tokenField.getText();
        String novaSenha = new String(novaSenhaField.getPassword());
        if (token.isEmpty() || novaSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe token e nova senha.");
            return;
        }
        RecuperacaoSenhaService service = new RecuperacaoSenhaService();
        boolean ok = service.redefinirSenha(token, novaSenha);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Senha redefinida com sucesso.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Token inválido.");
        }
    }
}
