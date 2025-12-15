package edu.u2.sorting;

public class SelectionSort {

    // ordena con selección
    public static <T extends Comparable<T>> void sort(T[] array, SortMetrics metrics) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                metrics.incComparisons();
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                // intercambio
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                metrics.incSwaps();
            }
        }
    }
}
