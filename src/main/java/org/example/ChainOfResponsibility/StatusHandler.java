package org.example.ChainOfResponsibility;

import org.example.Proxy.Order;

public abstract class StatusHandler {
    protected StatusHandler next;

    public void setNext(StatusHandler next) {
        this.next = next;
    }

    public abstract void changeStatus(Order order);

}
