package cl.usm.fidelidad;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Cliente
 */
class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("1", "Juan Pérez", "juan@email.com");
    }

    @Test
    void testCrearClienteConValoresIniciales() {
        // Given: cliente creado en setUp()
        
        // Then: verificar valores iniciales
        assertEquals("1", cliente.getId());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("juan@email.com", cliente.getCorreo());
        assertEquals(0, cliente.getPuntos());
        assertEquals(Nivel.BRONCE, cliente.getNivel());
        assertEquals(0, cliente.getStreakDias());
    }

    @Test
    void testValidarCorreoElectronico() {
        // Given: cliente con correo válido
        Cliente clienteValido = new Cliente("2", "María", "maria@test.com");
        
        // Then: no debe lanzar excepción
        assertDoesNotThrow(() -> clienteValido.validarCorreo());
    }

    @Test
    void testCorreoInvalidoDebeLanzarExcepcion() {
        // Given: cliente con correo inválido
        Cliente clienteInvalido = new Cliente("3", "Pedro", "correo-invalido");
        
        // Then: debe lanzar excepción
        assertThrows(IllegalArgumentException.class, () -> clienteInvalido.validarCorreo());
    }

    @Test
    void testAgregarPuntos() {
        // Given: cliente con 0 puntos
        
        // When: agregar 100 puntos
        cliente.agregarPuntos(100);
        
        // Then: debe tener 100 puntos
        assertEquals(100, cliente.getPuntos());
    }

    @Test
    void testActualizarNivelSegunPuntos() {
        // Given: cliente bronce
        
        // When: agregar 500 puntos (umbral para Plata)
        cliente.agregarPuntos(500);
        
        // Then: debe subir a nivel Plata
        assertEquals(Nivel.PLATA, cliente.getNivel());
    }
} 