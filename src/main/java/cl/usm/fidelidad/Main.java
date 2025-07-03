package cl.usm.fidelidad;

import java.util.Scanner;

/**
 * Clase principal de la aplicación de consola
 * Sistema de Tarjeta de Fidelidad Gamificada
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            
            switch (opcion) {
                case 1:
                    probarCrearCliente(scanner);
                    break;
                case 2:
                    probarValidarCorreo(scanner);
                    break;
                case 3:
                    probarAgregarPuntos(scanner);
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE FIDELIDAD GAMIFICADA ===");
        System.out.println("1. Crear cliente");
        System.out.println("2. Validar correo");
        System.out.println("3. Agregar puntos");
        System.out.println("4. Salir");
        System.out.println("=====================================");
    }
    
    private static void probarCrearCliente(Scanner scanner) {
        System.out.println("\n--- Crear Cliente ---");
        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese correo: ");
        String correo = scanner.nextLine();
        
        Cliente cliente = new Cliente(id, nombre, correo);
        System.out.println("Cliente creado exitosamente:");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Correo: " + cliente.getCorreo());
        System.out.println("Puntos: " + cliente.getPuntos());
        System.out.println("Nivel: " + cliente.getNivel());
        System.out.println("Streak: " + cliente.getStreakDias());
    }
    
    private static void probarValidarCorreo(Scanner scanner) {
        System.out.println("\n--- Validar Correo ---");
        System.out.print("Ingrese correo a validar: ");
        String correo = scanner.nextLine();
        
        Cliente cliente = new Cliente("test", "Test", correo);
        try {
            cliente.validarCorreo();
            System.out.println("✅ Correo válido: " + correo);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Correo inválido: " + e.getMessage());
        }
    }
    
    private static void probarAgregarPuntos(Scanner scanner) {
        System.out.println("\n--- Agregar Puntos ---");
        System.out.print("Ingrese puntos a agregar: ");
        int puntos = scanner.nextInt();
        
        Cliente cliente = new Cliente("test", "Test", "test@email.com");
        System.out.println("Nivel inicial: " + cliente.getNivel());
        System.out.println("Puntos iniciales: " + cliente.getPuntos());
        
        cliente.agregarPuntos(puntos);
        
        System.out.println("Puntos después de agregar: " + cliente.getPuntos());
        System.out.println("Nivel después de agregar: " + cliente.getNivel());
    }
} 