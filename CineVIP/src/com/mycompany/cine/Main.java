package com.mycompany.cine;

import com.mycompany.PantallaInicio.PantallaInicio;  // CORREGIDO
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("No se pudo cargar el Look and Feel del sistema");
        }
        
        SwingUtilities.invokeLater(() -> {
            PantallaInicio pantallaInicio = new PantallaInicio();
            pantallaInicio.setVisible(true);
            pantallaInicio.setLocationRelativeTo(null);
        });
    }
}

