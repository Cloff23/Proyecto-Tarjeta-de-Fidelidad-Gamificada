package cl.usm.fidelidad;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * Pruebas unitarias para la aplicación de consola
 * Siguiendo TDD: escribimos las pruebas primero
 */
class AplicacionConsolaTest {

    private AplicacionConsola app;

    @BeforeEach
    void setUp() {
        app = new AplicacionConsola();
    }

    @Test
    void testCrearCliente() {
        // Given: datos de cliente
        String id = "CLI001";
        String nombre = "Juan Pérez";
        String correo = "juan@email.com";
        
        // When: crear cliente
        boolean creado = app.crearCliente(id, nombre, correo);
        
        // Then: verificar que se creó
        assertTrue(creado);
        Cliente cliente = app.buscarCliente(id);
        assertNotNull(cliente);
        assertEquals(nombre, cliente.getNombre());
        assertEquals(correo, cliente.getCorreo());
    }

    @Test
    void testCrearClienteConCorreoInvalido() {
        // Given: datos de cliente con correo inválido
        String id = "CLI002";
        String nombre = "María";
        String correo = "correo-invalido";
        
        // When: crear cliente
        boolean creado = app.crearCliente(id, nombre, correo);
        
        // Then: verificar que no se creó
        assertFalse(creado);
    }

    @Test
    void testBuscarClienteNoExiste() {
        // When: buscar cliente que no existe
        Cliente cliente = app.buscarCliente("CLI999");
        
        // Then: verificar que retorna null
        assertNull(cliente);
    }

    @Test
    void testRegistrarCompra() {
        // Given: cliente existente
        app.crearCliente("CLI001", "Juan", "juan@email.com");
        
        // When: registrar compra
        boolean registrada = app.registrarCompra("COMP001", "CLI001", 150.0);
        
        // Then: verificar que se registró
        assertTrue(registrada);
    }

    @Test
    void testRegistrarCompraClienteNoExiste() {
        // When: registrar compra con cliente que no existe
        boolean registrada = app.registrarCompra("COMP001", "CLI999", 150.0);
        
        // Then: verificar que no se registró
        assertFalse(registrada);
    }

    @Test
    void testMostrarPuntosCliente() {
        // Given: cliente con compras
        app.crearCliente("CLI001", "Juan", "juan@email.com");
        app.registrarCompra("COMP001", "CLI001", 150.0);
        
        // When: obtener información del cliente
        String info = app.mostrarPuntosCliente("CLI001");
        
        // Then: verificar que contiene la información
        assertNotNull(info);
        assertTrue(info.contains("Juan"));
        assertTrue(info.contains("1")); // 1 punto por $150
    }

    @Test
    void testListarClientes() {
        // Given: varios clientes
        app.crearCliente("CLI001", "Juan", "juan@email.com");
        app.crearCliente("CLI002", "María", "maria@email.com");
        
        // When: listar clientes
        String lista = app.listarClientes();
        
        // Then: verificar que contiene ambos clientes
        assertNotNull(lista);
        assertTrue(lista.contains("Juan"));
        assertTrue(lista.contains("María"));
    }
} 