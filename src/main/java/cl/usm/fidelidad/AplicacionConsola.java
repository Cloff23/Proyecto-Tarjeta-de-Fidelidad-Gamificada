package cl.usm.fidelidad;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que maneja la lógica de la aplicación de consola
 */
public class AplicacionConsola {
    
    private Map<String, Cliente> clientes;
    private GestionCompras gestionCompras;
    
    public AplicacionConsola() {
        this.clientes = new HashMap<>();
        this.gestionCompras = new GestionCompras();
    }
    
    /**
     * Crea un nuevo cliente
     */
    public boolean crearCliente(String id, String nombre, String correo) {
        try {
            Cliente cliente = new Cliente(id, nombre, correo);
            cliente.validarCorreo(); // Validar correo
            clientes.put(id, cliente);
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Correo inválido
        }
    }
    
    /**
     * Busca un cliente por su ID
     */
    public Cliente buscarCliente(String id) {
        return clientes.get(id);
    }
    
    /**
     * Registra una compra para un cliente
     */
    public boolean registrarCompra(String idCompra, String idCliente, double monto) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente == null) {
            return false; // Cliente no existe
        }
        
        Compra compra = gestionCompras.crearCompra(idCompra, idCliente, monto, LocalDate.now());
        int puntos = compra.calcularPuntos(cliente);
        cliente.agregarPuntos(puntos);
        
        return true;
    }
    
    /**
     * Muestra los puntos y nivel de un cliente
     */
    public String mostrarPuntosCliente(String idCliente) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente == null) {
            return "Cliente no encontrado";
        }
        
        return String.format("Cliente: %s\nPuntos: %d\nNivel: %s", 
                           cliente.getNombre(), 
                           cliente.getPuntos(), 
                           cliente.getNivel());
    }
    
    /**
     * Lista todos los clientes
     */
    public String listarClientes() {
        if (clientes.isEmpty()) {
            return "No hay clientes registrados";
        }
        
        StringBuilder sb = new StringBuilder("Lista de Clientes:\n");
        for (Cliente cliente : clientes.values()) {
            sb.append(String.format("- %s (%s): %d puntos, Nivel %s\n", 
                                  cliente.getNombre(), 
                                  cliente.getId(), 
                                  cliente.getPuntos(), 
                                  cliente.getNivel()));
        }
        return sb.toString();
    }
} 