package org.example.Decorator;

import java.io.Serializable;

//этот интерфейс создан для того, чтобы связать классы Pizza и его декораторы
public interface SimplePizzaInterface extends Serializable {
    public double getPrice();
    public String getInfo();
}
