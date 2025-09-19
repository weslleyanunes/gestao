package com.gestao.ui;

import com.gestao.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    private Usuario usuarioLogado;
    // Navegação moderna: painel lateral + CardLayout
    private JPanel conteudo;
    private CardLayout cards;
    private JPanel projetosPanel;
    private JPanel tarefasPanel;
    private JPanel usuariosPanel;
    private JPanel logsPanel;

    public MainDashboard(Usuario usuario) {
        this.usuarioLogado = usuario;

        setTitle("Sistema de Gestão - Dashboard — Usuário: " + usuario.getNome());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        projetosPanel = new JPanel(new BorderLayout());
        tarefasPanel = new JPanel(new BorderLayout());
        usuariosPanel = new JPanel(new BorderLayout());
        logsPanel = new JPanel(new BorderLayout());

        // Barra superior com usuário/cargo
    JPanel topBar = new JPanel(new BorderLayout());
    topBar.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
    JLabel userInfo = new JLabel("Usuário: " + usuario.getNome() + " — Cargo: " + (usuario.getCargo() != null ? usuario.getCargo() : ""));
        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    topBar.add(userInfo, BorderLayout.WEST);
    topBar.add(rightTop, BorderLayout.EAST);
    add(topBar, BorderLayout.NORTH);

        // Navegação lateral
    JPanel nav = new JPanel();
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));
    nav.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JButton btnProj = new JButton("Projetos");
        JButton btnTar = new JButton("Tarefas");
        JButton btnUsu = new JButton("Usuários");
        JButton btnLog = new JButton("Logs");
        btnUsu.setVisible(usuario.isAdministrador() || usuario.isDesenvolvedor());
        btnLog.setVisible(usuario.isDesenvolvedor());
        nav.add(btnProj);
        nav.add(Box.createVerticalStrut(6));
        nav.add(btnTar);
        if (btnUsu.isVisible()) {
            nav.add(Box.createVerticalStrut(6));
            nav.add(btnUsu);
        }
        if (btnLog.isVisible()) {
            nav.add(Box.createVerticalStrut(6));
            nav.add(btnLog);
        }
        add(nav, BorderLayout.WEST);

        // Área de conteúdo com CardLayout
        cards = new CardLayout();
        conteudo = new JPanel(cards);
        conteudo.add(projetosPanel, "projetos");
        conteudo.add(tarefasPanel, "tarefas");
        conteudo.add(usuariosPanel, "usuarios");
        conteudo.add(logsPanel, "logs");
        add(conteudo, BorderLayout.CENTER);

        // Ações de navegação
        btnProj.addActionListener(e -> cards.show(conteudo, "projetos"));
        btnTar.addActionListener(e -> cards.show(conteudo, "tarefas"));
        btnUsu.addActionListener(e -> cards.show(conteudo, "usuarios"));
        btnLog.addActionListener(e -> cards.show(conteudo, "logs"));

        // Ícones no canto inferior direito: sair, preferências, tema, logs, sobre
    JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    south.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JButton sairIco = new JButton("⎋");
        sairIco.setToolTipText("Sair");
        sairIco.addActionListener(e -> sair());
    JButton prefIco = new JButton("⚙");
    prefIco.setToolTipText("Preferências");
    prefIco.addActionListener(e -> abrirPreferencias());
        JButton temaIco = new JButton("☼");
        temaIco.setToolTipText("Alternar tema claro/escuro");
        temaIco.addActionListener(e -> alternarTema());
        JButton logsIco = new JButton("🧾");
        logsIco.setToolTipText("Logs");
        logsIco.setVisible(usuario.isDesenvolvedor());
        logsIco.addActionListener(e -> {
            cards.show(conteudo, "logs");
            montarPainelLogs();
        });
        JButton sobreIco = new JButton("ⓘ");
        sobreIco.setToolTipText("Sobre");
        sobreIco.addActionListener(e -> {
            AboutDialog dlg = new AboutDialog(this);
            dlg.setLocationRelativeTo(this);
            dlg.setVisible(true);
        });
        south.add(prefIco);
        south.add(temaIco);
        south.add(logsIco);
        south.add(sobreIco);
        south.add(sairIco);
        add(south, BorderLayout.SOUTH);

        montarPainelProjetos();
        montarPainelTarefas();
        montarPainelUsuarios();
        montarPainelLogs();
        // Mostra conteúdo inicial
        if (usuario.isAdministrador() || usuario.isGerente() || usuario.isDesenvolvedor()) cards.show(conteudo, "projetos");
        else cards.show(conteudo, "tarefas");
    }

    private void abrirPreferencias() {
        PreferencesDialog dialog = new PreferencesDialog(this, () -> {
            // Reaplica tema e debug após salvar preferências
            SwingUtilities.updateComponentTreeUI(this);
            montarPainelLogs();
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void sair() {
        int op = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            // Fecha o dashboard e volta ao login
            SwingUtilities.invokeLater(() -> {
                dispose();
                new LoginForm().setVisible(true);
            });
        }
    }

    private void alternarTema() {
        boolean dark = UIManager.getLookAndFeel().getName().toLowerCase().contains("dark");
        try {
            if (!dark) com.formdev.flatlaf.FlatDarkLaf.setup();
            else com.formdev.flatlaf.FlatLightLaf.setup();
            UIManager.put("Component.arc", 14);
            UIManager.put("Button.arc", 14);
            UIManager.put("TextComponent.arc", 14);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ignored) {}
    }

    private void montarPainelProjetos() {
    if (projetosPanel == null) return;
        projetosPanel.removeAll();
        ProjetoPanel painel = new ProjetoPanel(usuarioLogado);
        projetosPanel.add(painel, BorderLayout.CENTER);
        projetosPanel.revalidate();
        projetosPanel.repaint();
    }

    private void montarPainelTarefas() {
        tarefasPanel.removeAll();
        TarefaPanel tarefas = new TarefaPanel(usuarioLogado);
        tarefasPanel.add(new JScrollPane(tarefas), BorderLayout.CENTER);
        tarefasPanel.revalidate();
        tarefasPanel.repaint();
    }

    private void montarPainelUsuarios() {
        if (usuariosPanel == null) return;
        usuariosPanel.removeAll();
        usuariosPanel.add(new UsuariosPanel(), BorderLayout.CENTER);
        usuariosPanel.revalidate();
        usuariosPanel.repaint();
    }

    private void montarPainelLogs() {
    if (logsPanel == null) return;
        logsPanel.removeAll();
        JTextArea area = new JTextArea();
        area.setEditable(false);
        JButton refresh = new JButton("Atualizar");
        JToggleButton dbg = new JToggleButton("Debug");
        dbg.setSelected(com.gestao.service.DebugLogger.isDebug());
        dbg.addActionListener(e -> com.gestao.service.DebugLogger.setDebug(dbg.isSelected()));
        refresh.addActionListener(e -> carregarLogs(area));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(dbg);
        top.add(refresh);
        JButton changelog = new JButton("Changelog");
        changelog.addActionListener(e -> {
            ChangelogDialog dlg = new ChangelogDialog(this);
            dlg.setLocationRelativeTo(this);
            dlg.setVisible(true);
        });
        top.add(changelog);
        logsPanel.add(top, BorderLayout.NORTH);
        logsPanel.add(new JScrollPane(area), BorderLayout.CENTER);
        carregarLogs(area);
        logsPanel.revalidate();
        logsPanel.repaint();
    }

    private void carregarLogs(JTextArea area) {
        java.nio.file.Path p = java.nio.file.Paths.get("database/app.log");
        if (java.nio.file.Files.exists(p)) {
            try { area.setText(new String(java.nio.file.Files.readAllBytes(p))); }
            catch (Exception ignored) {}
        } else {
            area.setText("Sem logs disponíveis.");
        }
    }

}
