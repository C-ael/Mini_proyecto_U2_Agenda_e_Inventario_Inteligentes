package edu.u2.app;

import edu.u2.list.PatientLinkedList;
import edu.u2.model.Patient;
import edu.u2.util.CSVLoader;

import java.util.Scanner;

public class PatientMain {
    public static void runPatients(Scanner scanner) {

        PatientLinkedList list =
                CSVLoader.loadPatients("pacientes_500.csv");

        if (list.size() == 0) {
            System.out.println("No hay pacientes cargados.");
            return;
        }

        int searchOption = 0;
        do {
            // --- MENÚ DE BÚSQUEDA/FILTRO ---
            System.out.println("\n--- PACIENTES: BÚSQUEDA/FILTRO ---");
            System.out.println("1. Búsqueda por Apellido (Primero/Último)");
            System.out.println("2. Filtrar por Prioridad");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Opción: ");

            // 1. Validar la entrada de la opción del menú
            if (!scanner.hasNextInt()) {
                System.out.println("Opción inválida (debe ser un número). Volviendo al menú principal.");
                scanner.nextLine();
                return;
            }

            searchOption = scanner.nextInt();
            scanner.nextLine();

            switch (searchOption) {
                case 1:
                    runPatientLastNameSearch(scanner, list);
                    break;
                case 2:
                    runPatientPriorityFilter(scanner, list);
                    break;
                case 0:
                    System.out.println("Regresando al menu principal");
                    break;
                default:
                    System.out.println("Opción de búsqueda inválida.");
            }
        } while (searchOption != 0);
    }

    private static void runPatientLastNameSearch(Scanner scanner, PatientLinkedList list) {
        String apellido;

        // Bucle de validación de entrada
        while (true) {
            System.out.print("\nApellido a buscar: ");
            apellido = scanner.nextLine().trim();

            if (apellido.isEmpty()) {
                System.out.println("Error: El apellido no puede estar vacío. Intente de nuevo.");
                continue;
            }

            // --- Lógica de Validación Estricta: busca caracteres NO permitidos ---
            boolean containsInvalidChar = false;
            for (char c : apellido.toCharArray()) {
                // Caracteres permitidos: Letras, espacio (' '), y guion ('-')
                if (!Character.isLetter(c) && c != ' ' && c != '-') {
                    containsInvalidChar = true;
                    break;
                }
            }

            if (containsInvalidChar) {
                System.out.println("No es correcto. El apellido solo debe contener letras, espacios o guiones. Intente de nuevo.");
                continue; // Repite el bucle
            }

            // Si la entrada es válida, salimos
            break;
        }

        Patient first = list.searchFirstByLastName(apellido);
        Patient last = list.searchLastByLastName(apellido);

        System.out.println("Primero: " + (first != null ? first : "No encontrado"));
        System.out.println("Último: " + (last != null ? last : "No encontrado"));
    }


    private static void runPatientPriorityFilter(Scanner scanner, PatientLinkedList list) {
        // Bucle interno para forzar la entrada válida de prioridad
        while (true) {
            System.out.print("\nPrioridad a filtrar: ");

            // 2. Validar que la entrada sea un entero (InputMismatchException)
            if (!scanner.hasNextInt()) {
                System.out.println("Error: La prioridad debe ser un número entero. Intente de nuevo.");
                scanner.nextLine(); // Consumir la entrada no válida (ej: "jjj")
                continue; // Volver al inicio del while(true)
            }

            int prioridad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            // Si llegamos aquí, la entrada es válida y rompemos el bucle while
            System.out.println("Pacientes con prioridad " + prioridad + ":");
            java.util.List<Patient> results = list.findAllByPriority(prioridad);

            if (results.isEmpty()) {
                System.out.println("No se encontraron pacientes con esa prioridad.");
            } else {
                results.forEach(System.out::println);
            }
            break;
        }
    }
}
