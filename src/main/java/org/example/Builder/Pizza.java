package org.example.Builder;
import org.example.Decorator.SimplePizzaInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

//Класс - Product
public class Pizza implements SimplePizzaInterface {
    private static final long serialVersionUID = 1L;
    private Size size; //обязательный
    private DoughType doughType; //обязательный
    private List<Filling> filling; //в целом пицца без начинки - тоже пицца
    private Sauce sauce; //необязательный
    private String name; //необязательный

    public Pizza(PizzaBuilder builder){
        this.size = builder.size;
        this.doughType = builder.doughType;
        this.filling = new ArrayList<>(builder.filling);
        this.sauce = builder.sauce;
        this.name = builder.name;
    }

    public Size getSize(){
        return size;
    }
    public DoughType getDoughType(){
        return doughType;
    }
    public List<Filling> getFilling(){
        return new ArrayList<>(filling);
    }
    public Sauce getSauce(){
        return this.sauce;
    }
    public String getName(){
        return this.name;
    }
    //Методы, переопределения которых требует интерфейс SimplePizzaInterface
    @Override
    public String getInfo() {
        StringBuilder description = new StringBuilder();
        description.append("Пицца <" + (name==null? "Без названия": name) + ">")
                .append(". Размер: ").append(size)
                .append(". Тесто: ").append(doughType)
                .append(". Начинка: ")
                .append(filling.isEmpty()? "Отсутствуют": filling.stream().map(f->f.toString()).collect(Collectors.joining(", ")))
                .append(". Cоус: " + (sauce == null? "Отсутствует": sauce));

        return description.toString();
    }
    @Override
    public double getPrice() {
        return size.getPrice() + size.getPriceCoeff()
                * (filling.stream().flatMapToDouble(i -> DoubleStream.of(i.getPrice())).sum()
                + doughType.getPrice() + (sauce == null? 0 : sauce.getPrice()));
    }
    //toString переопределяем для удобства вывода
    @Override
    public String toString(){
        return getInfo() + ". Цена: " + getPrice();
    }

    //Класс - Builder
    public static class PizzaBuilder {
        private Size size;
        private DoughType doughType;
        private List<Filling> filling = new ArrayList<>();
        private Sauce sauce;
        private String name;

        public PizzaBuilder setSize(Size size) {
            this.size = size;
            return this;
        }
        public PizzaBuilder setDoughType(DoughType doughType) {
            this.doughType = doughType;
            return this;
        }
        public PizzaBuilder addFilling(Filling filling) {
            this.filling.add(filling);
            return this;
        }
        public PizzaBuilder addSauce(Sauce sauce) {
            this.sauce = sauce;
            return this;
        }
        public PizzaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Pizza build() {
            if (size == null) {
                throw new IllegalStateException("Размер пиццы не указан");
            }
            if (doughType == null) {
                throw new IllegalStateException("Тип теста не указан");
            }
            return new Pizza(this);
        }
    }
}