package org.example.Adapter;

public enum BunDoughType {
    LAYERED("Слоеное", 30),
    YEAST("Дрожжевое", 20),
    SHORTBREAD("Песочное", 50);

    private final String description;
    private final double price;

    BunDoughType(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return description;
    }

    public double getPrice() {
        return price;

    }
}
