package edu.u2.app;

import edu.u2.model.*;
import edu.u2.sorting.*;
import edu.u2.search.*;
import edu.u2.util.TimeUtils;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import static edu.u2.app.Appointmentmain.runAppointments;
import static edu.u2.app.InventaryItemMain.runInventory;
import static edu.u2.app.PatientMain.runPatients;

public class Main {

    private static final int RUNS = 10;
    private static final int DISCARD = 3;

    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runMainMenu(scanner);
        scanner.close();
        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }


    public static void runMainMenu(Scanner scanner) {
        int option = 0;

        // Bucle do-while para mantener el menú hasta que se seleccione la opción 0
        do {
            System.out.println("\n=== MINI PROYECTO U2 ===");
            System.out.println("1. Agenda de citas");
            System.out.println("2. Inventario");
            System.out.println("3. Pacientes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            // Manejo de entrada no válida para evitar el crash del programa
            if (!scanner.hasNextInt()) {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Consumir la entrada inválida
                continue;
            }

            option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    runAppointments(scanner);
                    break;
                case 2:
                    runInventory(scanner);
                    break;
                case 3:
                    runPatients(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 0);
    }

    /* ===============================
   EXPERIMENTO DE ORDENAMIENTO
   =============================== */
    public static <T extends Comparable<T>> void runSortingExperiment(
            String title,
            T[] original,
            SortingAlgorithm<T> algorithm
    ) {
        long[] times = new long[RUNS];
        long comparisons = 0;
        long swaps = 0;

        for (int i = 0; i < RUNS; i++) {

            T[] copy = Arrays.copyOf(original, original.length);
            SortMetrics metrics = new SortMetrics();

            long time = TimeUtils.measureTime(() ->
                    algorithm.sort(copy, metrics)
            );

            times[i] = time;
            comparisons = metrics.getComparisons();
            swaps = metrics.getSwaps();
        }

        long median = medianTime(times);

        System.out.printf(
                "%s | tiempo(ns): %d | comparaciones: %d | swaps: %d%n",
                title, median, comparisons, swaps
        );
    }

    private static long medianTime(long[] times) {
        Arrays.sort(times);
        int start = DISCARD;
        int size = times.length - DISCARD;
        return times[start + size / 2];
    }


    @FunctionalInterface
    public interface SortingAlgorithm<T extends Comparable<T>> {
        void sort(T[] array, SortMetrics metrics);
    }
}