package com.gestao.ui;

import com.gestao.dao.UsuarioDAO;
import com.gestao.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private UsuarioDAO usuarioDAO;

    public LoginUI() {
        initComponents();
        setupUI();
        usuarioDAO = new UsuarioDAO(); // Inicializa o DAO
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }

    private void initComponents() {
        setTitle("Sistema de Gestão de Projetos - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Painel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 240, 240));
        JLabel titleLabel = new JLabel("Sistema de Gestão de Projetos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180));
        titlePanel.add(titleLabel);

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Usuário
        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        usernameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        // Senha
        JLabel passLabel = new JLabel("Senha:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // Adicionar componentes ao painel principal
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void setupUI() {
        try {
            // Método correto para obter o look and feel do sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos.",
                    "Erro de Login",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = null;
        try {
            usuario = usuarioDAO.validarLogin(username, password);
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao conectar ao banco de dados: " + ex.getMessage(),
                    "Erro de Login",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuario != null) {
            JOptionPane.showMessageDialog(this,
                    "Login realizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            openMainApplication(usuario);
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos.",
                    "Erro de Login",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openMainApplication(Usuario usuario) {
        MainApplicationUI mainApp = new MainApplicationUI(usuario);
        mainApp.setVisible(true);
    }
}