package org.example.Proxy;

public enum Status {
    CREATED("Создан"),
    IN_PROCESS("В работе"),
    DONE("Выполнен"),
    CANCELED("Отменен");


    private final String description;

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}