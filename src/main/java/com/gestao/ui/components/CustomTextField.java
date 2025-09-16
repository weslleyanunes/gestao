package com.gestao.ui.components;

import com.gestao.ui.themes.ModernTheme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomTextField extends JTextField {

    private String placeholder = "";

    public CustomTextField() {
        super();
        setFont(ModernTheme.FONT_BODY);
        setBackground(ModernTheme.BACKGROUND);
        setForeground(ModernTheme.TEXT_PRIMARY);
        setCaretColor(ModernTheme.TEXT_PRIMARY);
        setBorder(new EmptyBorder(8, 12, 8, 12));
        setOpaque(false);
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

        // Desenha o placeholder se o campo estiver vazio
        if (getText().isEmpty() && placeholder != null && !placeholder.isEmpty()) {
            g2.setColor(ModernTheme.TEXT_SECONDARY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            FontMetrics fm = g2.getFontMetrics();
            // Centraliza o texto do placeholder verticalmente
            g2.drawString(placeholder, getInsets().left, (getHeight() - fm.getHeight()) / 2 + fm.getAscent());
        }

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Desenha a borda arredondada
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(ModernTheme.BORDER);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
        g2.dispose();
    }
}