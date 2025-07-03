package cl.usm.fidelidad;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Main (interfaz de usuario)
 * Siguiendo TDD: escribimos las pruebas primero
 */
class MainTest {

    private Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testMainTieneAplicacionConsola() {
        // Given: Main inicializado
        
        // When: obtener la aplicación de consola
        AplicacionConsola app = main.getAplicacionConsola();
        
        // Then: verificar que existe
        assertNotNull(app);
    }

    @Test
    void testProcesarOpcionCrearCliente() {
        // Given: datos de cliente válidos
        String[] opciones = {"1", "CLI001", "Juan Pérez", "juan@email.com"};
        
        // When: procesar opción crear cliente
        boolean resultado = main.procesarOpcionCrearCliente(opciones);
        
        // Then: verificar que se creó correctamente
        assertTrue(resultado);
        Cliente cliente = main.getAplicacionConsola().buscarCliente("CLI001");
        assertNotNull(cliente);
        assertEquals("Juan Pérez", cliente.getNombre());
    }

    @Test
    void testProcesarOpcionCrearClienteCorreoInvalido() {
        // Given: datos de cliente con correo inválido
        String[] opciones = {"1", "CLI002", "María", "correo-invalido"};
        
        // When: procesar opción crear cliente
        boolean resultado = main.procesarOpcionCrearCliente(opciones);
        
        // Then: verificar que no se creó
        assertFalse(resultado);
    }

    @Test
    void testProcesarOpcionRegistrarCompra() {
        // Given: cliente existente y datos de compra
        main.getAplicacionConsola().crearCliente("CLI001", "Juan", "juan@email.com");
        String[] opciones = {"2", "COMP001", "CLI001", "150.0"};
        
        // When: procesar opción registrar compra
        boolean resultado = main.procesarOpcionRegistrarCompra(opciones);
        
        // Then: verificar que se registró
        assertTrue(resultado);
        Cliente cliente = main.getAplicacionConsola().buscarCliente("CLI001");
        assertEquals(1, cliente.getPuntos()); // 1 punto por $150
    }

    @Test
    void testProcesarOpcionRegistrarCompraClienteNoExiste() {
        // Given: datos de compra con cliente que no existe
        String[] opciones = {"2", "COMP001", "CLI999", "150.0"};
        
        // When: procesar opción registrar compra
        boolean resultado = main.procesarOpcionRegistrarCompra(opciones);
        
        // Then: verificar que no se registró
        assertFalse(resultado);
    }

    @Test
    void testProcesarOpcionMostrarPuntos() {
        // Given: cliente con puntos
        main.getAplicacionConsola().crearCliente("CLI001", "Juan", "juan@email.com");
        main.getAplicacionConsola().registrarCompra("COMP001", "CLI001", 150.0);
        String[] opciones = {"3", "CLI001"};
        
        // When: procesar opción mostrar puntos
        String resultado = main.procesarOpcionMostrarPuntos(opciones);
        
        // Then: verificar que contiene la información
        assertNotNull(resultado);
        assertTrue(resultado.contains("Juan"));
        assertTrue(resultado.contains("1")); // 1 punto
    }

    @Test
    void testProcesarOpcionMostrarPuntosClienteNoExiste() {
        // Given: cliente que no existe
        String[] opciones = {"3", "CLI999"};
        
        // When: procesar opción mostrar puntos
        String resultado = main.procesarOpcionMostrarPuntos(opciones);
        
        // Then: verificar que indica que no existe
        assertNotNull(resultado);
        assertTrue(resultado.contains("no encontrado"));
    }
} 