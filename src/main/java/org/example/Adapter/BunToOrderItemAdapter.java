package org.example.Adapter;

import org.example.Decorator.SimplePizzaInterface;

public class BunToOrderItemAdapter implements SimplePizzaInterface {
    BunWithFilling bunWithFilling;

    public BunToOrderItemAdapter(BunWithFilling bunWithFilling) {
        this.bunWithFilling = bunWithFilling;
    }
    @Override
    public double getPrice() {
        return bunWithFilling.getBunPrice();
    }

    @Override
    public String getInfo() {
        return bunWithFilling.toString();
    }

    @Override
    public String toString(){
        return  bunWithFilling.toString();
    }
}
