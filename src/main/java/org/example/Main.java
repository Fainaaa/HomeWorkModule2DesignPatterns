package org.example;

import org.example.Adapter.BunDoughType;
import org.example.Adapter.BunFilling;
import org.example.Adapter.BunToOrderItemAdapter;
import org.example.Adapter.BunWithFilling;
import org.example.Builder.*;
import org.example.ChainOfResponsibility.CreatedStatusHandler;
import org.example.ChainOfResponsibility.InProcessStatusHandler;
import org.example.ChainOfResponsibility.EmptyStatusHandler;
import org.example.ChainOfResponsibility.StatusHandler;
import org.example.Decorator.DoubleCheesePizzaDecorator;
import org.example.Decorator.SimplePizzaInterface;
import org.example.Proxy.Order;
import org.example.Proxy.OrdersTableManagerProxy;
import org.example.Proxy.Status;
import org.example.Strategy.DiscountPricingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Все вызовы собраны тут, а не в своих пакетах, т.к. все мои примеры на одну тему
        //Порядок вызовов логически соответствует порядку появления сущностей и дополнений
        //Создание продукта -> добавление опций -> добавление заказа -> расчет стоимости заказа -> работа с заказами -> добавление нового продукта
        builderExample();
        decoratorExample();
        proxyExample();
        strategyExample();
        chainOfResponsibilityExample();
        adapterExample();
    }

    public static void builderExample(){
        System.out.println("\nРеализация паттерна Builder на примере создания пиццы");
        System.out.println("Собрана кастомная пицца");
        Pizza customPizza = new Pizza.PizzaBuilder()
                .setSize(Size.LARGE)
                .setDoughType(DoughType.THICK)
                .addFilling(Filling.CHEESE)
                .addFilling(Filling.MUSHROOMS)
                .addSauce(Sauce.RANCH)
                .build();
        System.out.println(customPizza);

        // Используем Director для стандартной пиццы
        System.out.println("Создана стандартная пицца Маргарита");
        Pizza margherita = PizzaDirector.createMargherita().build();
        System.out.println(margherita);

        //Поскольку Director возвращает PizzaBuilder, мы можем получить стандартную пиццу, но дополнить ее
        System.out.println("Создана стандартная пицца Вечина и сыр, соус заменен на Песто");
        Pizza hamAndMushroomsWithPesto = PizzaDirector.createHamAndMushrooms()
                .addSauce(Sauce.PESTO)
                .setName("Вечина и сыр под соусом Песто")
                .build();
        System.out.println(hamAndMushroomsWithPesto);
    }
    public static void decoratorExample(){
        System.out.println("\nРеализацция паттерна Decorator на примере дополнительных опций для пиццы");
        System.out.println("Создана новая пицца: ");
        Pizza pizzaWithoutDecorator = PizzaDirector.createMargherita().build();
        System.out.println(pizzaWithoutDecorator);
        System.out.println("К пицце добавлена опция Двойной сыр: ");
        DoubleCheesePizzaDecorator pizzaWithDecorator = new DoubleCheesePizzaDecorator(pizzaWithoutDecorator);
        System.out.println(pizzaWithDecorator);
    }

    public static void proxyExample(){
        System.out.println("\nРеализация паттерна Proxy на примере обращения к таблице заказов");
        System.out.println("Пицерия маленькая, у нее всего 1 доставщик, ему нужно видеть список заказов и иметь возможномть менять им статусы, чтобы не запутаться что уже доставлено." +
                "\nПосле создания списка, он несколько раз может открывать и просматривать его, каждый просмотр не должен приводить к запросу фйла с заказами" +
                "\nведь так как он один, никто другой не может поменять статус заказа");
        //Создаем 2 заказа
        List<SimplePizzaInterface> pizzaList1 = new ArrayList<>(Arrays.asList(PizzaDirector.createMargherita().build(), PizzaDirector.createHamAndMushrooms().build()));
        Order order1 = new Order(pizzaList1, "проспект Ленина, д. 24");
        List<SimplePizzaInterface> pizzaList2 = new ArrayList<>(Arrays.asList(PizzaDirector.createMargherita().build(), PizzaDirector.createVegetarian().build()));
        Order order2 = new Order(pizzaList2, "ул. Двор пролетарки, д. 7");
        //создаем список заказов
        List<Order> orders = new ArrayList<>(Arrays.asList(order1, order2));
        //передаем список прокси менеджеру
        OrdersTableManagerProxy proxy = new OrdersTableManagerProxy(orders);
        //загружаем реальную таблицу в файл заказов (src/main/resources/OrdersTable.txt)
        proxy.saveToFile();
        System.out.println("Кэш таблица прокси до вычитки");
        proxy.showTableCache();
        //первый раз вычитываем данные из файла
        proxy.loadFromFile();
        System.out.println("Кэш таблица прокси после вычитки");
        proxy.showTableCache();
        //Пытаемся несколько раз обратиться к файлу таблици заказов
        proxy.loadFromFile();
        proxy.loadFromFile();
        //Не видим строки "СПИСОК ЗАКАЗОВ ЗАГРУЖЕН" => прокси не обращался к файлу, он вернет данные из кэша
        //Изменяем статус одному из заказов
        proxy.getCacheTable().getOrders().get(0).setStatus(Status.DONE);
        System.out.println("Один из заказов изменен");
        System.out.println("Кэш таблица прокси после измененя заказа");
        proxy.showTableCache();
        //Выполнили все заказы, очищаем таблицу Кэша
        //Перед тем как очиститься, таблица запишется в файл, об этом свидетельствует строка "СПИСОК ЗАКАЗОВ ВЫГРУЖЕН"
        System.out.println("При завершении работы с кэш таблицей (при ее очищении), данные сохраняются в файл");
        proxy.clearCacheTable();
        System.out.println("Кэш таблица прокси после очистки");
        proxy.showTableCache();
        System.out.println("Кэш таблица прокси после вычитки");
        //Попробуем подгрузить снова
        proxy.loadFromFile();
        proxy.showTableCache();
    }

    public static void chainOfResponsibilityExample(){
        System.out.println("\nРеализация паттерна Chain Of Responsibility на примере изменения статусной модели Заказа");
        System.out.println("Допустим, что у сущности Заказа есть статусная модель: Пустота -> Создан -> В работе -> Выполнен. " +
                "Смена статусов может происходить только в указанном порядке. \nВ конце рабочего дня выяснилось, " +
                "что некоторые заказы были выполнены, но в системе почему-то не перешли в нужный статус. \nНеобходимо " +
                "перевести заказы в статус Выполнено, не нарушая статусную модель.");
        StatusHandler emptyStatusHandler = new EmptyStatusHandler();
        StatusHandler createdStatusHandler = new CreatedStatusHandler();
        StatusHandler inProcessStatusHandler = new InProcessStatusHandler();

        emptyStatusHandler.setNext(createdStatusHandler);
        createdStatusHandler.setNext(inProcessStatusHandler);
        //Создаем тестовый список из 3 заказов
        List<SimplePizzaInterface> list1 = new ArrayList<>(Arrays.asList(PizzaDirector.createMargherita().build()));
        List<Order> testOrders = new ArrayList<>();
        testOrders.add(new Order(list1, "null")); testOrders.get(0).setStatus(null);//пустой
        testOrders.add(new Order(list1, "null")); testOrders.get(1).setStatus(Status.CREATED);//создан
        testOrders.add(new Order(list1, "null")); testOrders.get(2).setStatus(Status.IN_PROCESS);//в работе
        //запускаем цепочку по каждому элементу
        for (int i = 0; i < testOrders.size(); i++){
            System.out.println("Заказ " + (i+1));
            emptyStatusHandler.changeStatus(testOrders.get(i));
        }
    }

    public static void strategyExample(){
        System.out.println("\nРеализация паттерна Strategy на примере разных стратегий расчета стоимости заказа");
        //Создаем заказ с дефолтным рассчетом стоимости
        List<SimplePizzaInterface> list2 = new ArrayList<>(Arrays.asList(PizzaDirector.createMargherita().build(), PizzaDirector.createVegetarian().build()));
        Order strategyOrder = new Order(list2, "ул. Сергея Есенина, д. 15");
        System.out.println("Стандартная стратегия");
        System.out.println(strategyOrder.getTotalCost());
        System.out.println("Стратегия с учетом скидки в 30%");
        strategyOrder.setPricingStrategy(new DiscountPricingStrategy());
        System.out.println(strategyOrder.getTotalCost());
    }

    public static void adapterExample(){
        System.out.println("\nРеализация паттерна Adapter на примере добавления Булочки с начинкой в список единиц для заказа");
        System.out.println("В пицерии появились булочки, но их закупают у стороннего производителя, однако должна быть возможность добавить булочку в заказ");
        SimplePizzaInterface pizza = PizzaDirector.createVegetarian().build();
        SimplePizzaInterface bun = new BunToOrderItemAdapter(new BunWithFilling(BunFilling.CHERRY, BunDoughType.LAYERED));
        //Компилятор не ругается, т.к. и класс адаптера и класс пицы реализуют интерфейс SimplePizzaInterface
        List<SimplePizzaInterface> orderItems = new ArrayList<>(Arrays.asList(pizza, bun));
        Order orderWithBun = new Order(orderItems, "ул. Короткая, д.1");
        System.out.println("Заказ содержащий и пиццу и булочку:");
        System.out.println(orderWithBun);
    }
}