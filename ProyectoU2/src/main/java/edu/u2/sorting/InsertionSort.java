package edu.u2.sorting;

public class InsertionSort {

    // ordena con inserción
    public static <T extends Comparable<T>> void sort(T[] array, SortMetrics metrics) {

        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= 0) {
                metrics.incComparisons();

                if (array[j].compareTo(key) > 0) {
                    array[j + 1] = array[j];
                    metrics.incSwaps();
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }
}
