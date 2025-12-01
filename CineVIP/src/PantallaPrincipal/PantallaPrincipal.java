package PantallaPrincipal;

import com.mycompany.cine.controlador.Cine;
import com.mycompany.cine.modelo.Funcion;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import PantallaReportes.PantallaReportes;

public class PantallaPrincipal extends JFrame {
    private final Cine cine;
    private final Funcion funcion;
    private final Set<String> asientosSeleccionados;
    private JLabel lblTotal;
    private JTextField txtNombre;
    private JLabel lblAsientosSeleccionados;
    
    public PantallaPrincipal(Cine cine, Funcion funcion) {
        this.cine = cine;
        this.funcion = funcion;
        this.asientosSeleccionados = new HashSet<>();
        initComponents();
        setTitle("Venta - " + funcion.getPelicula());
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void abrirReportes() {
        PantallaReportes ventana = new PantallaReportes();
        ventana.setVisible(true);
    }
    
    private void initComponents() {
        // Panel principal con BoxLayout vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(240, 248, 255));
        
        // ========== TÍTULO ==========
        JLabel titulo = new JLabel("🎬 " + funcion.getNombreCompleto(), SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(25, 25, 112));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // ========== PANEL CLIENTE ==========
        JPanel panelCliente = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCliente.setBackground(new Color(240, 248, 255));
        
        JLabel lblCliente = new JLabel("👤 Nombre del cliente (opcional):");
        lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        txtNombre = new JTextField(25);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNombre.setMaximumSize(new Dimension(400, 30));
        
        panelCliente.add(lblCliente);
        panelCliente.add(txtNombre);
        
        // ========== PANTALLA ==========
        JPanel panelPantalla = new JPanel();
        panelPantalla.setBackground(Color.BLACK);
        panelPantalla.setPreferredSize(new Dimension(700, 40));
        panelPantalla.setMaximumSize(new Dimension(700, 40));
        panelPantalla.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblPantalla = new JLabel(" P A N T A L L A ", SwingConstants.CENTER);
        lblPantalla.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPantalla.setForeground(Color.WHITE);
        panelPantalla.add(lblPantalla);
        
        // ========== ASIENTOS ==========
        JPanel panelAsientos = new JPanel(new GridLayout(4, 4, 10, 10));
        panelAsientos.setBackground(new Color(240, 248, 255));
        panelAsientos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelAsientos.setMaximumSize(new Dimension(500, 400));
        
        String[] filas = {"A", "B", "C", "D"};
        boolean[][] estadoAsientos = funcion.getEstadoAsientos();
        
        // Crear los 16 botones de asientos
        for (int fila = 0; fila < 4; fila++) {
            for (int col = 0; col < 4; col++) {
                String asiento = filas[fila] + (col + 1);
                JButton btnAsiento = new JButton(asiento);
                btnAsiento.setFont(new Font("Segoe UI", Font.BOLD, 14));
                btnAsiento.setFocusPainted(false);
                btnAsiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                boolean ocupado = false;
                if (estadoAsientos != null && fila < estadoAsientos.length && 
                    col < estadoAsientos[fila].length) {
                    ocupado = estadoAsientos[fila][col];
                }
                
                if (ocupado) {
                    btnAsiento.setBackground(new Color(231, 76, 60)); // Rojo
                    btnAsiento.setForeground(Color.WHITE);
                    btnAsiento.setEnabled(false);
                    btnAsiento.setToolTipText("🔴 ASIENTO OCUPADO");
                } else {
                    btnAsiento.setBackground(new Color(46, 204, 113)); // Verde
                    btnAsiento.setForeground(Color.BLACK);
                    btnAsiento.setToolTipText("🟢 ASIENTO DISPONIBLE - Click para seleccionar");
                    
                    btnAsiento.addActionListener(e -> {
                        seleccionarAsiento(btnAsiento, asiento);
                    });
                }
                
                panelAsientos.add(btnAsiento);
            }
        }
        
        // ========== INFORMACIÓN ==========
        JPanel panelInfo = new JPanel(new GridLayout(2, 2, 10, 10));
        panelInfo.setBackground(new Color(240, 248, 255));
        panelInfo.setMaximumSize(new Dimension(700, 100));
        
        lblAsientosSeleccionados = new JLabel("Asientos seleccionados: 0", SwingConstants.CENTER);
        lblAsientosSeleccionados.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel lblPrecio = new JLabel("Precio: $" + funcion.getPrecio(), SwingConstants.CENTER);
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel lblDisponibles = new JLabel("Disponibles: " + funcion.getAsientosDisponiblesCount() + "/16", SwingConstants.CENTER);
        lblDisponibles.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        lblTotal = new JLabel("💵 Total: $0.00", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setForeground(new Color(0, 100, 0));
        
        panelInfo.add(lblAsientosSeleccionados);
        panelInfo.add(lblPrecio);
        panelInfo.add(lblDisponibles);
        panelInfo.add(lblTotal);
        
        // ========== LEYENDA MEJORADA ==========
        JPanel panelLeyenda = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        panelLeyenda.setBackground(new Color(240, 248, 255));
        panelLeyenda.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            "Leyenda de Asientos",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 11),
            new Color(70, 70, 70)
        ));
        
