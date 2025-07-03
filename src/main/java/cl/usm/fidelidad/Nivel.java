package cl.usm.fidelidad;

/**
 * Enum que representa los niveles de fidelidad del programa
 */
public enum Nivel {
    BRONCE(0, 499, 1.0),
    PLATA(500, 1499, 1.2),
    ORO(1500, 2999, 1.5),
    PLATINO(3000, Integer.MAX_VALUE, 2.0);

    private final int umbralMinimo;
    private final int umbralMaximo;
    private final double multiplicador;

    Nivel(int umbralMinimo, int umbralMaximo, double multiplicador) {
        this.umbralMinimo = umbralMinimo;
        this.umbralMaximo = umbralMaximo;
        this.multiplicador = multiplicador;
    }

    public int getUmbralMinimo() {
        return umbralMinimo;
    }

    public int getUmbralMaximo() {
        return umbralMaximo;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    /**
     * Determina el nivel basado en los puntos totales
     */
    public static Nivel determinarNivel(int puntos) {
        for (Nivel nivel : values()) {
            if (puntos >= nivel.umbralMinimo && puntos <= nivel.umbralMaximo) {
                return nivel;
            }
        }
        return BRONCE; // nivel por defecto
    }
} 