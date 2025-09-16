package com.gestao.main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.gestao.ui.LoginForm;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FlatDarkLaf.setup();
                new LoginForm().setVisible(true);
            } catch (Exception e) {
                System.err.println("Erro ao iniciar a aplicação: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}