        // Crear items de leyenda con estilo mejorado
        panelLeyenda.add(crearItemLeyendaMejorado("", "Disponible", 
            new Color(46, 204, 113), new Color(39, 174, 96)));
        panelLeyenda.add(crearItemLeyendaMejorado("", "Seleccionado", 
            new Color(241, 196, 15), new Color(243, 156, 18)));
        panelLeyenda.add(crearItemLeyendaMejorado("", "Ocupado", 
            new Color(231, 76, 60), new Color(192, 57, 43)));
        
        // ========== BOTONES ==========
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        panelBotones.setBackground(new Color(240, 248, 255));
        
        // Botón COMPRAR
        JButton btnComprar = new JButton("COMPRAR BOLETOS");
        btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnComprar.setBackground(new Color(34, 139, 34));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFocusPainted(false);
        btnComprar.setPreferredSize(new Dimension(200, 45));
        btnComprar.addActionListener(e -> realizarCompra());
        
        // Botón REPORTES
        JButton btnReportes = new JButton("📊 VER REPORTES");
        btnReportes.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnReportes.setBackground(new Color(70, 130, 180));
        btnReportes.setForeground(Color.WHITE);
        btnReportes.setFocusPainted(false);
        btnReportes.setPreferredSize(new Dimension(200, 45));
        btnReportes.addActionListener(e -> {
            PantallaReportes pantallaReportes = new PantallaReportes();
            pantallaReportes.setVisible(true);
        });
        
