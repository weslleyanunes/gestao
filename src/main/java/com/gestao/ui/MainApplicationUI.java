package com.gestao.ui;

import com.gestao.model.Usuario;
import javax.swing.*;
import java.awt.*;

public class MainApplicationUI extends JFrame {
    private Usuario usuarioLogado;

    public MainApplicationUI(Usuario usuario) {
        this.usuarioLogado = usuario;
        initUI();
    }

    private void initUI() {
        setTitle("Sistema de Gestão de Projetos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 600));

        MainDashboard mainDashboard = new MainDashboard(usuarioLogado);
        setContentPane(mainDashboard);
    }
}