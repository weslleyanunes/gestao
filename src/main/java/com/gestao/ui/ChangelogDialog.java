package com.gestao.ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ChangelogDialog extends JDialog {
    public ChangelogDialog(Window owner) {
        super(owner, "Sistema de Gestão - Changelog", ModalityType.APPLICATION_MODAL);
        setSize(640, 480);
        setResizable(true);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        carregarConteudo(area);

        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        root.add(new JScrollPane(area), BorderLayout.CENTER);

        JButton fechar = new JButton("Fechar");
        fechar.addActionListener(e -> dispose());
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(fechar);
        root.add(south, BorderLayout.SOUTH);

        setContentPane(root);
    }

    private void carregarConteudo(JTextArea area) {
        File md = new File("CHANGELOG.md");
        if (md.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(md))) {
                String line; StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) sb.append(line).append('\n');
                area.setText(sb.toString());
                area.setCaretPosition(0);
                return;
            } catch (IOException ignored) {}
        }
        // Fallback: instrução para usar git log
        area.setText("Nenhum CHANGELOG.md encontrado.\n\n" +
                "Para gerar um changelog básico, execute:\n" +
                "  git --no-pager log --pretty=format:'%h %ad %an %s' --date=short\n\n" +
                "Recomenda-se manter um arquivo CHANGELOG.md no repositório (formato Keep a Changelog)." );
        area.setCaretPosition(0);
    }
}
