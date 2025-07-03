package cl.usm.fidelidad;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Pruebas unitarias para la gestión CRUD de compras
 * Siguiendo TDD: escribimos las pruebas primero
 */
class GestionComprasTest {

    private GestionCompras gestionCompras;
    private Cliente cliente;
    private LocalDate fecha;

    @BeforeEach
    void setUp() {
        gestionCompras = new GestionCompras();
        cliente = new Cliente("1", "Juan Pérez", "juan@email.com");
        fecha = LocalDate.now();
    }

    @Test
    void testCrearCompra() {
        // Given: datos de compra
        String idCompra = "COMP001";
        double monto = 150.0;
        
        // When: crear compra
        Compra compra = gestionCompras.crearCompra(idCompra, cliente.getId(), monto, fecha);
        
        // Then: verificar que se creó correctamente
        assertNotNull(compra);
        assertEquals(idCompra, compra.getIdCompra());
        assertEquals(cliente.getId(), compra.getIdCliente());
        assertEquals(monto, compra.getMonto(), 0.01);
        assertEquals(fecha, compra.getFecha());
    }

    @Test
    void testListarCompras() {
        // Given: crear algunas compras
        gestionCompras.crearCompra("COMP001", cliente.getId(), 100.0, fecha);
        gestionCompras.crearCompra("COMP002", cliente.getId(), 200.0, fecha);
        
        // When: listar compras
        List<Compra> compras = gestionCompras.listarCompras();
        
        // Then: verificar que se listan correctamente
        assertEquals(2, compras.size());
        assertEquals("COMP001", compras.get(0).getIdCompra());
        assertEquals("COMP002", compras.get(1).getIdCompra());
    }

    @Test
    void testBuscarCompraPorId() {
        // Given: crear una compra
        gestionCompras.crearCompra("COMP001", cliente.getId(), 100.0, fecha);
        
        // When: buscar compra por ID
        Compra compra = gestionCompras.buscarCompraPorId("COMP001");
        
        // Then: verificar que se encuentra
        assertNotNull(compra);
        assertEquals("COMP001", compra.getIdCompra());
        assertEquals(cliente.getId(), compra.getIdCliente());
    }

    @Test
    void testBuscarCompraPorIdNoExiste() {
        // When: buscar compra que no existe
        Compra compra = gestionCompras.buscarCompraPorId("COMP999");
        
        // Then: verificar que retorna null
        assertNull(compra);
    }

    @Test
    void testActualizarCompra() {
        // Given: crear una compra
        gestionCompras.crearCompra("COMP001", cliente.getId(), 100.0, fecha);
        
        // When: actualizar la compra
        boolean actualizado = gestionCompras.actualizarCompra("COMP001", 250.0);
        
        // Then: verificar que se actualizó
        assertTrue(actualizado);
        Compra compra = gestionCompras.buscarCompraPorId("COMP001");
        assertEquals(250.0, compra.getMonto(), 0.01);
    }

    @Test
    void testActualizarCompraNoExiste() {
        // When: actualizar compra que no existe
        boolean actualizado = gestionCompras.actualizarCompra("COMP999", 250.0);
        
        // Then: verificar que no se actualizó
        assertFalse(actualizado);
    }

    @Test
    void testEliminarCompra() {
        // Given: crear una compra
        gestionCompras.crearCompra("COMP001", cliente.getId(), 100.0, fecha);
        
        // When: eliminar la compra
        boolean eliminado = gestionCompras.eliminarCompra("COMP001");
        
        // Then: verificar que se eliminó
        assertTrue(eliminado);
        Compra compra = gestionCompras.buscarCompraPorId("COMP001");
        assertNull(compra);
    }

    @Test
    void testEliminarCompraNoExiste() {
        // When: eliminar compra que no existe
        boolean eliminado = gestionCompras.eliminarCompra("COMP999");
        
        // Then: verificar que no se eliminó
        assertFalse(eliminado);
    }
} 