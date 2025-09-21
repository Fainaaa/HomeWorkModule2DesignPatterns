package org.example.ChainOfResponsibility;

import org.example.Proxy.Order;
import org.example.Proxy.Status;

public class CreatedStatusHandler extends StatusHandler{

    @Override
    public void changeStatus(Order order) {
        if(order.getStatus() == Status.CREATED){
            System.out.println("Исходный статус " + order.getStatus() + " изменен на " + Status.IN_PROCESS);
            order.setStatus(Status.IN_PROCESS);
        }
        if (next != null){
            next.changeStatus(order);
        }
    }
}
