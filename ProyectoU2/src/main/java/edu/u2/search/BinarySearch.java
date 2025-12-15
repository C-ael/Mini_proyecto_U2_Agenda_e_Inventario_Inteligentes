package edu.u2.search;

public class BinarySearch {

    // búsqueda binaria iterativa
    public static <T extends Comparable<T>> int search(T[] array, T key) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int cmp = array[mid].compareTo(key);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
