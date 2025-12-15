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

public class InventaryItemMain {
    /* ===============================
      INVENTARIO (ARREGLO)
      =============================== */
    public static void runInventory(Scanner scanner) {

        InventoryItem[] data =
                CSVLoader.loadInventory("inventario_500_inverso.csv");

        if (data.length == 0) {
            System.out.println("No hay inventario cargado.");
            return;
        }

        // ==========================================================
        // === NUEVO REQUERIMIENTO: ORDENAMIENTO DE INVENTARIO INVERSO Y COMPARACIÓN ===
        System.out.println("\n--- Experimento de Ordenamiento (Inventario Inverso) ---");
        System.out.println("El dataset inverso penaliza Insertion Sort y Bubble Sort.\n");

        // 1. Ejecutar Insertion Sort (Alta penalización esperada)
        System.out.println("Ejecutando Insertion Sort...");
        runSortingExperiment(
                "Inventario - Insertion Sort",
                Arrays.copyOf(data, data.length), // Copia del arreglo original
                InsertionSort::sort
        );

        // 2. Ejecutar Bubble Sort (Alta penalización esperada)
        System.out.println("Ejecutando Bubble Sort...");
        runSortingExperiment(
                "Inventario - Bubble Sort",
                Arrays.copyOf(data, data.length), // Copia del arreglo original
                BubbleSort::sort
        );

        // 4. Ordenar el arreglo principal (data) con Arrays.sort para la búsqueda binaria.
        // Se usa el algoritmo más eficiente de Java para garantizar la velocidad de la búsqueda.
        System.out.println("\nOrdenando el arreglo principal para Búsqueda Binaria...");
        Arrays.sort(data);

        /* ---- BÚSQUEDA EXACTA ---- */
        System.out.print("\nIngrese stock exacto a buscar: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Volviendo al menú principal.");
            scanner.nextLine();
            return;
        }
        int stock = scanner.nextInt();

        InventoryItem key = new InventoryItem("0", "", stock);
        int index = BinarySearch.search(data, key);

        System.out.println(index >= 0
                ? "Item encontrado: " + data[index]
                : "Stock no encontrado");

        /* ---- BÚSQUEDA POR RANGO ---- */
        System.out.print("\nStock mínimo: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Volviendo al menú principal.");
            scanner.nextLine();
            return;
        }
        int min = scanner.nextInt();

        System.out.print("Stock máximo: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Volviendo al menú principal.");
            scanner.nextLine();
            return;
        }
        int max = scanner.nextInt();

        InventoryItem low = new InventoryItem("0", "", min);
        InventoryItem high = new InventoryItem("0", "", max);

        int from = Bounds.lowerBound(data, low);
        int to = Bounds.upperBound(data, high);

        System.out.println("\nItems en rango de stock:");
        for (int i = from; i < to; i++) {
            System.out.println(data[i]);
        }
    }

}
