package org.example.Strategy;

import org.example.Proxy.Order;
import java.io.Serializable;
import java.util.stream.DoubleStream;

public interface PricingStrategy extends Serializable {
    default double countTotalCost(Order order, String deliveryAddress) {
        return order.getItems().stream().flatMapToDouble(i -> DoubleStream.of(i.getPrice())).sum();
    }
}