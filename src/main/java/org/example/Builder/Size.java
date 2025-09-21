package org.example.Builder;

public enum Size {
    SMALL("Маленькая", 100, 1),
    MEDIUM("Средняя",  200, 1.5),
    LARGE("Большая", 300, 2);

    private final String description;
    private final double price;
    private double priceCoeff;

    Size(String description, double price, double priceCoeff) {
        this.description = description;
        this.price = price;
        this.priceCoeff = priceCoeff;
    }

    @Override
    public String toString(){
        return description;
    }
    public double getPrice(){
        return this.price;
    }
    public double getPriceCoeff(){
        return this.priceCoeff;
    }
}

