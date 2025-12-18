package edu.u2.model;

public class InventoryItem implements Comparable<InventoryItem> {

    private String id;
    private String name;
    private int stock;

    public InventoryItem(String id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getStock() {
        return stock;
    }

    // comparación por stock
    @Override
    public int compareTo(InventoryItem other) {
        return Integer.compare(this.stock, other.stock);
    }

    // representación para consola
    @Override
    public String toString() {
        return "Item [" +
                "id=" + id +
                ", nombre=" + name +
                ", stock=" + stock +
                "]";
    }
}
