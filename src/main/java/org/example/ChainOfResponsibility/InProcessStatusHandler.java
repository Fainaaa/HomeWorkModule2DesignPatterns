package org.example.ChainOfResponsibility;

import org.example.Proxy.Order;
import org.example.Proxy.Status;

public class InProcessStatusHandler extends StatusHandler{

    @Override
    public void changeStatus(Order order) {
        if(order.getStatus() == Status.IN_PROCESS){
            System.out.println("Исходный статус " + order.getStatus() + " изменен на " + Status.DONE);
            order.setStatus(Status.DONE);
        }
        if (next != null){
            next.changeStatus(order);
        }
    }
}