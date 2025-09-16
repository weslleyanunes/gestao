package com.gestao.ui;

import com.gestao.model.Usuario;
import com.gestao.ui.themes.ModernTheme;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainContentPanel;
    private Usuario usuario;

    public MainDashboard(Usuario usuario) {
        this.usuario = usuario;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(ModernTheme.BACKGROUND);

        // Painel Lateral (Sidebar)
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(ModernTheme.BACKGROUND_PANEL);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, ModernTheme.BORDER));

        JLabel logoLabel = new JLabel("GestãoPro", SwingConstants.CENTER);
        logoLabel.setFont(ModernTheme.FONT_TITLE.deriveFont(22f));
        logoLabel.setForeground(ModernTheme.TEXT_PRIMARY);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setMaximumSize(new Dimension(200, 100));
        logoLabel.setPreferredSize(new Dimension(200, 100));

        sidebar.add(logoLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        sidebar.add(createNavButton("Dashboard"));
        sidebar.add(createNavButton("Projetos"));
        sidebar.add(createNavButton("Tarefas"));
        sidebar.add(createNavButton("Equipes"));
        sidebar.add(createNavButton("Relatórios"));

        sidebar.add(Box.createVerticalGlue());

        JButton logoutButton = createNavButton("Sair");
        logoutButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new LoginForm().setVisible(true);
        });
        sidebar.add(logoutButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));


        // Painel de Conteúdo Principal
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setOpaque(false);

        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setOpaque(false);
        JLabel welcomeLabel = new JLabel("Bem-vindo, " + usuario.getNome() + "!");
        welcomeLabel.setFont(ModernTheme.FONT_TITLE);
        welcomeLabel.setForeground(ModernTheme.TEXT_PRIMARY);
        welcomePanel.add(welcomeLabel);

        mainContentPanel.add(welcomePanel, "Dashboard");
        mainContentPanel.add(createPlaceholderPanel("Projetos"), "Projetos");
        mainContentPanel.add(createPlaceholderPanel("Tarefas"), "Tarefas");
        mainContentPanel.add(createPlaceholderPanel("Equipes"), "Equipes");
        mainContentPanel.add(createPlaceholderPanel("Relatórios"), "Relatórios");

        add(sidebar, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(ModernTheme.FONT_BODY);
        button.setForeground(ModernTheme.TEXT_SECONDARY);
        button.setBackground(ModernTheme.BACKGROUND_PANEL);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(200, 50));

        button.addActionListener(e -> cardLayout.show(mainContentPanel, text));

        return button;
    }

    private JPanel createPlaceholderPanel(String text) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        JLabel label = new JLabel("Painel de " + text);
        label.setFont(ModernTheme.FONT_TITLE.deriveFont(32f));
        label.setForeground(ModernTheme.TEXT_SECONDARY);
        panel.add(label);
        return panel;
    }
}