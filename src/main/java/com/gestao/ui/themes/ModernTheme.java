package com.gestao.ui.themes;

import java.awt.Color;
import java.awt.Font;

/**
 * Paleta de cores inspirada no tema Nord.
 * https://www.nordtheme.com/
 */
public class ModernTheme {
    // Cores Base (Polar Night)
    public static final Color BACKGROUND = new Color(0x2E3440);       // nord0
    public static final Color BACKGROUND_PANEL = new Color(0x3B4252); // nord1
    public static final Color BORDER = new Color(0x4C566A);           // nord3

    // Cores de Destaque (Frost)
    public static final Color ACCENT_BLUE = new Color(0x5E81AC);      // nord10
    public static final Color ACCENT_BLUE_HOVER = new Color(0x81A1C1); // nord8

    // Cores de Texto (Snow Storm)
    public static final Color TEXT_PRIMARY = new Color(0xD8DEE9);     // nord4
    public static final Color TEXT_SECONDARY = new Color(0x9299a8);   // nord4 com alpha
    public static final Color TEXT_ON_ACCENT = new Color(0xFFFFFF);

    // Cores de Feedback (Aurora)
    public static final Color DESTRUCTIVE_RED = new Color(0xBF616A);  // nord11

    // Fontes Padrão
    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 24);
    public static final Font FONT_SUBHEADING = new Font("SansSerif", Font.BOLD, 18);
    public static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FONT_BUTTON = new Font("SansSerif", Font.BOLD, 14);
}