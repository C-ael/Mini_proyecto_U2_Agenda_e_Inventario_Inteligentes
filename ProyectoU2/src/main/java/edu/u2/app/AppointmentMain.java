package edu.u2.app;

import edu.u2.model.Appointment;
import edu.u2.search.BinarySearch;
import edu.u2.search.Bounds;
import edu.u2.sorting.BubbleSort;
import edu.u2.sorting.InsertionSort;
import edu.u2.sorting.SelectionSort;
import edu.u2.util.CSVLoader;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

import static edu.u2.app.Main.DATE_TIME_FORMAT;
import static edu.u2.app.Main.runSortingExperiment;

public class AppointmentMain {

    // ejecuta el flujo principal de la agenda de citas
    public static void runAppointments(Scanner scanner) {

        // selección del dataset
        System.out.println("\nSeleccione dataset:");
        System.out.println("1. Normal");
        System.out.println("2. Casi ordenado");
        System.out.print("Opción: ");

        int dataset = scanner.nextInt();
        scanner.nextLine();

        // define el archivo según la opción elegida
        String file = (dataset == 2)
                ? "citas_100_casi_ordenadas.csv"
                : "citas_100.csv";

        // carga las citas desde CSV
        Appointment[] data = CSVLoader.loadAppointments(file);

        // valida que existan datos
        if (data.length == 0) {
            System.out.println("No hay citas cargadas.");
            return;
        }

        // experimento de ordenamiento por fecha y hora
        System.out.println("\n--- Experimento de Ordenamiento (Fecha/Hora) ---");

        // Insertion Sort
        System.out.println("Ejecutando Insertion Sort...");
        runSortingExperiment("Citas - Insertion Sort", Arrays.copyOf(data, data.length),
                InsertionSort::sort
        );

        // Bubble Sort
        System.out.println("Ejecutando Bubble Sort...");
        runSortingExperiment("Citas - Bubble Sort", Arrays.copyOf(data, data.length),
                BubbleSort::sort
        );

        // Selection Sort
        System.out.println("Ejecutando Selection Sort...");
        runSortingExperiment("Citas - Selection Sort", Arrays.copyOf(data, data.length),
                SelectionSort::sort
        );

        // ordena el arreglo principal para permitir búsqueda binaria
        System.out.println("Ordenando para Búsqueda Binaria...");
        Arrays.sort(data);

        int searchOption = 0;
        
        do {
            // menú de búsqueda
            System.out.println("\nSeleccione tipo de búsqueda:");
            System.out.println("1. Búsqueda Exacta por Fecha/Hora");
            System.out.println("2. Búsqueda por Rango de Fechas/Horas");
            System.out.println("0. Menu Inicio");
            System.out.print("Opción: ");

            // valida entrada numérica
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Volviendo al menú principal.");
                scanner.nextLine();
                return;
            }

            searchOption = scanner.nextInt();
            scanner.nextLine();

            switch (searchOption) {
                case 1:
                    runExactAppointmentSearch(scanner, data);
                    break;
                case 2:
                    runRangeAppointmentSearch(scanner, data);
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal");
                    break;
                default:
                    System.out.println("Opción de búsqueda inválida.");
            }

        } while (searchOption != 0);
    }

    // búsqueda binaria exacta por fecha y hora
    private static void runExactAppointmentSearch(Scanner scanner, Appointment[] data) {

        System.out.print("\nIngrese fechaHora exacta (yyyy-MM-dd'T'HH:mm): ");
        String exact = scanner.nextLine();

        try {
            // clave ficticia usada solo para comparar por fechaHora
            Appointment key = new Appointment("", "", "",
                    LocalDateTime.parse(exact, DATE_TIME_FORMAT)
            );

            int index = BinarySearch.search(data, key);

            System.out.println(
                    index >= 0
                            ? "Cita encontrada: " + data[index]
                            : "Cita no encontrada"
            );

        } catch (Exception e) {
            System.out.println("Formato incorrecto. Use yyyy-MM-dd'T'HH:mm.");
        }
    }

    // búsqueda de citas dentro de un rango de fechas
    private static void runRangeAppointmentSearch(Scanner scanner, Appointment[] data) {

        System.out.print("\nFecha inicio (yyyy-MM-dd'T'HH:mm): ");
        String startStr = scanner.nextLine();

        System.out.print("Fecha fin (yyyy-MM-dd'T'HH:mm): ");
        String endStr = scanner.nextLine();

        try {
            // claves para calcular límites del rango
            Appointment start = new Appointment("", "", "",
                    LocalDateTime.parse(startStr, DATE_TIME_FORMAT)
            );

            Appointment end = new Appointment("", "", "",
                    LocalDateTime.parse(endStr, DATE_TIME_FORMAT)
            );

            // calcula el rango usando búsqueda binaria
            int from = Bounds.lowerBound(data, start);
            int to = Bounds.upperBound(data, end);

            System.out.println("\nCitas en el rango:");
            if (from < to) {
                for (int i = from; i < to; i++) {
                    System.out.println(data[i]);
                }
            } else {
                System.out.println("No se encontraron citas en ese rango.");
            }

        } catch (Exception e) {
            System.out.println("Formato incorrecto. Use yyyy-MM-dd'T'HH:mm.");
        }
    }
}
