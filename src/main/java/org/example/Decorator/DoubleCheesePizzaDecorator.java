package org.example.Decorator;
import org.example.Builder.Filling;

public class DoubleCheesePizzaDecorator extends PizzaDecorator{
    public DoubleCheesePizzaDecorator(SimplePizzaInterface pizza){
        super(pizza);
    }

    @Override
    public double getPrice(){
        return pizza.getPrice() + Filling.CHEESE.getPrice();
    }
    @Override
    public String getInfo(){
        return pizza.getInfo();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getInfo());
        sb.append("\nВключена опция 'Двойной сыр'\nЦена: " + getPrice());
        return sb.toString();
    }
}
