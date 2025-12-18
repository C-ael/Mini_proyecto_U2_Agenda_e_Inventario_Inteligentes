package edu.u2.app;

import edu.u2.list.PatientLinkedList;
import edu.u2.model.Patient;
import edu.u2.util.CSVLoader;

import java.util.Scanner;

public class PatientMain {

    // ejecuta el módulo de pacientes
    public static void runPatients(Scanner scanner) {

        PatientLinkedList list = CSVLoader.loadPatients("pacientes_500.csv");

        // valida que existan pacientes cargados
        if (list.size() == 0) {
            System.out.println("No hay pacientes cargados.");
            return;
        }

        int searchOption;

        do {
            // mostrar el menú
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1. Buscar por apellido");
            System.out.println("2. Filtrar por prioridad");
            System.out.println("0. Volver al menu principal");
            System.out.print("Opción: ");

            // valida entrada numérica
            if (!scanner.hasNextInt()) {
                System.out.println("Opción inválida.");
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
                    System.out.println("Opción inválida.");
            }

        } while (searchOption != 0);
    }

    // busca el primer y último paciente por apellido
    private static void runPatientLastNameSearch(Scanner scanner, PatientLinkedList list) {

        String lastName;

        // valida el apellido ingresado
        while (true) {
            System.out.print("\nApellido a buscar: ");
            lastName = scanner.nextLine().trim();

            if (lastName.isEmpty()) {
                System.out.println("El apellido no puede estar vacío.");
                continue;
            }

            // verifica caracteres permitidos
            boolean invalidChar = false;
            for (char c : lastName.toCharArray()) {
                if (!Character.isLetter(c) && c != ' ' && c != '-') {
                    invalidChar = true;
                    break;
                }
            }

            if (invalidChar) {
                System.out.println("El apellido contiene caracteres inválidos.");
                continue;
            }

            break;
        }

        Patient first = list.searchFirstByLastName(lastName);
        Patient last = list.searchLastByLastName(lastName);

        System.out.println("Primero: " + (first != null ? first : "No encontrado"));
        System.out.println("Último: " + (last != null ? last : "No encontrado"));
    }

    // filtra pacientes según la prioridad
    private static void runPatientPriorityFilter(Scanner scanner, PatientLinkedList list) {

        while (true) {
            System.out.print("\nPrioridad a filtrar: ");

            // valida que la prioridad sea numérica
            if (!scanner.hasNextInt()) {
                System.out.println("La prioridad debe ser un número.");
                scanner.nextLine();
                continue;
            }

            int priority = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Pacientes con prioridad " + priority + ":");
            java.util.List<Patient> results = list.findAllByPriority(priority);

            if (results.isEmpty()) {
                System.out.println("No se encontraron pacientes.");
            } else {
                results.forEach(System.out::println);
            }

            break;
        }
    }
}
