package cl.usm.fidelidad;

import java.time.LocalDate;

/**
 * Clase que representa un cliente en el sistema de fidelidad
 */
public class Cliente {
    private String id;
    private String nombre;
    private String correo;
    private int puntos;
    private Nivel nivel;
    private int streakDias;
    private int comprasHoy;
    private LocalDate ultimaCompra;

    public Cliente(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.nivel = Nivel.BRONCE;
        this.streakDias = 0;
        this.comprasHoy = 0;
        this.ultimaCompra = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
        actualizarNivel();
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public int getStreakDias() {
        return streakDias;
    }

    public void setStreakDias(int streakDias) {
        this.streakDias = streakDias;
    }

    public int getComprasHoy() {
        return comprasHoy;
    }

    public void setComprasHoy(int comprasHoy) {
        this.comprasHoy = comprasHoy;
    }

    public LocalDate getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(LocalDate ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    /**
     * Valida que el correo electrónico contenga el símbolo @
     */
    public void validarCorreo() {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("El correo electrónico debe contener el símbolo @");
        }
    }

    /**
     * Agrega puntos al cliente y actualiza su nivel
     */
    public void agregarPuntos(int puntosAgregar) {
        this.puntos += puntosAgregar;
        actualizarNivel();
    }

    /**
     * Registra una compra y actualiza el contador de streak
     */
    public void registrarCompra(LocalDate fechaCompra) {
        // Si es un día diferente, reiniciar contador
        if (ultimaCompra == null || !ultimaCompra.equals(fechaCompra)) {
            comprasHoy = 0;
        }
        
        comprasHoy++;
        ultimaCompra = fechaCompra;
    }

    /**
     * Verifica si el cliente debe recibir bonus de streak
     */
    public boolean debeRecibirBonusStreak() {
        return comprasHoy == 3;
    }

    /**
     * Actualiza el nivel del cliente basado en sus puntos totales
     */
    private void actualizarNivel() {
        this.nivel = Nivel.determinarNivel(this.puntos);
    }
} 