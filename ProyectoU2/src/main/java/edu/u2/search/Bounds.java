package edu.u2.search;

public class Bounds {

    // retorna el primer índice donde el valor es >= key
    public static <T extends Comparable<T>> int lowerBound(T[] array, T key) {

        int low = 0;
        int high = array.length;

        // búsqueda binaria para el límite inferior
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (array[mid].compareTo(key) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // retorna el primer índice donde el valor es > key
    public static <T extends Comparable<T>> int upperBound(T[] array, T key) {

        int low = 0;
        int high = array.length;

        // búsqueda binaria para el límite superior
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (array[mid].compareTo(key) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
