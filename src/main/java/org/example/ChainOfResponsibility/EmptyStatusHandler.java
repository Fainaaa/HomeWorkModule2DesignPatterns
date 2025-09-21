package org.example.ChainOfResponsibility;

import org.example.Proxy.Order;
import org.example.Proxy.Status;

public class EmptyStatusHandler extends StatusHandler{

    @Override
    public void changeStatus(Order order) {
        if(order.getStatus() == null){
            System.out.println("Исходный статус null изменен на " + Status.CREATED);
            order.setStatus(Status.CREATED);
        }
        if (next != null){
            next.changeStatus(order);
        }
    }
}
