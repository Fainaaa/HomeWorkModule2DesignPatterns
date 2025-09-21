package org.example.Builder;

public enum Sauce {
    TOMATO("Томатный", 20),
    PESTO("Песто", 50),
    RANCH("Ранч", 40);

    private final String description;
    private final double price;
    Sauce(String description, double price) {
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