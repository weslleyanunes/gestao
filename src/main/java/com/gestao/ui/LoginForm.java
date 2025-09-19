package com.gestao.ui;

import com.gestao.model.Usuario;
import com.gestao.service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private final JTextField txtLogin = new JTextField(20);
    private final JPasswordField txtSenha = new JPasswordField(20);

    public LoginForm() {
    setTitle("Sistema de Gestão - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    JPanel root = new JPanel(new GridBagLayout());
    root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.anchor = GridBagConstraints.WEST;

        Dimension fieldSize = new Dimension(220, 28);
        txtLogin.setPreferredSize(fieldSize);
        txtSenha.setPreferredSize(fieldSize);

        gc.gridx = 0; gc.gridy = 0; root.add(new JLabel("Login:"), gc);
        gc.gridx = 1; gc.gridy = 0; gc.fill = GridBagConstraints.HORIZONTAL; root.add(txtLogin, gc);
        gc.gridx = 0; gc.gridy = 1; gc.fill = GridBagConstraints.NONE; root.add(new JLabel("Senha:"), gc);
        gc.gridx = 1; gc.gridy = 1; gc.fill = GridBagConstraints.HORIZONTAL; root.add(txtSenha, gc);

    JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(e -> fazerLogin());
    // Enter ativa o botão Entrar
    getRootPane().setDefaultButton(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setForeground(UIManager.getColor("Component.linkColor"));
        btnCadastrar.addActionListener(e -> abrirCadastroUsuario());

        JButton btnRecuperar = new JButton("Esqueci minha senha");
        btnRecuperar.setBorderPainted(false);
        btnRecuperar.setContentAreaFilled(false);
        btnRecuperar.setFocusPainted(false);
        btnRecuperar.setForeground(UIManager.getColor("Component.linkColor"));
        btnRecuperar.addActionListener(e -> abrirRecuperacaoSenha());

        JPanel leftActions = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftActions.add(btnCadastrar);
        leftActions.add(btnRecuperar);
        leftActions.setOpaque(false);

        JPanel rightActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightActions.add(btnEntrar);
        rightActions.setOpaque(false);

        JPanel actions = new JPanel(new BorderLayout());
        actions.add(leftActions, BorderLayout.WEST);
        actions.add(rightActions, BorderLayout.EAST);

        gc.gridx = 0; gc.gridy = 2; gc.gridwidth = 2; gc.fill = GridBagConstraints.HORIZONTAL;
        root.add(actions, gc);

        setContentPane(root);
        pack();
        // largura convencional
        setSize(420, Math.max(getHeight(), 220));
        setLocationRelativeTo(null);
    }

    private void fazerLogin() {
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());

        Usuario usuario = new UsuarioService().validarLogin(login, senha);
        if (usuario != null) {
            com.gestao.service.DebugLogger.info("Login bem-sucedido: " + login + " (" + usuario.getCargo() + ")");
            dispose();
            new MainDashboard(usuario).setVisible(true);
        } else {
            com.gestao.service.DebugLogger.info("Falha no login para usuário: " + login);
            JOptionPane.showMessageDialog(this, "Login ou senha inválidos");
        }
    }

    private void abrirCadastroUsuario() {
        CadastroUsuarioForm form = new CadastroUsuarioForm();
        form.setLocationRelativeTo(this);
        form.setVisible(true);
    }

    private void abrirRecuperacaoSenha() {
        RecuperacaoSenhaForm form = new RecuperacaoSenhaForm();
        form.setLocationRelativeTo(this);
        form.setVisible(true);
    }
}
