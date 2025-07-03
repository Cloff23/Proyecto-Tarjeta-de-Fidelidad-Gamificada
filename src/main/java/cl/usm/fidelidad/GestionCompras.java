package cl.usm.fidelidad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona las operaciones CRUD de compras
 */
public class GestionCompras {
    
    private Map<String, Compra> compras;
    
    public GestionCompras() {
        this.compras = new HashMap<>();
    }
    
    /**
     * Crea una nueva compra
     */
    public Compra crearCompra(String idCompra, String idCliente, double monto, LocalDate fecha) {
        Compra compra = new Compra(idCompra, idCliente, monto, fecha);
        compras.put(idCompra, compra);
        return compra;
    }
    
    /**
     * Lista todas las compras
     */
    public List<Compra> listarCompras() {
        return new ArrayList<>(compras.values());
    }
    
    /**
     * Busca una compra por su ID
     */
    public Compra buscarCompraPorId(String idCompra) {
        return compras.get(idCompra);
    }
    
    /**
     * Actualiza el monto de una compra
     */
    public boolean actualizarCompra(String idCompra, double nuevoMonto) {
        Compra compra = compras.get(idCompra);
        if (compra != null) {
            compra.setMonto(nuevoMonto);
            return true;
        }
        return false;
    }
    
    /**
     * Elimina una compra por su ID
     */
    public boolean eliminarCompra(String idCompra) {
        Compra compra = compras.remove(idCompra);
        return compra != null;
    }
} 