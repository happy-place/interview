package com.bigdata.designpattern.factory;

interface Moveable2 {
    void move();
}

class Car2 implements Moveable2 {
    @Override
    public void move() {
        System.out.println("wu~");
    }
}

class Plane2 implements Moveable2 {
    @Override
    public void move() {
        System.out.println("cuao~~");
    }
}

class Moto2 implements Moveable2 {
    @Override
    public void move() {
        System.out.println("hong~~");
    }
}

class MoveableFactory{
    public Car2 createCar(){
        // pre process
        Car2 car = new Car2();
        // post process
        return car;
    }

    public Plane2 createPlane(){
        // pre process
        Plane2 plane = new Plane2();
        // post process
        return plane;
    }

    public Moto2 createMoto(){
        // pre process
        Moto2 moto = new Moto2();
        // post process
        return moto;
    }

}


/**
 *
 * factory.createXX()
 *
 * 任意定制具备某项功能产品的生产过程
 * 1.公共特征抽象为接口，不同产品各有各的实现；
 * 2.使用统一工厂获取产品对象；
 * 3.拓展时，需要添加新产品实现，然后在工厂中添加对应获取新对象方法
 *
 * 简单工厂适合对象创建过程比较复杂的场景，且通常拓展性不足，需要硬编码改动地方太多
 *
 */
public class SimpleFactory2 {

    public static void main(String[] args) {
        MoveableFactory factory = new MoveableFactory();
        Moveable2 moveable = factory.createCar();
        moveable.move();
        moveable = factory.createPlane();
        moveable.move();
        moveable = factory.createMoto();
        moveable.move();
    }

}
