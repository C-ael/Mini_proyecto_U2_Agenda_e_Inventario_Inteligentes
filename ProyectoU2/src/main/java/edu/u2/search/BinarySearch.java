package edu.u2.search;

public class BinarySearch {

    // realiza búsqueda binaria iterativa en un arreglo ordenado
    public static <T extends Comparable<T>> int search(T[] array, T key) {

        int low = 0;
        int high = array.length - 1;

        // divide el rango de búsqueda hasta encontrar el elemento
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // resultado de comparar el elemento central con la clave
            int cmp = array[mid].compareTo(key);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;   // la clave está a la derecha
            } else {
                high = mid - 1;  // la clave está a la izquierda
            }
        }

        // retorna -1 si el elemento no existe en el arreglo
        return -1;
    }
}
