package com.gestao.ui;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class AboutDialog extends JDialog {
    public AboutDialog(Window owner) {
        super(owner, "Sistema de Gestão - Sobre", ModalityType.APPLICATION_MODAL);
        setSize(520, 360);
        setResizable(false);

        String html = "<html>"
                + "<h2>Sistema de Gestão de Projetos</h2>"
                + "<p>Aplicativo desktop em Java/Swing para gestão de usuários, projetos e tarefas, com persistência em arquivo e temas claro/escuro.</p>"
                + "<h3>Autores</h3>"
                + "<ul>"
                + "<li><a href='https://github.com/weslleyanunes'>@weslleyanunes</a></li>"
                + "<li><a href='https://github.com/marcvcci'>@marcvcci</a></li>"
                + "<li><a href='https://github.com/TidezTi'>@TidezTi</a></li>"
                + "</ul>"
                + "<h3>Licença</h3>"
                + "<p>Distribuído sob a licença <b>GPLv3</b>. Veja o arquivo <code>LICENSE</code> no repositório para mais detalhes.</p>"
                + "</html>";

        JEditorPane pane = new JEditorPane("text/html", html);
        pane.setEditable(false);
        pane.setOpaque(false);
        pane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                if (Desktop.isDesktopSupported()) {
                    try { Desktop.getDesktop().browse(URI.create(e.getURL().toString())); } catch (IOException ex) { /* ignore */ }
                }
            }
        });

        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        root.add(new JScrollPane(pane), BorderLayout.CENTER);

        JButton fechar = new JButton("Fechar");
        fechar.addActionListener(e -> dispose());
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(fechar);
        root.add(south, BorderLayout.SOUTH);

        setContentPane(root);
    }
}
