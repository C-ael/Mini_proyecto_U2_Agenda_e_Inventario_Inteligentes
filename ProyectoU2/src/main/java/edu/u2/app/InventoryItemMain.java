package edu.u2.app;

import edu.u2.model.InventoryItem;
import edu.u2.search.BinarySearch;
import edu.u2.search.Bounds;
import edu.u2.sorting.BubbleSort;
import edu.u2.sorting.InsertionSort;
import edu.u2.util.CSVLoader;

import java.util.Arrays;
import java.util.Scanner;

import static edu.u2.app.Main.runSortingExperiment;

public class InventoryItemMain {

    // ejecuta el flujo principal del inventario
    public static void runInventory(Scanner scanner) {

        // carga el inventario desde CSV (dataset inverso)
        InventoryItem[] data = CSVLoader.loadInventory("inventario_500_inverso.csv");

        // valida que existan datos
        if (data.length == 0) {
            System.out.println("No hay inventario cargado.");
            return;
        }

        System.out.println("\nInventario cargado correctamente (" + data.length + " items).");

        // experimento de ordenamiento con dataset inverso
        System.out.println("\n--- Experimento de Ordenamiento (Inventario Inverso) ---");
        System.out.println("El dataset inverso penaliza Insertion Sort y Bubble Sort.\n");

        // Insertion Sort
        System.out.println("Ejecutando Insertion Sort...");
        runSortingExperiment(
                "Inventario - Insertion Sort",
                Arrays.copyOf(data, data.length),
                InsertionSort::sort
        );

        // Bubble Sort
        System.out.println("Ejecutando Bubble Sort...");
        runSortingExperiment(
                "Inventario - Bubble Sort",
                Arrays.copyOf(data, data.length),
                BubbleSort::sort
        );

        // ordena el arreglo principal para búsqueda binaria
        System.out.println("\nOrdenando el inventario para búsquedas...");
        Arrays.sort(data);

        int option = -1;

        do {
            // menú de búsqueda
            System.out.println("\nSeleccione tipo de búsqueda:");
            System.out.println("1. Búsqueda exacta por stock");
            System.out.println("2. Búsqueda por rango de stock");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingrese un número entero: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                scanner.nextLine();
                continue;
            }

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    runExactStockSearch(scanner, data);
                    break;

                case 2:
                    runRangeStockSearch(scanner, data);
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción fuera de rango. Intente nuevamente.");
            }

        } while (option != 0);
    }

    // búsqueda binaria exacta por stock
    private static void runExactStockSearch(Scanner scanner, InventoryItem[] data) {

        Integer stock = null;

        while (stock == null) {
            System.out.print("\nIngrese el stock exacto a buscar: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                scanner.nextLine();
                continue;
            }

            stock = scanner.nextInt();
            scanner.nextLine();
        }

        // clave ficticia para comparar solo por stock
        InventoryItem key = new InventoryItem("0", "", stock);

        int index = BinarySearch.search(data, key);

        System.out.println(
                index >= 0
                        ? "Item encontrado: " + data[index]
                        : "No se encontró ningún item con ese stock."
        );
    }

    // búsqueda de inventario por rango de stock
    private static void runRangeStockSearch(Scanner scanner, InventoryItem[] data) {

        Integer min = null;
        Integer max = null;

        while (min == null) {
            System.out.print("\nIngrese el stock mínimo: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                scanner.nextLine();
                continue;
            }

            min = scanner.nextInt();
            scanner.nextLine();
        }

        while (max == null) {
            System.out.print("Ingrese el stock máximo: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                scanner.nextLine();
                continue;
            }

            max = scanner.nextInt();
            scanner.nextLine();
        }

        if (min > max) {
            System.out.println("El stock mínimo no puede ser mayor que el stock máximo.");
            return;
        }

        // claves para calcular límites del rango
        InventoryItem low = new InventoryItem("0", "", min);
        InventoryItem high = new InventoryItem("0", "", max);

        int from = Bounds.lowerBound(data, low);
        int to = Bounds.upperBound(data, high);

        System.out.println("\nItems en el rango de stock:");

        if (from < to) {
            for (int i = from; i < to; i++) {
                System.out.println(data[i]);
            }
        } else {
            System.out.println("No se encontraron items en ese rango.");
        }
    }
}
