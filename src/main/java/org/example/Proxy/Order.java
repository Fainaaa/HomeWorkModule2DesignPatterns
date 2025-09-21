package org.example.Proxy;

import org.example.Decorator.SimplePizzaInterface;
import org.example.Strategy.PricingStrategy;
import org.example.Strategy.SimplePricingStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SimplePizzaInterface> items = new ArrayList<>();
    private String deliveryAddress;
    private Status status;
    private PricingStrategy pricingStrategy; //Стратегия рассчета стоимости заказа
    private double totalCost;

    //конструктор с дефолтной стратегией рассчета стоимости
    public Order(List<SimplePizzaInterface> items, String deliveryAddress) {
        this.items = new ArrayList<>(items);
        this.deliveryAddress = deliveryAddress;
        this.status = Status.CREATED;
        this.pricingStrategy = new SimplePricingStrategy();
        reCountTotalCost();
    }

    public Order(List<SimplePizzaInterface> items, String deliveryAddress, PricingStrategy pricingStrategy) {
        this.items = new ArrayList<>(items);
        this.deliveryAddress = deliveryAddress;
        this.status = Status.CREATED;
        this.pricingStrategy = pricingStrategy;
        reCountTotalCost();
    }

    private void reCountTotalCost(){    //пересчет итоговой стоимости заказа
        this.totalCost = pricingStrategy.countTotalCost(this, deliveryAddress);
    }
    public List<SimplePizzaInterface> getItems() {
        return items;
    }
    public void addItem(SimplePizzaInterface pizza){
        items.add(pizza);
        reCountTotalCost();//пересчитыаем при добавлении пиццы
    }
    public double getTotalCost() {
        return totalCost;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        reCountTotalCost(); //пересчитыаем при смене адреса
    }
    public Status getStatus(){
        return status;
    }
    public void setStatus(Status status){
        this.status = status;
    }
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
        reCountTotalCost();//пересчитываем при смене стратегии
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Адрес: ").append(getDeliveryAddress()).append("\n");
        info.append("Сумма: ").append(String.format("%.2f", getTotalCost()));
        info.append("\nСтатус: ").append(getStatus());
        info.append("\nПозиции:\n");

        for (SimplePizzaInterface pizza : getItems()) {
            info.append("  - ").append(pizza.toString()).append("\n");
        }
        info.append("\n");
        return info.toString();
    }
}
