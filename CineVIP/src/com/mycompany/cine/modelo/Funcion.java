package com.mycompany.cine.modelo;

import java.util.ArrayList;
import java.util.List;

public class Funcion {
    private int id;               // Cambiado de final para poder usar setters
    private String pelicula;      // Cambiado de final para poder usar setters  
    private final String hora;    // Mantenido como final (no tiene setter)
    private double precio;        // Cambiado de final para poder usar setters
    private final boolean[] asientos; // 16 asientos (4x4)
    private final List<Boleto> boletosVendidos;
    
    // Constructor principal
    public Funcion(int id, String pelicula, String hora, double precio) {
        this.id = id;
        this.pelicula = pelicula;
        this.hora = hora;
        this.precio = precio;
        this.asientos = new boolean[16];
        this.boletosVendidos = new ArrayList<>();
    }
    
    // Constructor vacío (para usar en PantallaInicio)
    public Funcion() {
        this(0, "Sin título", "00:00", 0.0);
    }
    
    // Getters
    public int getId() { return id; }
    public String getPelicula() { return pelicula; }
    public String getHora() { return hora; }
    public double getPrecio() { return precio; }
    public String getNombreCompleto() { return pelicula + " - " + hora; }
    
    // Métodos de asientos
    public boolean isAsientoDisponible(String codigoAsiento) {
        try {
            int indice = convertirAsientoAIndice(codigoAsiento);
            return !asientos[indice];
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<String> getAsientosDisponibles() {
        List<String> disponibles = new ArrayList<>();
        String[] filas = {"A", "B", "C", "D"};
        
        for (int fila = 0; fila < 4; fila++) {
            for (int col = 1; col <= 4; col++) {
                String asiento = filas[fila] + col;
                if (isAsientoDisponible(asiento)) {
                    disponibles.add(asiento);
                }
            }
        }
        return disponibles;
    }
    
    public boolean venderAsiento(String codigoAsiento, String nombreCliente) {
        if (isAsientoDisponible(codigoAsiento)) {
            int indice = convertirAsientoAIndice(codigoAsiento);
            asientos[indice] = true;
            Boleto boleto = new Boleto(nombreCliente, this, codigoAsiento);
            boletosVendidos.add(boleto);
            return true;
        }
        return false;
    }
    
    private int convertirAsientoAIndice(String codigoAsiento) {
        char fila = codigoAsiento.charAt(0);
        int columna = Integer.parseInt(codigoAsiento.substring(1));
        
        int filaIndice = fila - 'A';
        return filaIndice * 4 + (columna - 1);
    }
    
    public int getCantidadBoletosVendidos() {
        return boletosVendidos.size();
    }
    
    public double getTotalVendido() {
        return getCantidadBoletosVendidos() * precio;
    }
    
    public List<Boleto> getBoletosVendidos() {
        return new ArrayList<>(boletosVendidos);
    }
    
    public boolean[][] getEstadoAsientos() {
        boolean[][] estado = new boolean[4][4];
        for (int i = 0; i < 16; i++) {
            estado[i/4][i%4] = asientos[i];
        }
        return estado;
    }
    
    public int getAsientosDisponiblesCount() {
        int count = 0;
        for (boolean ocupado : asientos) {
            if (!ocupado) count++;
        }
        return count;
    }

    // Setters corregidos (sin excepciones)
    public void setEstadoAsientos(boolean[][] nuevoEstado) {
        if (nuevoEstado != null && nuevoEstado.length == 4 && nuevoEstado[0].length == 4) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int indice = i * 4 + j;
                    asientos[indice] = nuevoEstado[i][j];
                }
            }
        }
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
    
    // NOTA: No podemos tener setNombreCompleto porque getNombreCompleto() 
    // combina película + hora, y hora es final
    public void setNombreCompleto(String nombreCompleto) {
        // Ignoramos este método porque no tiene sentido
        // getNombreCompleto() es una combinación de película y hora
    }
    
    public void setId(int id) {
        this.id = id;
    }
}