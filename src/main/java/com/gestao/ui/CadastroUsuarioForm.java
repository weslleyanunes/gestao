package com.gestao.ui;

import com.gestao.dao.UsuarioDAO;
import com.gestao.model.Usuario;
import com.gestao.ui.components.CustomButton;
import com.gestao.ui.components.CustomPanel;
import com.gestao.ui.components.CustomTextField;
import com.gestao.ui.themes.ModernTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class CadastroUsuarioForm extends JFrame {

    private CustomTextField nomeField, cpfField, cargoField, loginField;
    private JPasswordField senhaField;
    private JComboBox<String> perfilComboBox;
    private UsuarioDAO usuarioDAO;

    public CadastroUsuarioForm() {
        usuarioDAO = new UsuarioDAO();
        initUI();
    }

    private void initUI() {
        setTitle("Cadastro de Usuário");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(ModernTheme.BACKGROUND);
        setLayout(new GridBagLayout());

        CustomPanel mainPanel = new CustomPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(320, 520));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 16, 8, 16);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Criar Conta");
        titleLabel.setFont(ModernTheme.FONT_TITLE);
        titleLabel.setForeground(ModernTheme.TEXT_PRIMARY);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 16, 20, 16);
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 2; // Corrigido para 2
        gbc.insets = new Insets(8, 16, 8, 16);

        nomeField = new CustomTextField();
        nomeField.setPlaceholder("Nome Completo");
        gbc.gridy = 1;
        mainPanel.add(nomeField, gbc);

        cpfField = new CustomTextField();
        cpfField.setPlaceholder("CPF");
        gbc.gridy = 2;
        mainPanel.add(cpfField, gbc);

        cargoField = new CustomTextField();
        cargoField.setPlaceholder("Cargo");
        gbc.gridy = 3;
        mainPanel.add(cargoField, gbc);

        loginField = new CustomTextField();
        loginField.setPlaceholder("Login");
        gbc.gridy = 4;
        mainPanel.add(loginField, gbc);

        senhaField = new JPasswordField();
        senhaField.setToolTipText("Senha");
        senhaField.setFont(ModernTheme.FONT_BODY);
        senhaField.setBackground(ModernTheme.BACKGROUND);
        senhaField.setForeground(ModernTheme.TEXT_PRIMARY);
        senhaField.setCaretColor(ModernTheme.TEXT_PRIMARY);
        senhaField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ModernTheme.BORDER, 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        gbc.gridy = 5;
        mainPanel.add(senhaField, gbc);

        perfilComboBox = new JComboBox<>(new String[]{"Desenvolvedor", "Gerente"});
        perfilComboBox.setFont(ModernTheme.FONT_BODY);
        perfilComboBox.setBackground(ModernTheme.BACKGROUND);
        perfilComboBox.setForeground(ModernTheme.TEXT_PRIMARY);
        gbc.gridy = 6;
        mainPanel.add(perfilComboBox, gbc);

        CustomButton cadastrarButton = new CustomButton("Cadastrar");
        gbc.gridy = 7;
        gbc.insets = new Insets(16, 16, 8, 16);
        mainPanel.add(cadastrarButton, gbc);

        JLabel loginLabel = createLinkLabel("Já tem uma conta? Faça login");
        gbc.gridy = 8;
        gbc.insets = new Insets(4, 16, 16, 16);
        mainPanel.add(loginLabel, gbc);

        add(mainPanel, new GridBagConstraints());

        cadastrarButton.addActionListener(e -> cadastrarUsuario());

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginForm().setVisible(true);
                dispose();
            }
        });
    }

    private JLabel createLinkLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(ModernTheme.ACCENT_BLUE);
        label.setFont(ModernTheme.FONT_BODY.deriveFont(12f));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    private void cadastrarUsuario() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String cargo = cargoField.getText();
        String login = loginField.getText();
        String senha = new String(senhaField.getPassword());
        String perfil = (String) perfilComboBox.getSelectedItem();

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome, Login e Senha são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario novoUsuario = new Usuario(0L, nome, cpf, cargo, login, senha, perfil);

        try {
            usuarioDAO.adicionar(novoUsuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new LoginForm().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " + ex.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}