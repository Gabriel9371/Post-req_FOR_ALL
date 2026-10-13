package com.gabriel;

import com.gabriel.ui.MainWindow;

import javax.swing.*;
import java.awt.Color;

public class Main {

    public static void main(String[] args) {
        applyDarkTheme();
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }

    private static void applyDarkTheme() {
        Color background = new Color(0x1E1E1E);
        Color panel      = new Color(0x2B2B2B);
        Color base       = new Color(0x3C3F41);
        Color text       = new Color(0xDDDDDD);
        Color accent     = new Color(0x3D6FB6);

        UIManager.put("control", panel);
        UIManager.put("info", panel);
        UIManager.put("nimbusBase", base);
        UIManager.put("nimbusLightBackground", background);
        UIManager.put("nimbusSelectionBackground", accent);
        UIManager.put("nimbusFocus", accent);
        UIManager.put("nimbusBorder", new Color(0x4A4A4A));
        UIManager.put("nimbusDisabledText", new Color(0x808080));
        UIManager.put("text", text);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
            // sem Nimbus, fica o visual padrão
        }
    }
}