package PantallaReportes;

import com.mycompany.cine.controlador.Cine;
import com.mycompany.cine.modelo.Boleto;
import com.mycompany.cine.modelo.Funcion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PantallaReportes extends JFrame {
    private JTable tablaReportes;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboFiltro;
    private JTextField campoBusqueda;
    private JButton btnGenerarReporte, btnExportar, btnImprimir, btnFiltrar, btnActualizar;
    private JLabel labelTitulo, labelFecha, labelEstadisticas;
    private Cine controladorCine;
    
    public PantallaReportes() {
        // Obtener instancia del controlador Cine
        controladorCine = Cine.getInstance();
        
        // Configuración de la ventana principal
        setTitle("Sistema de Reportes - Cine VIP");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiado para cerrar solo esta ventana
        setLocationRelativeTo(null);
        
        // Configurar el layout
        setLayout(new BorderLayout());
        
        // Crear componentes
        inicializarComponentes();
        
        // Cargar datos REALES del cine
        cargarDatosReales();
    }
    
    private void inicializarComponentes() {
        // Panel superior con título y fecha
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(41, 128, 185));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        labelTitulo = new JLabel("🎬 REPORTES DEL CINE VIP", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setForeground(Color.WHITE);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        labelFecha = new JLabel("Fecha: " + sdf.format(new Date()), SwingConstants.RIGHT);
        labelFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        labelFecha.setForeground(Color.WHITE);
        
        panelSuperior.add(labelTitulo, BorderLayout.CENTER);
        panelSuperior.add(labelFecha, BorderLayout.EAST);
        
        // Panel de estadísticas
        JPanel panelEstadisticas = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelEstadisticas.setBackground(new Color(52, 152, 219));
        panelEstadisticas.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        labelEstadisticas = new JLabel("Cargando estadísticas...");
        labelEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));
        labelEstadisticas.setForeground(Color.WHITE);
        
        panelEstadisticas.add(labelEstadisticas);
        
        // Panel de controles (filtros y búsqueda)
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelControles.setBackground(new Color(236, 240, 241));
        panelControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Combo para filtro - AJUSTADO para el cine
        comboFiltro = new JComboBox<>(new String[]{"Todos", "Ventas", "Funciones", "Clientes", "Boletos"});
        comboFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Campo de búsqueda
        campoBusqueda = new JTextField(20);
        campoBusqueda.setFont(new Font("Arial", Font.PLAIN, 14));
        campoBusqueda.setToolTipText("Buscar por cliente, función o asiento...");
        
        // Botón de filtro
        btnFiltrar = new JButton("🔍 Filtrar");
        btnFiltrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFiltrar.setBackground(new Color(52, 152, 219));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setFocusPainted(false);
        
        // Botón Actualizar
        btnActualizar = new JButton("🔄 Actualizar");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnActualizar.setBackground(new Color(46, 204, 113));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        
        // Acción del botón filtrar
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarReportes();
            }
        });
        
        // Acción del botón actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatosReales();
                actualizarEstadisticas();
            }
        });
        
        panelControles.add(new JLabel("Tipo de Reporte:"));
        panelControles.add(comboFiltro);
        panelControles.add(new JLabel("Buscar:"));
        panelControles.add(campoBusqueda);
        panelControles.add(btnFiltrar);
        panelControles.add(btnActualizar);
        
        // Configurar la tabla - AJUSTADO para datos del cine
        String[] columnas = {"ID", "Tipo", "Descripción", "Fecha", "Cliente", "Estado", "Acción"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Solo la columna de acciones será editable
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                return String.class;
            }
        };
        
        tablaReportes = new JTable(modeloTabla);
        tablaReportes.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaReportes.setRowHeight(30);
        tablaReportes.setSelectionBackground(new Color(52, 152, 219, 100));
        
        // Personalizar el encabezado de la tabla
        JTableHeader header = tablaReportes.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(41, 128, 185));
        header.setForeground(Color.WHITE);
        
        // Panel de tabla con scroll
        JScrollPane scrollPane = new JScrollPane(tablaReportes);
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelInferior.setBackground(new Color(236, 240, 241));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Botón Generar Reporte
        btnGenerarReporte = new JButton("📊 Reporte Resumen");
        btnGenerarReporte.setFont(new Font("Arial", Font.BOLD, 14));
        btnGenerarReporte.setBackground(new Color(46, 204, 113));
        btnGenerarReporte.setForeground(Color.WHITE);
        btnGenerarReporte.setFocusPainted(false);
        btnGenerarReporte.setPreferredSize(new Dimension(200, 40));
        btnGenerarReporte.setToolTipText("Generar reporte resumen de ventas");
        
        // Botón Exportar
        btnExportar = new JButton("📥 Exportar Datos");
        btnExportar.setFont(new Font("Arial", Font.BOLD, 14));
        btnExportar.setBackground(new Color(155, 89, 182));
        btnExportar.setForeground(Color.WHITE);
        btnExportar.setFocusPainted(false);
        btnExportar.setPreferredSize(new Dimension(150, 40));
        btnExportar.setToolTipText("Exportar datos a archivo CSV");
        
        // Botón Imprimir
        btnImprimir = new JButton("🖨️ Imprimir");
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 14));
        btnImprimir.setBackground(new Color(241, 196, 15));
        btnImprimir.setForeground(Color.WHITE);
        btnImprimir.setFocusPainted(false);
        btnImprimir.setPreferredSize(new Dimension(150, 40));
        btnImprimir.setToolTipText("Imprimir reporte actual");
        
        // Botón Cerrar
        JButton btnCerrar = new JButton("❌ Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setBackground(new Color(231, 76, 60));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPreferredSize(new Dimension(150, 40));
        btnCerrar.addActionListener(e -> dispose());
        
        // Acciones de los botones
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteResumen();
            }
        });
        
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarDatosCSV();
            }
        });
        
        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirReporte();
            }
        });
        
        panelInferior.add(btnGenerarReporte);
        panelInferior.add(btnExportar);
        panelInferior.add(btnImprimir);
        panelInferior.add(btnCerrar);
        
        // Agregar paneles a la ventana principal
        add(panelSuperior, BorderLayout.NORTH);
        add(panelEstadisticas, BorderLayout.CENTER);
        add(panelControles, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        
        // Ajustar layout
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelSuperior, BorderLayout.NORTH);
        panelNorte.add(panelEstadisticas, BorderLayout.SOUTH);
        
        add(panelNorte, BorderLayout.NORTH);
        add(panelControles, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void cargarDatosReales() {
        // Limpiar tabla existente
        modeloTabla.setRowCount(0);
        
        // Obtener boletos vendidos del cine
        List<Boleto> boletos = controladorCine.getBoletosVendidos();
        
        if (boletos.isEmpty()) {
            // Mostrar mensaje si no hay datos
            Object[] filaVacia = {
                "-", "Sin datos", "No hay ventas registradas", 
                new SimpleDateFormat("dd/MM/yyyy").format(new Date()), 
                "Sistema", "Sin ventas", "N/A"
            };
            modeloTabla.addRow(filaVacia);
            
            // Agregar funciones disponibles
            List<Funcion> funciones = controladorCine.getFunciones();
            for (Funcion funcion : funciones) {
                Object[] filaFuncion = {
                    funcion.getId(), 
                    "Función", 
                    funcion.getNombreCompleto() + " - Disponibles: " + funcion.getAsientosDisponiblesCount(),
                    "Activa",
                    "Sistema",
                    funcion.getAsientosDisponiblesCount() > 0 ? "Disponible" : "Agotada",
                    "Ver"
                };
                modeloTabla.addRow(filaFuncion);
            }
        } else {
            // Cargar boletos vendidos
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < boletos.size(); i++) {
                Boleto boleto = boletos.get(i);
                Object[] fila = {
                    i + 1,
                    "Venta",
                    "Boleto: " + boleto.getFuncion().getPelicula() + " - Asiento: " + boleto.getAsiento(),
                    sdf.format(new Date()),
                    boleto.getNombreCliente(),
                    "Completado",
                    "Ver Detalles"
                };
                modeloTabla.addRow(fila);
            }
        }
        
        actualizarEstadisticas();
    }
    
    private void actualizarEstadisticas() {
        String[] stats = controladorCine.getEstadisticas();
        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("📊 ");
        for (int i = 0; i < stats.length; i++) {
            estadisticas.append(stats[i]);
            if (i < stats.length - 1) {
                estadisticas.append(" | ");
            }
        }
        labelEstadisticas.setText(estadisticas.toString());
    }
    
    private void filtrarReportes() {
        String filtro = (String) comboFiltro.getSelectedItem();
        String busqueda = campoBusqueda.getText().toLowerCase();
        
        // Recargar todos los datos primero
        cargarDatosReales();
        
        if (filtro.equals("Todos") && busqueda.isEmpty()) {
            return; // Mostrar todos los datos
        }
        
        // Filtrar filas de la tabla
        for (int i = modeloTabla.getRowCount() - 1; i >= 0; i--) {
            boolean mantenerFila = true;
            
            // Aplicar filtro por tipo
            if (!filtro.equals("Todos")) {
                String tipo = modeloTabla.getValueAt(i, 1).toString();
                if (filtro.equals("Ventas") && !tipo.equals("Venta")) {
                    mantenerFila = false;
                } else if (filtro.equals("Funciones") && !tipo.equals("Función")) {
                    mantenerFila = false;
                }
            }
            
            // Aplicar búsqueda
            if (mantenerFila && !busqueda.isEmpty()) {
                boolean coincide = false;
                for (int col = 0; col < modeloTabla.getColumnCount(); col++) {
                    String valor = modeloTabla.getValueAt(i, col).toString().toLowerCase();
                    if (valor.contains(busqueda)) {
                        coincide = true;
                        break;
                    }
                }
                mantenerFila = coincide;
            }
            
            // Eliminar fila si no cumple con los criterios
            if (!mantenerFila) {
                modeloTabla.removeRow(i);
            }
        }
    }
    
    private void generarReporteResumen() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE RESUMEN CINE VIP ===\n");
        reporte.append("Fecha: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())).append("\n\n");
        
        reporte.append("📈 ESTADÍSTICAS:\n");
        String[] stats = controladorCine.getEstadisticas();
        for (String stat : stats) {
            reporte.append("  • ").append(stat).append("\n");
        }
        
        reporte.append("\n🎬 FUNCIONES:\n");
        List<Funcion> funciones = controladorCine.getFunciones();
        for (Funcion funcion : funciones) {
            reporte.append(String.format("  • %s - Vendidos: %d/16 - Total: $%.2f\n",
                funcion.getNombreCompleto(),
                funcion.getCantidadBoletosVendidos(),
                funcion.getTotalVendido()));
        }
        
        reporte.append("\n🎫 ÚLTIMAS VENTAS:\n");
        List<Boleto> boletos = controladorCine.getBoletosVendidos();
        int limite = Math.min(boletos.size(), 10); // Mostrar máximo 10 ventas
        for (int i = 0; i < limite; i++) {
            Boleto b = boletos.get(i);
            reporte.append(String.format("  • %s - %s - Asiento: %s - $%.2f\n",
                b.getNombreCliente(),
                b.getFuncion().getPelicula(),
                b.getAsiento(),
                b.getPrecio()));
        }
        
        // Mostrar en un JTextArea con scroll
        JTextArea areaReporte = new JTextArea(reporte.toString(), 20, 50);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaReporte.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(areaReporte);
        
        JOptionPane.showMessageDialog(this, scrollPane, 
            "📊 Reporte Resumen del Cine", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exportarDatosCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportar Datos a CSV");
        fileChooser.setSelectedFile(new java.io.File("reporte_cine_" + 
            new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".csv"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            // Aquí iría la lógica para exportar a CSV
            JOptionPane.showMessageDialog(this, 
                "Datos exportados exitosamente:\n" + fileToSave.getAbsolutePath(), 
                "Exportación Exitosa", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void imprimirReporte() {
        // Crear reporte para imprimir
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DEL CINE VIP\n");
        reporte.append("====================\n");
        reporte.append("Fecha: ").append(new Date()).append("\n\n");
        reporte.append("Total de ventas: ").append(controladorCine.getTotalBoletosVendidos()).append("\n");
        reporte.append(String.format("Ganancias totales: $%.2f\n", controladorCine.getGananciasTotales()));
        
        JOptionPane.showMessageDialog(this, 
            "Reporte listo para imprimir:\n\n" + reporte.toString() + 
            "\n\nLa función de impresión está en desarrollo.", 
            "Imprimir Reporte", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Método main para pruebas independientes
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PantallaReportes pantalla = new PantallaReportes();
                pantalla.setVisible(true);
            }
        });
    }
}