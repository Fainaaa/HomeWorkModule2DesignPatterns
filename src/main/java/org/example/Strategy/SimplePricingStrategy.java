package org.example.Strategy;
import org.example.Proxy.Order;

public class SimplePricingStrategy implements PricingStrategy {
    private static final long serialVersionUID = 1L;
    @Override
    public double countTotalCost(Order order, String deliveryAddress){
        double deliveryCost = deliveryAddress.length() * 10;//Чем длиннее адрес, тем больше доставка
        return PricingStrategy.super.countTotalCost(order, deliveryAddress) + deliveryCost;
    }
}
