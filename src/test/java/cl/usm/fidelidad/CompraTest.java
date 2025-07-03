package cl.usm.fidelidad;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * Pruebas unitarias para la clase Compra
 * Siguiendo TDD: escribimos las pruebas primero
 */
class CompraTest {

    private Cliente cliente;
    private LocalDate fecha;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("1", "Juan Pérez", "juan@email.com");
        fecha = LocalDate.now();
    }

    @Test
    void testCrearCompraConValoresIniciales() {
        // Given: datos de compra
        String idCompra = "COMP001";
        double monto = 150.0;
        
        // When: crear compra
        Compra compra = new Compra(idCompra, cliente.getId(), monto, fecha);
        
        // Then: verificar valores
        assertEquals(idCompra, compra.getIdCompra());
        assertEquals(cliente.getId(), compra.getIdCliente());
        assertEquals(monto, compra.getMonto(), 0.01);
        assertEquals(fecha, compra.getFecha());
    }

    @Test
    void testCalcularPuntosBasicos() {
        // Given: compra de $150
        Compra compra = new Compra("COMP001", cliente.getId(), 150.0, fecha);
        
        // When: calcular puntos
        int puntos = compra.calcularPuntos(cliente);
        
        // Then: $150 = 1 punto (redondeo hacia abajo)
        assertEquals(1, puntos);
    }

    @Test
    void testCalcularPuntosConMultiplicadorNivel() {
        // Given: cliente plata (×1.2) y compra de $200
        cliente.agregarPuntos(500); // Subir a nivel Plata
        Compra compra = new Compra("COMP001", cliente.getId(), 200.0, fecha);
        
        // When: calcular puntos
        int puntos = compra.calcularPuntos(cliente);
        
        // Then: $200 = 2 puntos × 1.2 = 2.4 → 2 puntos (redondeo hacia abajo)
        assertEquals(2, puntos);
    }

    @Test
    void testCalcularPuntosNivelOro() {
        // Given: cliente oro (×1.5) y compra de $300
        cliente.agregarPuntos(1500); // Subir a nivel Oro
        Compra compra = new Compra("COMP001", cliente.getId(), 300.0, fecha);
        
        // When: calcular puntos
        int puntos = compra.calcularPuntos(cliente);
        
        // Then: $300 = 3 puntos × 1.5 = 4.5 → 4 puntos (redondeo hacia abajo)
        assertEquals(4, puntos);
    }
} 