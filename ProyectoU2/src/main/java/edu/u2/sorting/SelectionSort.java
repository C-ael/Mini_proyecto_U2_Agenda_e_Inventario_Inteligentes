package edu.u2.sorting;

public class SelectionSort {

    // ordena el arreglo usando selection sort
    public static <T extends Comparable<T>> void sort(T[] array, SortMetrics metrics) {

        int n = array.length;

        // recorre el arreglo buscando el menor elemento
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // compara con los elementos restantes
            for (int j = i + 1; j < n; j++) {
                metrics.incComparisons();
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // intercambia si encuentra un nuevo mínimo
            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                metrics.incSwaps();
            }
        }
    }
}
