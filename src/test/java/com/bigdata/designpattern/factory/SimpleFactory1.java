package com.bigdata.designpattern.factory;

interface Moveable1 {
    void move();
}

class Car1 implements Moveable1{
    @Override
    public void move() {
        System.out.println("wu~");
    }
}

class Plane1 implements Moveable1{
    @Override
    public void move() {
        System.out.println("cuao~~");
    }
}

class Moto1 implements Moveable1{
    @Override
    public void move() {
        System.out.println("hong~~");
    }
}

/**
 * XX xx = new Impl()
 * 任意定制具备某项功能产品
 * 1.将特定功能抽象为借口，向定制谁，就实现谁
 * 2.工厂不局限与factory函数，凡是能产生对象的方法都可以成为工厂
 *
 */
public class SimpleFactory1 {

    public static void main(String[] args) {
        Moveable1 moveable1 = new Car1();
        moveable1.move();

        moveable1 = new Plane1();
        moveable1.move();

        moveable1 = new Moto1();
        moveable1.move();
    }

}
