package cl.usm.fidelidad;

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

    public Cliente(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.nivel = Nivel.BRONCE;
        this.streakDias = 0;
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
     * Actualiza el nivel del cliente basado en sus puntos totales
     */
    private void actualizarNivel() {
        this.nivel = Nivel.determinarNivel(this.puntos);
    }
} 