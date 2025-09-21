package org.example.Proxy;

import java.util.List;
import java.util.Objects;

public class OrdersTableManagerProxy implements TableManager{
    private OrdersTableManager ordersTableManager;
    private OrdersTableManager.OrdersTable ordersTableCache = null;

    public OrdersTableManagerProxy(List<Order> orders){
        this.ordersTableManager = new OrdersTableManager(orders);
    }

    @Override
    public void loadFromFile() {
        if(ordersTableCache == null || ordersTableCache.getOrders().isEmpty()) {
            ordersTableManager.loadFromFile();
            ordersTableCache = ordersTableManager.getOrdersTable();
        }
    }
    @Override
    public void saveToFile(){
        ordersTableManager.saveToFile();
    }

    public OrdersTableManager.OrdersTable getCacheTable() {
        return ordersTableCache;
    }
    public void showTableCache() {
        if (ordersTableCache == null)
            System.out.println("Список пуст");
        else
            System.out.println(ordersTableCache);
    }
    public void showTable(){
        System.out.println(ordersTableManager.getOrdersTable());
    }
    public void clearCacheTable(){
        saveToFile();
        ordersTableCache.clearOrders();
    }
}

