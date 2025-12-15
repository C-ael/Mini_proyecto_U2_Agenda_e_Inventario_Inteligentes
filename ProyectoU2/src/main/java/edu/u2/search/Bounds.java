package edu.u2.search;

public class Bounds {

    // primer índice >= key
    public static <T extends Comparable<T>> int lowerBound(T[] array, T key) {

        int low = 0;
        int high = array.length;

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

    // primer índice > key
    public static <T extends Comparable<T>> int upperBound(T[] array, T key) {

        int low = 0;
        int high = array.length;

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
