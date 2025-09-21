package org.example.Builder;

public enum Filling {
    HAM("Ветчина", 50),
    SAUSAGE("Колбаса", 60),
    MUSHROOMS("Грибы", 50),
    ONIONS("Лук", 10),
    OLIVES("Оливки", 100),
    PEPPERS("Перец", 70),
    CHEESE("Сыр", 30);

    private final String description;
    private final double price;

    Filling(String description, double price) {
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