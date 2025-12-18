package edu.u2.sorting;

public class SortMetrics {

    private long comparisons;
    private long swaps;   // burbuja y selección
    private long moves;   // inserción
    private long timeNano;

    // inicializa métricas
    public SortMetrics() {
        reset();
    }

    // reinicia contadores
    public void reset() {
        comparisons = 0;
        swaps = 0;
        moves = 0;
        timeNano = 0;
    }

    // incrementa comparaciones
    public void incComparisons() {
        comparisons++;
    }

    // incrementa swaps
    public void incSwaps() {
        swaps++;
    }

    // incrementa movimientos
    public void incMoves() {
        moves++;
    }

    // establece tiempo en nanosegundos
    public void setTimeNano(long timeNano) {
        this.timeNano = timeNano;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getMoves() {
        return moves;
    }

    public long getTimeNano() {
        return timeNano;
    }
}
