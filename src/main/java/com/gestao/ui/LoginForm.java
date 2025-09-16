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

public class LoginForm extends JFrame {

    private CustomTextField loginField;
    private JPasswordField senhaField;
    private UsuarioDAO usuarioDAO;

    public LoginForm() {
        usuarioDAO = new UsuarioDAO();
        initUI();
    }

    private void initUI() {
        setTitle("Login");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(ModernTheme.BACKGROUND);
        setLayout(new GridBagLayout());

        CustomPanel mainPanel = new CustomPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(320, 380));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 16, 8, 16);
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Bem-vindo");
        titleLabel.setFont(ModernTheme.FONT_TITLE);
        titleLabel.setForeground(ModernTheme.TEXT_PRIMARY);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 16, 20, 16);
        mainPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(8, 16, 8, 16);

        loginField = new CustomTextField();
        loginField.setPlaceholder("Login");
        gbc.gridy = 1;
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
        gbc.gridy = 2;
        mainPanel.add(senhaField, gbc);

        CustomButton loginButton = new CustomButton("Entrar");
        gbc.gridy = 3;
        gbc.insets = new Insets(16, 16, 8, 16);
        mainPanel.add(loginButton, gbc);

        JPanel linksPanel = new JPanel(new BorderLayout());
        linksPanel.setOpaque(false);

        JLabel forgotPasswordLabel = createLinkLabel("Esqueci minha senha");
        JLabel signUpLabel = createLinkLabel("Cadastrar-se");

        linksPanel.add(forgotPasswordLabel, BorderLayout.WEST);
        linksPanel.add(signUpLabel, BorderLayout.EAST);
        gbc.gridy = 4;
        gbc.insets = new Insets(4, 16, 16, 16);
        mainPanel.add(linksPanel, gbc);

        add(mainPanel, new GridBagConstraints());

        loginButton.addActionListener(e -> autenticar());

        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CadastroUsuarioForm().setVisible(true);
                dispose();
            }
        });
    }

    private JLabel createLinkLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(ModernTheme.ACCENT_BLUE);
        label.setFont(ModernTheme.FONT_BODY.deriveFont(12f));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    private void autenticar() {
        String login = loginField.getText();
        String senha = new String(senhaField.getPassword());

        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Login e Senha são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Usuario usuario = usuarioDAO.validarLogin(login, senha);
            if (usuario != null) {
                new MainApplicationUI(usuario).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login ou senha inválidos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.\nVerifique sua conexão e as configurações.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}