package com.gestao.ui;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccessLogDialog extends JDialog {
    private final JTextArea area = new JTextArea();

    public AccessLogDialog(Window owner) {
        super(owner, "Sistema de Gestão - Log de Acesso", ModalityType.APPLICATION_MODAL);
        setSize(640, 420);
        setResizable(true);
        setLayout(new BorderLayout());
        area.setEditable(false);

    JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refresh = new JButton("Atualizar");
        refresh.addActionListener(e -> carregar());
        top.add(refresh);

    add(top, BorderLayout.NORTH);
    JScrollPane sp = new JScrollPane(area);
    add(sp, BorderLayout.CENTER);

        carregar();
    }

    private void carregar() {
        Path p = Paths.get("database/access.log");
        if (Files.exists(p)) {
            try { area.setText(new String(Files.readAllBytes(p))); }
            catch (Exception ex) { area.setText("Erro ao ler access.log: " + ex.getMessage()); }
        } else {
            area.setText("Sem registros de acesso.");
        }
        area.setCaretPosition(0);
    }
}
