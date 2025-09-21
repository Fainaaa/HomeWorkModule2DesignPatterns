package org.example.Proxy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersTableManager implements TableManager {
    public static String FILE_PATH = "src/main/resources/OrdersTable.txt";
    private OrdersTableManager.OrdersTable ordersTable;

    public OrdersTableManager(List<Order> orders){
        this.ordersTable = new OrdersTable(orders);
    }

    public OrdersTable getOrdersTable(){
        return ordersTable;
    }

    public void showTable(){
        System.out.println(ordersTable);
    }

    public void saveToFile() {
        saveToFile(FILE_PATH);
    }
    private void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(ordersTable);
            System.out.println("СПИСОК ЗАКАЗОВ СОХРАНЕН");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFromFile() {
        loadFromFile(FILE_PATH);
    }
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            ordersTable = (OrdersTable) ois.readObject();
            System.out.println("СПИСОК ЗАКАЗОВ ЗАГРУЖЕН");

        } catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static class OrdersTable implements Serializable {
        private static final long serialVersionUID = 1L;
        private List<Order> orders = new ArrayList<>();

        public OrdersTable(List<Order> orders) {
            this.orders = orders;
        }

        public void addOrder(Order order) {
            orders.add(order);
        }
        public List<Order> getOrders() {
            return new ArrayList<>(orders);
        }
        public void clearOrders() {
            orders.clear();
        }

        @Override
        public String toString() {
            if (orders.isEmpty()) {
                return "Список пуст";
            }
            StringBuilder info = new StringBuilder();
            info.append("Заказов: ").append(orders.size()).append("\n\n");
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                info.append("Заказ №").append(i + 1).append("\n");
                info.append(order.toString());
            }
            return info.toString();
        }
    }

}
