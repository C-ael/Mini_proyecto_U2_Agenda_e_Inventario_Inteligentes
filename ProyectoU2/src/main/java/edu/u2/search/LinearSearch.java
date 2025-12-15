package edu.u2.search;

import java.util.ArrayList;
import java.util.List;

public class LinearSearch {

    // búsqueda secuencial: primera coincidencia
    public static <T> int first(T[] array, T key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    // búsqueda secuencial: última coincidencia
    public static <T> int last(T[] array, T key) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    // búsqueda secuencial: todas las coincidencias
    public static <T> List<Integer> findAll(T[] array, T key) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(key)) {
                result.add(i);
            }
        }
        return result;
    }

    // búsqueda secuencial con centinela
    public static <T> int sentinel(T[] array, T key) {
        int n = array.length;
        T last = array[n - 1];

        array[n - 1] = key;
        int i = 0;

        while (!array[i].equals(key)) {
            i++;
        }

        array[n - 1] = last;

        if (i < n - 1 || last.equals(key)) {
            return i;
        }
        return -1;
    }
}
