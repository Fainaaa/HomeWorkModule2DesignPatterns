package org.example.Adapter;

//Предположим, что этот класс менять нельзя, как если бы он был чужим и уже где-то использовался
public class BunWithFilling {
    private BunFilling bunFilling;
    private BunDoughType bunDoughType;

    public BunWithFilling(BunFilling bunFilling, BunDoughType bunDoughType) {
        this.bunFilling = bunFilling;
        this.bunDoughType = bunDoughType;
    }
    public BunDoughType getBunDoughType() {
        return bunDoughType;
    }
    public BunFilling getBunFilling(){
        return bunFilling;
    }
    public void setBunFilling(BunFilling bunFilling) {
        this.bunFilling = bunFilling;
    }
    public void setBunDoughType(BunDoughType bunDoughType) {
        this.bunDoughType = bunDoughType;
    }

    public double getBunPrice(){ //метод расчета стоимости у Булочки есть, как и у пиццы, но у него другое название
        return bunDoughType.getPrice() + bunFilling.getPrice();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Пирожок с начинкой")
                .append(". Тесто: ").append(bunDoughType)
                .append(". Начинка: ").append(bunFilling)
                .append(". Цена: ").append(getBunPrice());

        return sb.toString();
    }

}
