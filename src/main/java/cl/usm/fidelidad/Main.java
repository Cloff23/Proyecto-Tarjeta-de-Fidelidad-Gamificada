package cl.usm.fidelidad;

import java.util.Scanner;

/**
 * Clase principal de la aplicación de consola
 * Sistema de Tarjeta de Fidelidad Gamificada
 */
public class Main {
    
    private AplicacionConsola aplicacionConsola;
    
    public Main() {
        this.aplicacionConsola = new AplicacionConsola();
    }
    
    public AplicacionConsola getAplicacionConsola() {
        return aplicacionConsola;
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            
            switch (opcion) {
                case 1:
                    main.procesarCrearCliente(scanner);
                    break;
                case 2:
                    main.procesarRegistrarCompra(scanner);
                    break;
                case 3:
                    main.procesarMostrarPuntos(scanner);
                    break;
                case 4:
                    main.procesarListarClientes();
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE FIDELIDAD GAMIFICADA ===");
        System.out.println("1. Crear cliente");
        System.out.println("2. Registrar compra");
        System.out.println("3. Mostrar puntos/nivel de cliente");
        System.out.println("4. Listar clientes");
        System.out.println("5. Salir");
        System.out.println("=====================================");
    }
    
    public boolean procesarOpcionCrearCliente(String[] opciones) {
        if (opciones.length < 4) return false;
        String id = opciones[1];
        String nombre = opciones[2];
        String correo = opciones[3];
        return aplicacionConsola.crearCliente(id, nombre, correo);
    }
    
    public boolean procesarOpcionRegistrarCompra(String[] opciones) {
        if (opciones.length < 4) return false;
        String idCompra = opciones[1];
        String idCliente = opciones[2];
        double monto = Double.parseDouble(opciones[3]);
        return aplicacionConsola.registrarCompra(idCompra, idCliente, monto);
    }
    
    public String procesarOpcionMostrarPuntos(String[] opciones) {
        if (opciones.length < 2) return "Error: ID de cliente requerido";
        String idCliente = opciones[1];
        return aplicacionConsola.mostrarPuntosCliente(idCliente);
    }
    
    private void procesarCrearCliente(Scanner scanner) {
        System.out.println("\n--- Crear Cliente ---");
        System.out.print("Ingrese ID del cliente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese correo: ");
        String correo = scanner.nextLine();
        
        boolean creado = aplicacionConsola.crearCliente(id, nombre, correo);
        if (creado) {
            System.out.println("✅ Cliente creado exitosamente");
        } else {
            System.out.println("❌ Error: Correo inválido");
        }
    }
    
    private void procesarRegistrarCompra(Scanner scanner) {
        System.out.println("\n--- Registrar Compra ---");
        System.out.print("Ingrese ID de la compra: ");
        String idCompra = scanner.nextLine();
        System.out.print("Ingrese ID del cliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("Ingrese monto: ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir salto de línea
        
        boolean registrada = aplicacionConsola.registrarCompra(idCompra, idCliente, monto);
        if (registrada) {
            System.out.println("✅ Compra registrada exitosamente");
            Cliente cliente = aplicacionConsola.buscarCliente(idCliente);
            System.out.println("Puntos otorgados: " + cliente.getPuntos());
        } else {
            System.out.println("❌ Error: Cliente no encontrado");
        }
    }
    
    private void procesarMostrarPuntos(Scanner scanner) {
        System.out.println("\n--- Mostrar Puntos/Nivel ---");
        System.out.print("Ingrese ID del cliente: ");
        String idCliente = scanner.nextLine();
        
        String info = aplicacionConsola.mostrarPuntosCliente(idCliente);
        System.out.println(info);
    }
    
    private void procesarListarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        String lista = aplicacionConsola.listarClientes();
        System.out.println(lista);
    }
} 