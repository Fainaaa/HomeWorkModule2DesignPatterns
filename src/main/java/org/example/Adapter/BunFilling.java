package org.example.Adapter;

public enum BunFilling {
    CREAM("Сливочный крем", 50),
    RASPBERRY("Малина", 100),
    CHERRY("Вишня", 80),
    PEAR("Груша", 70);

    private final String description;
    private final double price;

    BunFilling(String description, double price) {
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
