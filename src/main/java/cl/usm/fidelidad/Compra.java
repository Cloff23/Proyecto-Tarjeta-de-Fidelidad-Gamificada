package cl.usm.fidelidad;

import java.time.LocalDate;

/**
 * Clase que representa una compra en el sistema de fidelidad
 */
public class Compra {
    private String idCompra;
    private String idCliente;
    private double monto;
    private LocalDate fecha;

    public Compra(String idCompra, String idCliente, double monto, LocalDate fecha) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Calcula los puntos que otorga esta compra al cliente
     * Regla: $100 = 1 punto (redondeo hacia abajo)
     * Multiplicador por nivel: Bronce ×1, Plata ×1.2, Oro ×1.5, Platino ×2
     */
    public int calcularPuntos(Cliente cliente) {
        // Calcular puntos base: $100 = 1 punto
        int puntosBase = (int) Math.floor(monto / 100.0);
        
        // Aplicar multiplicador del nivel del cliente
        double multiplicador = cliente.getNivel().getMultiplicador();
        double puntosConMultiplicador = puntosBase * multiplicador;
        
        // Redondear hacia abajo
        return (int) Math.floor(puntosConMultiplicador);
    }
} 