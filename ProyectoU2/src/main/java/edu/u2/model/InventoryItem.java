package edu.u2.model;

public class InventoryItem implements Comparable<InventoryItem> {

    private String id;
    private String name;
    private int stock;

    // constructor del item
    public InventoryItem(String id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    // obtiene el id
    public String getId() {
        return id;
    }

    // obtiene el nombre
    public String getName() {
        return name;
    }

    // obtiene el stock
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
