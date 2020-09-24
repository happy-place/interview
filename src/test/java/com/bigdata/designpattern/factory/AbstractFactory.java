package com.bigdata.designpattern.factory;

abstract class Food {
    abstract void eat();
}

class ChineseRice extends Food{
    @Override
    void eat() {
        System.out.println("eat chinese rice");
    }
}

class USRice extends Food{
    @Override
    void eat() {
        System.out.println("eat us rice");
    }
}

abstract class Drink {
    abstract void drink();
}

class ChineseDrink extends Drink{
    @Override
    void drink() {
        System.out.println("drink tea");
    }
}

class USDrink extends Drink{
    @Override
    void drink() {
        System.out.println("drink coffee");
    }
}

abstract class MyAbstractFactory{
    abstract Food createFood();
    abstract Drink createDrink();
}

class ChineseFactory extends MyAbstractFactory{
    @Override
    public Food createFood() {
        return new ChineseRice();
    }

    @Override
    public Drink createDrink() {
        return new ChineseDrink();
    }
}


class USFactory extends MyAbstractFactory{
    @Override
    public Food createFood() {
        return new USRice();
    }

    @Override
    public Drink createDrink() {
        return new USDrink();
    }
}

/**
 * 抽象工厂设计模式
 * 1.不同类产品继承各自抽象父类；
 * 2.不同工厂继承自统一抽象工厂，此抽象工厂生产了不同类产品
 *
 * 方便成簇拓展，如一次性拓展某个国家的食品和饮品；
 * 不方便拓展单一品类产品
 */

public class AbstractFactory {

    public static void main(String[] args) {
        MyAbstractFactory factory = new USFactory();
        Drink drink = factory.createDrink();
        drink.drink();
        Food food = factory.createFood();
        food.eat();

        factory = new ChineseFactory();
        drink = factory.createDrink();
        drink.drink();
        food = factory.createFood();
        food.eat();
    }

}
