package cl.usm.fidelidad;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * Pruebas unitarias para la funcionalidad de streak
 * Siguiendo TDD: escribimos las pruebas primero
 */
class StreakTest {

    private Cliente cliente;
    private LocalDate fecha;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("1", "Juan Pérez", "juan@email.com");
        fecha = LocalDate.now();
    }

    @Test
    void testStreakDe3ComprasMismoDia() {
        // Given: cliente con 0 compras en el día
        
        // When: hacer 3 compras en el mismo día
        Compra compra1 = new Compra("COMP001", cliente.getId(), 100.0, fecha);
        Compra compra2 = new Compra("COMP002", cliente.getId(), 100.0, fecha);
        Compra compra3 = new Compra("COMP003", cliente.getId(), 100.0, fecha);
        
        // Then: debe obtener bonus de +10 puntos en la tercera compra
        int puntos1 = compra1.calcularPuntos(cliente);
        int puntos2 = compra2.calcularPuntos(cliente);
        int puntos3 = compra3.calcularPuntos(cliente);
        
        assertEquals(1, puntos1); // 1 punto base
        assertEquals(1, puntos2); // 1 punto base
        assertEquals(11, puntos3); // 1 punto base + 10 bonus streak
    }

    @Test
    void testStreakSeReiniciaEnDiferenteDia() {
        // Given: cliente con 2 compras en el día actual
        Compra compra1 = new Compra("COMP001", cliente.getId(), 100.0, fecha);
        Compra compra2 = new Compra("COMP002", cliente.getId(), 100.0, fecha);
        
        // When: hacer compra en día diferente
        LocalDate fechaDiferente = fecha.plusDays(1);
        Compra compra3 = new Compra("COMP003", cliente.getId(), 100.0, fechaDiferente);
        
        // Then: no debe obtener bonus streak
        int puntos3 = compra3.calcularPuntos(cliente);
        assertEquals(1, puntos3); // Solo 1 punto base, sin bonus
    }

    @Test
    void testStreakNoSeActivaConMenosDe3Compras() {
        // Given: cliente con 0 compras
        
        // When: hacer solo 2 compras en el mismo día
        Compra compra1 = new Compra("COMP001", cliente.getId(), 100.0, fecha);
        Compra compra2 = new Compra("COMP002", cliente.getId(), 100.0, fecha);
        
        // Then: no debe obtener bonus streak
        int puntos1 = compra1.calcularPuntos(cliente);
        int puntos2 = compra2.calcularPuntos(cliente);
        
        assertEquals(1, puntos1); // Solo 1 punto base
        assertEquals(1, puntos2); // Solo 1 punto base, sin bonus
    }
} 