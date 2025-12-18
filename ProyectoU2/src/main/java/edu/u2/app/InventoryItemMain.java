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

        // valida que el archivo tenga datos
        if (data.length == 0) {
            System.out.println("No hay inventario cargado.");
            return;
        }

        // experimento de ordenamiento con dataset inverso
        System.out.println("\n--- Experimento de Ordenamiento (Inventario Inverso) ---");
        System.out.println("El dataset inverso penaliza Insertion Sort y Bubble Sort.\n");

        // ejecuta Insertion Sort sobre una copia del arreglo
        System.out.println("Ejecutando Insertion Sort...");
        runSortingExperiment("Inventario - Insertion Sort",
                Arrays.copyOf(data, data.length),
                InsertionSort::sort
        );

        // ejecuta Bubble Sort sobre una copia del arreglo
        System.out.println("Ejecutando Bubble Sort...");
        runSortingExperiment("Inventario - Bubble Sort",
                Arrays.copyOf(data, data.length),
                BubbleSort::sort
        );

        // ordena el arreglo original para permitir búsqueda binaria
        System.out.println("\nOrdenando el arreglo principal para Búsqueda Binaria...");
        Arrays.sort(data);

        // BÚSQUEDA EXACTA
        System.out.print("\nIngrese stock exacto a buscar: ");

        // valida entrada numérica
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Volviendo al menú principal.");
            scanner.nextLine();
            return;
        }

        int stock = scanner.nextInt();

        // clave ficticia para comparar solo por stock
        InventoryItem key = new InventoryItem("0", "", stock);

        int index = BinarySearch.search(data, key);

        System.out.println(index >= 0 ? "Item encontrado: " + data[index] : "Stock no encontrado");

        // BÚSQUEDA CON RANGO
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

        // claves para calcular límites del rango
        InventoryItem low = new InventoryItem("0", "", min);
        InventoryItem high = new InventoryItem("0", "", max);

        // obtiene índices del rango usando búsqueda binaria
        int from = Bounds.lowerBound(data, low);
        int to = Bounds.upperBound(data, high);

        System.out.println("\nItems en rango de stock:");
        for (int i = from; i < to; i++) {
            System.out.println(data[i]);
        }
    }
}
