package com.mycompany.PantallaInicio;

import PantallaPrincipal.PantallaPrincipal;
import com.mycompany.cine.controlador.Cine;
import com.mycompany.cine.modelo.Funcion;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PantallaInicio extends javax.swing.JFrame {
    
    private Cine cine;
    private List<Funcion> funciones;
    
    public PantallaInicio() {
        initComponents();
        setTitle("Cine VIP - Login");
        setLocationRelativeTo(null);
        
        // Inicializar datos CORRECTAMENTE
        inicializarDatos();
    }
    
    private void inicializarDatos() {
        // Opción CORRECTA: Usar el cine que YA TIENE funciones predefinidas
        cine = new Cine();  // El constructor de Cine crea 3 funciones automáticamente
        
        // Obtener las funciones REALES del cine
        funciones = cine.getFunciones();  // ¡Esto es lo importante!
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        tfContraseña = new javax.swing.JPasswordField();
        lblDatosdeIngreso = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        lblUsuario.setText("Usuario");

        lblContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblContraseña.setForeground(new java.awt.Color(0, 0, 0));
        lblContraseña.setText("Contraseña");

        tfUsuario.setBackground(new java.awt.Color(204, 204, 255));
        tfUsuario.setForeground(new java.awt.Color(0, 0, 0));
        tfUsuario.setText("Empleado001");
        tfUsuario.addActionListener(this::tfUsuarioActionPerformed);

        tfContraseña.setBackground(new java.awt.Color(204, 204, 255));
        tfContraseña.setForeground(new java.awt.Color(0, 0, 0));
        tfContraseña.setText("1234");

        lblDatosdeIngreso.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblDatosdeIngreso.setForeground(new java.awt.Color(0, 0, 0));
        lblDatosdeIngreso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDatosdeIngreso.setText("CINE VIP - ACCESO EMPLEADOS");

        btnIngresar.setBackground(new java.awt.Color(51, 51, 255));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 24));
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(this::btnIngresarActionPerformed);

        btnCancelar.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 24));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDatosdeIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUsuario)
                            .addComponent(lblContraseña))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(tfContraseña))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblDatosdeIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {
        String usuario = tfUsuario.getText();
        String contraseña = new String(tfContraseña.getPassword());
        
        if (usuario.equals("Empleado001") && contraseña.equals("1234")) {
            JOptionPane.showMessageDialog(this, 
                "Acceso concedido\nBienvenido Empleado001", 
                "Login Exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // VERIFICAR que tenemos funciones
            if (funciones == null || funciones.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Error: No hay funciones disponibles. Reiniciando datos...", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                inicializarDatos(); // Intentar cargar de nuevo
            }
            
            if (funciones != null && !funciones.isEmpty()) {
                // Crear array de nombres de funciones para el diálogo
                String[] opcionesFunciones = new String[funciones.size()];
                for (int i = 0; i < funciones.size(); i++) {
                    Funcion f = funciones.get(i);
                    opcionesFunciones[i] = f.getNombreCompleto() + " - $" + f.getPrecio();
                }
                
                // Mostrar diálogo para seleccionar función
                String funcionSeleccionada = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione una función:",
                    "Selección de Función",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesFunciones,
                    opcionesFunciones[0]
                );
                
                if (funcionSeleccionada != null) {
                    // Encontrar la función seleccionada
                    Funcion funcion = null;
                    for (Funcion f : funciones) {
                        String nombreFuncion = f.getNombreCompleto() + " - $" + f.getPrecio();
                        if (nombreFuncion.equals(funcionSeleccionada)) {
                            funcion = f;
                            break;
                        }
                    }
                    
                    if (funcion != null && cine != null) {
                        // CORRECTO: Pasar cine y funcion al constructor
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(cine, funcion);
                        pantallaPrincipal.setVisible(true);
                        pantallaPrincipal.setLocationRelativeTo(null);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "Error al obtener datos de la función seleccionada", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No hay funciones disponibles en el sistema", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Usuario o contraseña incorrectos", 
                "Error de Login", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        tfUsuario.setText("");
        tfContraseña.setText("");
        JOptionPane.showMessageDialog(this, 
            "Campos limpiados", 
            "Información", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void tfUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        tfContraseña.requestFocus();
    }

    // Variables declaration
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblDatosdeIngreso;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField tfContraseña;
    private javax.swing.JTextField tfUsuario;
}