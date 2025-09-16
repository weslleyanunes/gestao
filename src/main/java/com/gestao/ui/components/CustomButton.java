package com.gestao.ui.components;

import com.gestao.ui.themes.ModernTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    private Color backgroundColor = ModernTheme.ACCENT_BLUE;
    private Color hoverColor = ModernTheme.ACCENT_BLUE_HOVER;

    public CustomButton(String text) {
        super(text);
        setFont(ModernTheme.FONT_BUTTON);
        setForeground(Color.WHITE);
        setBackground(backgroundColor);
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backgroundColor = hoverColor;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backgroundColor = ModernTheme.ACCENT_BLUE;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
        g2.dispose();
        super.paintComponent(g);
    }
}