package org.example.Builder;

public enum DoughType {
    THIN("Тонкое", 50),
    THICK("Толстое", 100);

    private final String description;
    private final double price;

    DoughType(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return description;
    }
    public double getPrice(){
        return this.price;
    }
}