        // Botón CANCELAR
        JButton btnCancelar = new JButton(" CANCELAR");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(200, 45));
        btnCancelar.addActionListener(e -> dispose());
        
        // Agregar los TRES botones al panel
        panelBotones.add(btnComprar);
        panelBotones.add(btnReportes);
        panelBotones.add(btnCancelar);
        
        // ========== ORGANIZAR TODO ==========
        // Agregar espacios entre componentes
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelCliente);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelPantalla);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));
        panelPrincipal.add(panelAsientos);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelInfo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(panelLeyenda);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelBotones);
        
        // Agregar panel principal a la ventana
        setContentPane(panelPrincipal);
    }
    
    private JPanel crearItemLeyendaMejorado(String icono, String texto, Color colorFondo, Color colorTexto) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        panel.setBackground(new Color(250, 252, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        // Icono
        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        // Texto
        JLabel lblTexto = new JLabel(texto);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTexto.setForeground(colorTexto);
        
        panel.add(lblIcono);
        panel.add(lblTexto);
        return panel;
    }
    
    private void seleccionarAsiento(JButton boton, String asiento) {
        if (asientosSeleccionados.contains(asiento)) {
            // Deseleccionar
            asientosSeleccionados.remove(asiento);
            boton.setBackground(new Color(46, 204, 113)); // Verde
            boton.setToolTipText("ASIENTO DISPONIBLE - Click para seleccionar");
        } else {
            // Seleccionar
            asientosSeleccionados.add(asiento);
            boton.setBackground(new Color(241, 196, 15)); // Amarillo/Naranja
            boton.setToolTipText("ASIENTO SELECCIONADO - Click para deseleccionar");
        }
        
        // Actualizar información
        int cantidad = asientosSeleccionados.size();
        double total = cantidad * funcion.getPrecio();
        
        lblAsientosSeleccionados.setText("Asientos seleccionados: " + cantidad);
        lblTotal.setText(String.format(" Total: $%.2f", total));
    }
    
    private void realizarCompra() {
        if (asientosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "❌ Debe seleccionar al menos un asiento", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nombreCliente = txtNombre.getText().trim();
        if (nombreCliente.isEmpty()) {
            nombreCliente = "Cliente no registrado";
        }
        
        // Confirmar compra
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Confirmar compra de " + asientosSeleccionados.size() + " boleto(s) por $" + 
            (asientosSeleccionados.size() * funcion.getPrecio()) + "?",
            "Confirmar Compra",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Realizar venta de cada asiento
        boolean exito = true;
        StringBuilder asientosNoDisponibles = new StringBuilder();
        
        for (String asiento : asientosSeleccionados) {
            if (!cine.venderBoleto(funcion.getId(), asiento, nombreCliente)) {
                exito = false;
                asientosNoDisponibles.append(asiento).append(" ");
            }
        }
        
        if (exito) {
            double total = asientosSeleccionados.size() * funcion.getPrecio();
            String mensaje = String.format(
                "<html><div style='text-align: center;'>" +
                "<h2>✅ COMPRA EXITOSA</h2>" +
                "<b>Cliente:</b> %s<br>" +
                "<b>Función:</b> %s<br>" +
                "<b>Asientos:</b> %s<br>" +
                "<b>Total:</b> <font color='green'>$%.2f</font><br>" +
                "<b>Boletos vendidos:</b> %d<br><br>" +
                "<i>¡Disfrute la función! </i>" +
                "</div></html>",
                nombreCliente,
                funcion.getNombreCompleto(),
                String.join(", ", asientosSeleccionados),
                total,
                asientosSeleccionados.size()
            );
            
            JOptionPane.showMessageDialog(this, mensaje, 
                "Compra Realizada", JOptionPane.INFORMATION_MESSAGE);
            
            // ========== EN LUGAR DE CERRAR, RESETEAMOS ==========
            
            // 1. Limpiar selección de asientos
            asientosSeleccionados.clear();
            
            // 2. Resetear los botones de asientos
            Component[] componentes = getContentPane().getComponents();
            for (Component comp : componentes) {
                if (comp instanceof JPanel) {
                    resetearBotonesAsientos((JPanel) comp);
                }
            }
            
            // 3. Actualizar información
            lblAsientosSeleccionados.setText("Asientos seleccionados: 0");
            lblTotal.setText("Total: $0.00");
            txtNombre.setText(""); // Limpiar nombre
            
            // 4. Actualizar contador de disponibles
            JLabel lblDisponibles = null;
            for (Component comp : getContentPane().getComponents()) {
                if (comp instanceof JPanel) {
                    lblDisponibles = buscarLabelDisponibles((JPanel) comp);
                    if (lblDisponibles != null) break;
                }
            }
            if (lblDisponibles != null) {
                lblDisponibles.setText("Disponibles: " + funcion.getAsientosDisponiblesCount() + "/16");
            }
            
            // 5. Actualizar estado de los botones según nueva disponibilidad
            actualizarEstadoAsientos();
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Los siguientes asientos ya no están disponibles:\n" + 
                asientosNoDisponibles.toString() + "\n\nPor favor, seleccione otros asientos.", 
                "Error en la Compra", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    
    private void resetearBotonesAsientos(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String texto = btn.getText();
                // Solo resetear botones de asientos (A1, A2, B1, etc.)
                if (texto.matches("[A-D][1-4]")) {
                    // Verificar si el asiento sigue disponible
                    boolean[][] estadoAsientos = funcion.getEstadoAsientos();
                    if (estadoAsientos != null) {
                        char fila = texto.charAt(0);
                        int col = Integer.parseInt(texto.substring(1)) - 1;
                        int filaIndex = fila - 'A';
                        
                        if (filaIndex < estadoAsientos.length && col < estadoAsientos[filaIndex].length) {
                            if (estadoAsientos[filaIndex][col]) {
                                btn.setBackground(new Color(231, 76, 60)); // Rojo
                                btn.setForeground(Color.WHITE);
                                btn.setEnabled(false);
                                btn.setToolTipText("ASIENTO OCUPADO");
                            } else {
                                btn.setBackground(new Color(46, 204, 113)); // Verde
                                btn.setForeground(Color.BLACK);
                                btn.setEnabled(true);
                                btn.setToolTipText(" ASIENTO DISPONIBLE - Click para seleccionar");
                            }
                        }
                    }
                }
            } else if (comp instanceof JPanel) {
                resetearBotonesAsientos((JPanel) comp);
            }
        }
    }
    
    private JLabel buscarLabelDisponibles(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel lbl = (JLabel) comp;
                if (lbl.getText().contains("Disponibles:")) {
                    return lbl;
                }
            } else if (comp instanceof JPanel) {
                JLabel encontrado = buscarLabelDisponibles((JPanel) comp);
                if (encontrado != null) return encontrado;
            }
        }
        return null;
    }
    
    private void actualizarEstadoAsientos() {
        boolean[][] estadoAsientos = funcion.getEstadoAsientos();
        if (estadoAsientos == null) return;
        
        String[] filas = {"A", "B", "C", "D"};
        
        // Buscar el panel de asientos
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                actualizarBotonesEnPanel((JPanel) comp, estadoAsientos, filas);
            }
        }
    }
    
    private void actualizarBotonesEnPanel(JPanel panel, boolean[][] estadoAsientos, String[] filas) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String texto = btn.getText();
                
                // Verificar si es un botón de asiento
                if (texto.matches("[A-D][1-4]")) {
                    char filaChar = texto.charAt(0);
                    int col = Integer.parseInt(texto.substring(1)) - 1;
                    int filaIndex = filaChar - 'A';
                    
                    if (filaIndex >= 0 && filaIndex < estadoAsientos.length && 
                        col >= 0 && col < estadoAsientos[filaIndex].length) {
                        
                        boolean ocupado = estadoAsientos[filaIndex][col];
                        
                        if (ocupado) {
                            btn.setBackground(new Color(231, 76, 60)); // Rojo
                            btn.setForeground(Color.WHITE);
                            btn.setEnabled(false);
                            btn.setToolTipText("🔴 ASIENTO OCUPADO");
                        } else {
                            btn.setBackground(new Color(46, 204, 113)); // Verde
                            btn.setForeground(Color.BLACK);
                            btn.setEnabled(true);
                            btn.setToolTipText("🟢 ASIENTO DISPONIBLE - Click para seleccionar");
                        }
                    }
                }
            } else if (comp instanceof JPanel) {
                actualizarBotonesEnPanel((JPanel) comp, estadoAsientos, filas);
            }
        }
    }
}