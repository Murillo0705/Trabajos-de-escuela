package com.mycompany.cine.controlador;

import com.mycompany.cine.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class Cine {
    private final List<Funcion> funciones;
    private final List<Boleto> boletosVendidos;
    private static final double PRECIO_BOLETO = 250.00;
    private String nombre;
    private int id;
    
    // Patrón Singleton para acceso global
    private static Cine instance;
    
    public static Cine getInstance() {
        if (instance == null) {
            instance = new Cine();
        }
        return instance;
    }
    
    public Cine() {
        funciones = new ArrayList<>();
        boletosVendidos = new ArrayList<>();
        this.nombre = "Cine VIP";
        this.id = 1;
        inicializarFunciones();
    }
    
    private void inicializarFunciones() {
        funciones.add(new Funcion(1, "El Teléfono Negro 2", "2:00 PM", PRECIO_BOLETO));
        funciones.add(new Funcion(2, "Chainsaw Man La Película: Arco de Reze", "5:00 PM", PRECIO_BOLETO));
        funciones.add(new Funcion(3, "A pesar de ti", "8:00 PM", PRECIO_BOLETO));
    }
    
    // === MÉTODOS PARA REPORTES ===
    
    public List<Funcion> getFunciones() {
        return new ArrayList<>(funciones);
    }
    
    public Funcion getFuncion(int id) {
        for (Funcion funcion : funciones) {
            if (funcion.getId() == id) {
                return funcion;
            }
        }
        return null;
    }
    
    public boolean venderBoleto(int idFuncion, String asiento, String nombreCliente) {
        Funcion funcion = getFuncion(idFuncion);
        if (funcion != null && funcion.venderAsiento(asiento, nombreCliente)) {
            Boleto boleto = new Boleto(nombreCliente, funcion, asiento);
            boletosVendidos.add(boleto);
            return true;
        }
        return false;
    }
    
    public List<Boleto> getBoletosVendidos() {
        return new ArrayList<>(boletosVendidos);
    }
    
    public int getTotalBoletosVendidos() {
        return boletosVendidos.size();
    }
    
    public double getGananciasTotales() {
        double total = 0;
        for (Boleto boleto : boletosVendidos) {
            total += boleto.getPrecio();
        }
        return total;
    }
    
    public String[][] getReporteGeneral() {
        String[][] reporte = new String[boletosVendidos.size()][4];
        for (int i = 0; i < boletosVendidos.size(); i++) {
            Boleto boleto = boletosVendidos.get(i);
            reporte[i][0] = boleto.getNombreCliente();
            reporte[i][1] = boleto.getFuncion().getNombreCompleto();
            reporte[i][2] = boleto.getAsiento();
            reporte[i][3] = String.format("$%.2f", boleto.getPrecio());
        }
        return reporte;
    }
    
    public String[][] getReportePorFuncion() {
        String[][] reporte = new String[funciones.size()][3];
        for (int i = 0; i < funciones.size(); i++) {
            Funcion funcion = funciones.get(i);
            reporte[i][0] = funcion.getNombreCompleto();
            reporte[i][1] = String.valueOf(funcion.getCantidadBoletosVendidos());
            reporte[i][2] = String.format("$%.2f", funcion.getTotalVendido());
        }
        return reporte;
    }
    
    // NUEVO: Método para obtener estadísticas
    public String[] getEstadisticas() {
        String[] stats = new String[4];
        stats[0] = "Total Boletos Vendidos: " + getTotalBoletosVendidos();
        stats[1] = String.format("Ganancias Totales: $%.2f", getGananciasTotales());
        stats[2] = "Funciones Activas: " + funciones.size();
        stats[3] = "Asientos Totales Disponibles: " + (funciones.size() * 16 - getTotalBoletosVendidos());
        return stats;
    }
    
    // NUEVO: Método para obtener datos de reportes detallados
    public Object[][] getDatosReportesDetallados() {
        if (boletosVendidos.isEmpty()) {
            return new Object[0][0];
        }
        
        Object[][] datos = new Object[boletosVendidos.size()][7];
        for (int i = 0; i < boletosVendidos.size(); i++) {
            Boleto b = boletosVendidos.get(i);
            datos[i][0] = i + 1; // ID
            datos[i][1] = "Venta"; // Tipo
            datos[i][2] = "Boleto para " + b.getFuncion().getPelicula(); // Descripción
            datos[i][3] = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()); // Fecha
            datos[i][4] = b.getNombreCliente(); // Usuario/Cliente
            datos[i][5] = "Completado"; // Estado
            datos[i][6] = "Ver"; // Acciones
        }
        return datos;
    }
    
    public void reiniciarSistema() {
        funciones.clear();
        boletosVendidos.clear();
        inicializarFunciones();
    }
    
    // Setters y getters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}