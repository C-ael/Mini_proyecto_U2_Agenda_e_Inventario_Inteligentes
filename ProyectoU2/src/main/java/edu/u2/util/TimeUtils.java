package edu.u2.util;

public class TimeUtils {

    // Método para medir el tiempo en nanosegundos de una operación
    public static long measureTime(Runnable task) {
        long startTime = System.nanoTime();  // Registra el tiempo de inicio
        task.run();  // Ejecuta la tarea pasada como parámetro
        long endTime = System.nanoTime();  // Registra el tiempo de finalización
        return endTime - startTime;  // Retorna el tiempo de ejecución en nanosegundos
    }

    // Método para calcular la mediana de los tiempos de varias ejecuciones
    public static double calculateMedian(long[] times) {
        // Ordena los tiempos para calcular la mediana
        java.util.Arrays.sort(times);

        int middle = times.length / 2;
        if (times.length % 2 == 1) {
            return times[middle];  // Si el número de elementos es impar, retorna el valor del medio
        } else {
            return (times[middle - 1] + times[middle]) / 2.0;  // Si es par, la mediana es el promedio de los dos valores centrales
        }
    }
}
