package com.gestao.main;

import com.gestao.model.Usuario;
import com.gestao.repository.UsuarioRepositorio;
import com.gestao.ui.LoginForm;
import com.gestao.service.PreferencesManager;
import com.gestao.service.DebugLogger;

import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.gestao.service.PasswordUtils;

public class App {
    public static void main(String[] args) {
        // Carrega preferências de tema e debug
        PreferencesManager prefs = new PreferencesManager();
        DebugLogger.setDebug(prefs.isDebug());
        try {
            if ("dark".equalsIgnoreCase(prefs.getTheme())) {
                com.formdev.flatlaf.FlatDarkLaf.setup();
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            // Presets de estilo (Material-like) globais via FlatLaf
            UIManager.put("Component.arc", 16);
            UIManager.put("Button.arc", 16);
            UIManager.put("TextComponent.arc", 14);
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button.focusWidth", 1);
            UIManager.put("TextComponent.minimumWidth", 120);
            UIManager.put("Button.minimumWidth", 100);
            UIManager.put("Separator.thickness", 1);
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.trackArc", 999);
            UIManager.put("ScrollBar.width", 12);
        } catch (Exception ignored) {}
        criarAdminPadrao();
        SwingUtilities.invokeLater(() -> {
            LoginForm lf = new LoginForm();
            lf.setTitle("Sistema de Gestão - Login");
            lf.setVisible(true);
        });
    }

    private static void criarAdminPadrao() {
        UsuarioRepositorio repo = new UsuarioRepositorio();
        if (repo.findByLogin("admin") == null) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setLogin("admin");
            admin.setSenhaHash(PasswordUtils.hash("admin123"));
            admin.setCargo("Administrador");
            repo.adicionar(admin);
        }
    }
}
