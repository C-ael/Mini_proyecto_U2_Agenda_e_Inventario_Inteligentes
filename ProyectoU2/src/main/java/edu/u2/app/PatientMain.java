package edu.u2.app;

import edu.u2.list.PatientLinkedList;
import edu.u2.model.Patient;
import edu.u2.util.CSVLoader;

import java.util.List;
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

        System.out.println("\nPacientes cargados correctamente (" + list.size() + " registros).");

        int searchOption = -1;

        do {
            // mostrar el menú
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1. Buscar por apellido");
            System.out.println("2. Filtrar por prioridad");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingrese un número entero: ");

            // valida entrada numérica
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                scanner.nextLine();
                continue; // no sale del módulo
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
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción fuera de rango. Intente nuevamente.");
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

            boolean invalidChar = false;
            for (char c : lastName.toCharArray()) {
                if (!Character.isLetter(c) && c != ' ' && c != '-') {
                    invalidChar = true;
                    break;
                }
            }

            if (invalidChar) {
                System.out.println("El apellido solo puede contener letras, espacios o guiones.");
                continue;
            }

            break;
        }

        System.out.println("\nBuscando pacientes con apellido: \"" + lastName + "\"");

        Patient first = list.searchFirstByLastName(lastName);
        Patient last = list.searchLastByLastName(lastName);

        if (first == null && last == null) {
            System.out.println("No se encontraron pacientes con ese apellido.");
            return;
        }

        if (first != null) {
            System.out.println("Primer paciente encontrado:");
            System.out.println(first);
        }

        if (last != null && last != first) {
            System.out.println("Último paciente encontrado:");
            System.out.println(last);
        }
    }

    // filtra pacientes según la prioridad
    private static void runPatientPriorityFilter(Scanner scanner, PatientLinkedList list) {

        System.out.println("\nLa prioridad indica el nivel de urgencia del paciente.");
        System.out.println("Ejemplo: 1 = alta, 2 = media, 3 = baja.");

        while (true) {
            System.out.print("\nIngrese la prioridad a filtrar (o 0 para volver): ");

            // validar número entero
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                scanner.nextLine();
                continue;
            }

            int priority = scanner.nextInt();
            scanner.nextLine();

            // opción explícita de salida
            if (priority == 0) {
                System.out.println("Volviendo al menú de pacientes...");
                return;
            }

            System.out.println("\nBuscando pacientes con prioridad " + priority + "...");

            java.util.List<Patient> results = list.findAllByPriority(priority);

            if (results.isEmpty()) {
                System.out.println("No se encontraron pacientes con esa prioridad. Intente con 1, 2 o 3");
            } else {
                results.forEach(System.out::println);
            }
        }
    }
}
