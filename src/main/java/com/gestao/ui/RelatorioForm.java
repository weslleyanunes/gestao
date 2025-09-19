package com.gestao.ui;

import com.gestao.model.Relatorio;
import com.gestao.service.RelatorioService;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class RelatorioForm extends JFrame {
    private JTextField tituloField;
    private JTextArea conteudoArea;
    private JButton gerarButton;

    public RelatorioForm() {
        setTitle("Gerar Relatório");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Título:"));
        tituloField = new JTextField(20);
        panel.add(tituloField);

        panel.add(new JLabel("Conteúdo:"));
        conteudoArea = new JTextArea(5, 20);
        panel.add(new JScrollPane(conteudoArea));

        gerarButton = new JButton("Gerar Relatório");
        gerarButton.addActionListener(this::gerarRelatorio);
        panel.add(gerarButton);

        add(panel);
    }

    private void gerarRelatorio(ActionEvent e) {
        Relatorio relatorio = new Relatorio();
        relatorio.setTitulo(tituloField.getText());
        relatorio.setConteudo(conteudoArea.getText());

        RelatorioService relatorioService = new RelatorioService();
        if (relatorioService.gerarRelatorio(relatorio)) {
            JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao gerar relatório.");
        }
    }
}
