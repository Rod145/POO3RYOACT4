//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        TarjetaDAOImpl tarjetaDAO = new TarjetaDAOImpl();

        Usuario usuarioLogueado = null;
        int opcion = 0;

        System.out.println("=== BIENVENIDO AL SISTEMA DE TARJETAS ===");

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            if (usuarioLogueado == null) {
                System.out.println("1. Registrar nuevo usuario");
                System.out.println("2. Iniciar sesión");
            } else {
                System.out.println("Sesión activa: " + usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido());
                System.out.println("3. Registrar nueva tarjeta");
                System.out.println("4. Cerrar sesión");
            }
            System.out.println("5. Salir del programa");
            System.out.print("Elige una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } else {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    if (usuarioLogueado != null) {
                        System.out.println("Ya tienes una sesión iniciada. Cierra sesión primero.");
                        break;
                    }

                    System.out.println("\n--- REGISTRO DE USUARIO ---");
                    Usuario nuevoUsuario = new Usuario();

                    System.out.print("Nombre: ");
                    nuevoUsuario.setNombre(scanner.nextLine());
                    System.out.print("Apellido: ");
                    nuevoUsuario.setApellido(scanner.nextLine());
                    System.out.print("Edad: ");
                    nuevoUsuario.setEdad(scanner.nextInt());
                    scanner.nextLine(); // Limpiar
                    System.out.print("Correo electrónico: ");
                    nuevoUsuario.setEmail(scanner.nextLine());
                    System.out.print("Contraseña: ");
                    nuevoUsuario.setPassword(scanner.nextLine());
                    nuevoUsuario.setActivo(true);

                    if (usuarioDAO.crear(nuevoUsuario)) {
                        System.out.println("¡Usuario registrado con éxito en la base de datos!");
                    } else {
                        System.out.println("Hubo un error al registrar el usuario.");
                    }
                    break;

                case 2:
                    if (usuarioLogueado != null) {
                        System.out.println("Ya estás logueado.");
                        break;
                    }

                    System.out.println("\n--- INICIAR SESIÓN ---");
                    System.out.print("Correo: ");
                    String email = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();

                    Usuario u = usuarioDAO.iniciarSesion(email, password);
                    if (u != null) {
                        usuarioLogueado = u;
                        System.out.println("¡Inicio de sesión exitoso! Bienvenido, " + u.getNombre() + ".");
                    } else {
                        System.out.println("Credenciales incorrectas o usuario no encontrado.");
                    }
                    break;

                case 3:
                    if (usuarioLogueado == null) {
                        System.out.println("Debes iniciar sesión primero (Opción 2).");
                        break;
                    }

                    System.out.println("\n--- REGISTRAR NUEVA TARJETA ---");
                    Tarjeta nuevaTarjeta = new Tarjeta();

                    nuevaTarjeta.setUsuarioId(usuarioLogueado.getId());

                    System.out.print("Clave (Ej. 1234): ");
                    nuevaTarjeta.setClave(scanner.nextLine());
                    System.out.print("Número de tarjeta (16 dígitos): ");
                    nuevaTarjeta.setNumero(scanner.nextLine());
                    System.out.print("Fecha de expiración (YYYY-MM-DD): ");
                    nuevaTarjeta.setFechaExp(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Tipo (Débito/Crédito): ");
                    String tipo = scanner.nextLine();
                    nuevaTarjeta.setTipo(tipo);

                    if (tipo.equalsIgnoreCase("Crédito")) {
                        System.out.print("Límite de crédito asignado: ");
                        nuevaTarjeta.setLimiteCredito(scanner.nextDouble());
                    } else {
                        nuevaTarjeta.setLimiteCredito(0.0);
                    }

                    nuevaTarjeta.setSaldo(0.0); // Inicia con saldo cero
                    nuevaTarjeta.setActivo(true);

                    if (tarjetaDAO.crear(nuevaTarjeta)) {
                        System.out.println("¡Tarjeta registrada y vinculada a tu cuenta con éxito!");
                    } else {
                        System.out.println("Hubo un error al registrar la tarjeta.");
                    }
                    break;

                case 4:
                    if (usuarioLogueado != null) {
                        usuarioLogueado = null;
                        System.out.println("Has cerrado sesión.");
                    } else {
                        System.out.println("No hay ninguna sesión activa.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del sistema... ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}