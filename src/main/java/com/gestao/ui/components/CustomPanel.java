package com.gestao.ui.components;

import com.gestao.ui.themes.ModernTheme;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private int cornerRadius = 15;

    public CustomPanel() {
        super();
        setOpaque(false);
        setBackground(ModernTheme.BACKGROUND_PANEL);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha o painel com cantos arredondados
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        graphics.setColor(ModernTheme.BORDER);
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
}