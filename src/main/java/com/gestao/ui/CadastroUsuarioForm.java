package com.gestao.ui;

import com.gestao.model.Usuario;
import com.gestao.service.UsuarioService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class CadastroUsuarioForm extends JFrame {
    // Campos do formulário
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField loginField;
    private JPasswordField senhaField;
    private JComboBox<String> cargoComboBox;
    private JButton cadastrarButton;
    private JButton cancelarButton;

    public CadastroUsuarioForm() {
    setTitle("Sistema de Gestão - Cadastro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
    GridBagConstraints gc = new GridBagConstraints();
    gc.insets = new Insets(6, 6, 6, 6);
    gc.anchor = GridBagConstraints.WEST;

    Dimension fieldSize = new Dimension(220, 28);
    nomeField = new JTextField(); nomeField.setPreferredSize(fieldSize);
    cpfField = new JTextField(); cpfField.setPreferredSize(fieldSize);
    loginField = new JTextField(); loginField.setPreferredSize(fieldSize);
    senhaField = new JPasswordField(); senhaField.setPreferredSize(fieldSize);
    String[] cargos = {"Administrador", "Gerente", "Membro", "Desenvolvedor"};
    cargoComboBox = new JComboBox<>(cargos);
    cargoComboBox.setPreferredSize(fieldSize);

    int y = 0;
    gc.gridx = 0; gc.gridy = y; panel.add(new JLabel("Nome:"), gc);
    gc.gridx = 1; gc.gridy = y++; gc.fill = GridBagConstraints.HORIZONTAL; panel.add(nomeField, gc);

    gc.gridx = 0; gc.gridy = y; gc.fill = GridBagConstraints.NONE; panel.add(new JLabel("CPF:"), gc);
    gc.gridx = 1; gc.gridy = y++; gc.fill = GridBagConstraints.HORIZONTAL; panel.add(cpfField, gc);

    // Removido campo de texto de cargo; usar apenas combo abaixo

    gc.gridx = 0; gc.gridy = y; gc.fill = GridBagConstraints.NONE; panel.add(new JLabel("Login:"), gc);
    gc.gridx = 1; gc.gridy = y++; gc.fill = GridBagConstraints.HORIZONTAL; panel.add(loginField, gc);

    gc.gridx = 0; gc.gridy = y; gc.fill = GridBagConstraints.NONE; panel.add(new JLabel("Senha:"), gc);
    gc.gridx = 1; gc.gridy = y++; gc.fill = GridBagConstraints.HORIZONTAL; panel.add(senhaField, gc);

    gc.gridx = 0; gc.gridy = y; gc.fill = GridBagConstraints.NONE; panel.add(new JLabel("Cargo:"), gc);
    gc.gridx = 1; gc.gridy = y++; gc.fill = GridBagConstraints.HORIZONTAL; panel.add(cargoComboBox, gc);

    JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    cadastrarButton = new JButton("Cadastrar");
    cadastrarButton.addActionListener(this::cadastrarUsuario);
    cancelarButton = new JButton("Cancelar");
    cancelarButton.addActionListener(e -> dispose());
    botoes.add(cancelarButton);
    botoes.add(cadastrarButton);

    gc.gridx = 0; gc.gridy = y; gc.gridwidth = 2; gc.fill = GridBagConstraints.HORIZONTAL;
    panel.add(botoes, gc);

    add(panel);
    }

    private void cadastrarUsuario(ActionEvent e) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(nomeField.getText());
            usuario.setCpf(cpfField.getText());
            // Cargo virá do combo abaixo
            usuario.setLogin(loginField.getText());
            usuario.setSenhaHash(String.valueOf(senhaField.getPassword()));
            usuario.setCargo(cargoComboBox.getSelectedItem().toString());

            UsuarioService usuarioService = new UsuarioService();
            if (usuarioService.criarUsuario(usuario)) {
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                limparFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Falha no cadastro: Verifique se o login já existe ou campos obrigatórios estão preenchidos");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void limparFormulario() {
    Arrays.asList(nomeField, cpfField, loginField, senhaField)
                .forEach(campo -> campo.setText(""));
    cargoComboBox.setSelectedIndex(0);
    }
}
