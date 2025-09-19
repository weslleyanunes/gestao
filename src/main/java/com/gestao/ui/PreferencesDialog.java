package com.gestao.ui;

import com.gestao.service.PreferencesManager;
import com.gestao.service.DebugLogger;

import javax.swing.*;
import java.awt.*;

public class PreferencesDialog extends JDialog {
    private final JComboBox<String> temaCombo = new JComboBox<>(new String[]{"Claro", "Escuro"});
    private final JCheckBox debugChk = new JCheckBox("Ativar modo debug");
    private final Runnable onSaved;

    public PreferencesDialog(Window owner, Runnable onSaved) {
        super(owner, "Sistema de Gestão - Preferências", ModalityType.APPLICATION_MODAL);
        this.onSaved = onSaved;
        setSize(360, 220);
        setResizable(false);
        montarUI();
        carregar();
    }

    private void montarUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.add(new JLabel("Tema padrão:"));
        form.add(temaCombo);
        form.add(Box.createVerticalStrut(8));
        form.add(debugChk);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(e -> dispose());
        JButton salvar = new JButton("Salvar");
        salvar.addActionListener(e -> salvar());
        botoes.add(cancelar);
        botoes.add(salvar);

        root.add(form, BorderLayout.CENTER);
        root.add(botoes, BorderLayout.SOUTH);
        setContentPane(root);
    }

    private void carregar() {
        PreferencesManager pm = new PreferencesManager();
        String theme = pm.getTheme();
        temaCombo.setSelectedItem("dark".equalsIgnoreCase(theme) ? "Escuro" : "Claro");
        debugChk.setSelected(pm.isDebug());
    }

    private void salvar() {
        PreferencesManager pm = new PreferencesManager();
        String sel = (String) temaCombo.getSelectedItem();
        String theme = "Escuro".equals(sel) ? "dark" : "light";
        pm.setTheme(theme);
        pm.setDebug(debugChk.isSelected());
        pm.save();

        // Aplica imediatamente
        try {
            if ("dark".equals(theme)) com.formdev.flatlaf.FlatDarkLaf.setup();
            else com.formdev.flatlaf.FlatLightLaf.setup();
            UIManager.put("Component.arc", 14);
            UIManager.put("Button.arc", 14);
            UIManager.put("TextComponent.arc", 14);
            DebugLogger.setDebug(debugChk.isSelected());
        } catch (Exception ignored) {}
        if (onSaved != null) onSaved.run();
        dispose();
    }
}
