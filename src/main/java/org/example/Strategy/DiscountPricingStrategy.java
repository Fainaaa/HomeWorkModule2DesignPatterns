package org.example.Strategy;
import org.example.Proxy.Order;

public class DiscountPricingStrategy implements PricingStrategy {
    private static final long serialVersionUID = 1L;
    @Override
    public double countTotalCost(Order order, String deliveryAddress){
        double deliveryCost = deliveryAddress.length() * 10;//Чем длиннее адрес, тем больше доставка
        //30% скидка, округляем до десятых
        return Math.round(((PricingStrategy.super.countTotalCost(order, deliveryAddress) + deliveryCost) * 0.7) * 10.0) / 10.0;

    }
}
