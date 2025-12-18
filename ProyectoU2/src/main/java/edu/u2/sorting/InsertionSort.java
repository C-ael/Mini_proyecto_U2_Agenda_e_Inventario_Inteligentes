package edu.u2.sorting;

public class InsertionSort {

    // ordena el arreglo usando insertion sort
    public static <T extends Comparable<T>> void sort(T[] array, SortMetrics metrics) {

        // recorre el arreglo desde el segundo elemento
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;

            // desplaza los elementos mayores a la derecha
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

            // inserta el elemento en su posición correcta
            array[j + 1] = key;
        }
    }
}
