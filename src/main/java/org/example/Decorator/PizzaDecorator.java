package org.example.Decorator;


public abstract class PizzaDecorator implements SimplePizzaInterface {
    protected SimplePizzaInterface pizza;

    public PizzaDecorator(SimplePizzaInterface pizza){
        this.pizza = pizza;
    }
}
