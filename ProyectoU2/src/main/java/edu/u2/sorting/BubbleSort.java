package edu.u2.sorting;

public class BubbleSort {

    // ordena el arreglo usando bubble sort
    public static <T extends Comparable<T>> void sort(T[] array, SortMetrics metrics) {

        int n = array.length;

        // hace múltiples pasadas sobre el arreglo
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                // compara elementos adyacentes
                metrics.incComparisons();
                if (array[j].compareTo(array[j + 1]) > 0) {

                    // intercambia si están en desorden
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    metrics.incSwaps();
                }
            }
        }
    }
}
