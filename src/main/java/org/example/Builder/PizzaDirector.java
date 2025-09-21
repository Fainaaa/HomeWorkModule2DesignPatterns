package org.example.Builder;

//Класс - Director
public class PizzaDirector {
    public static Pizza.PizzaBuilder createMargherita() {
        return new Pizza.PizzaBuilder()
                .setSize(Size.MEDIUM)
                .setDoughType(DoughType.THIN)
                .addFilling(Filling.CHEESE)
                .addSauce(Sauce.TOMATO)
                .setName("Маргарита");
    }
    public static Pizza.PizzaBuilder createHamAndMushrooms() {
        return new Pizza.PizzaBuilder()
                .setSize(Size.MEDIUM)
                .setDoughType(DoughType.THIN)
                .addFilling(Filling.CHEESE)
                .addFilling(Filling.MUSHROOMS)
                .addFilling(Filling.HAM)
                .setName("Ветчина и грибыы");
    }
    public static Pizza.PizzaBuilder createVegetarian() {
        return new Pizza.PizzaBuilder()
                .setSize(Size.MEDIUM)
                .setDoughType(DoughType.THIN)
                .addFilling(Filling.CHEESE)
                .addFilling(Filling.MUSHROOMS)
                .addFilling(Filling.ONIONS)
                .addFilling(Filling.OLIVES)
                .addFilling(Filling.PEPPERS)
                .addSauce(Sauce.TOMATO)
                .setName("Вегетарианская");
    }

}
