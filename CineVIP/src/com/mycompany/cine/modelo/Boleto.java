package com.mycompany.cine.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Boleto {
    private final String nombreCliente;
    private final Funcion funcion;
    private final String asiento;
    private final double precio;
    private final LocalDateTime fechaVenta;
    
    public Boleto(String nombreCliente, Funcion funcion, String asiento) {
        this.nombreCliente = (nombreCliente == null || nombreCliente.trim().isEmpty()) 
                ? "Cliente no registrado" : nombreCliente;
        this.funcion = funcion;
        this.asiento = asiento;
        this.precio = funcion.getPrecio();
        this.fechaVenta = LocalDateTime.now();
    }
    
    public String getNombreCliente() { return nombreCliente; }
    public Funcion getFuncion() { return funcion; }
    public String getAsiento() { return asiento; }
    public double getPrecio() { return precio; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaVenta.format(formatter);
    }
    
    @Override
    public String toString() {
        return String.format("%s | %s | Asiento: %s | $%.2f | %s",
                nombreCliente, 
                funcion.getNombreCompleto(), 
                asiento, 
                precio,
                getFechaFormateada());
    }
}