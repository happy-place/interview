package com.bigdata.designpattern.proxy;

interface Moveable1 {
    void move();
}

class Car1 implements Moveable1 {
    @Override
    public void move() {
        System.out.println("wu~~~");
    }
}

class Plane1 implements Moveable1 {
    @Override
    public void move() {
        System.out.println("cua~~~");
    }
}


class Car1Proxy implements Moveable1 {
    Car1 proxyed;
    private long startMills, stopMills;

    private void preLogging() {
        System.out.println("start logging");
    }

    private void postLogging() {
        System.out.println("stop logging");
    }

    private void startTiming() {
        startMills = System.currentTimeMillis();
    }

    private void stopTiming() {
        stopMills = System.currentTimeMillis();
        System.out.println("total cost " + (stopMills - startMills) + "ms");
    }

    public void setProxyed(Car1 proxyed) {
        this.proxyed = proxyed;
    }

    @Override
    public void move() {
        preLogging();
        startTiming();

        proxyed.move();

        stopTiming();
        postLogging();
    }
}

class Plane1Proxy implements Moveable1 {
    Plane1 proxyed;
    private long startMills, stopMills;

    private void preLogging() {
        System.out.println("start logging");
    }

    private void postLogging() {
        System.out.println("stop logging");
    }

    private void startTiming() {
        startMills = System.currentTimeMillis();
    }

    private void stopTiming() {
        stopMills = System.currentTimeMillis();
        System.out.println("total cost " + (stopMills - startMills) + "ms");
    }

    public void setProxyed(Plane1 proxyed) {
        this.proxyed = proxyed;
    }

    @Override
    public void move() {
        preLogging();
        startTiming();

        proxyed.move();

        stopTiming();
        postLogging();
    }
}

class MoveableProxy implements Moveable1{

    private Moveable1 moveable;

    private long startMills, stopMills;

    private void preLogging() {
        System.out.println("start logging");
    }

    private void postLogging() {
        System.out.println("stop logging");
    }

    private void startTiming() {
        startMills = System.currentTimeMillis();
    }

    private void stopTiming() {
        stopMills = System.currentTimeMillis();
        System.out.println("total cost " + (stopMills - startMills) + "ms");
    }

    public void setMoveable(Moveable1 moveable) {
        this.moveable = moveable;
    }

    @Override
    public void move() {
        preLogging();
        startTiming();

        moveable.move();

        stopTiming();
        postLogging();
    }
}

/**
 * 静态代理
 * 1.被代理对象和代理对象一起实现相同接口，无接口情况下，代理类可以继承被代理类；
 * 2.代理类聚合了被代理类，即持有被代理类对象引用（是具体类，而非抽象父类或接口）；
 * 3.代理类代替被代理类实现调用，底层实质还是调用了被代理对象的目标方法，只不过在调用前后添加了额外操作
 *
 * 缺点：代理类里面聚合的是具体实现类，需要为兄弟类各种编写代理类，出现类爆炸；
 *
 * 优化版：
 * 1.将代理类内部聚合的被代理类换成，父类或接口，一个相同代理类对象，需要代理谁，就将引用指向谁，不用编写多个代理类，不会引起类爆炸；
 * 2.已经与JDK动态代理非常类似，只不过生成代理类过程是手动完成，不是基于二进制字节码自动编写的；
 */
public class StaticProxy {

    public static void main(String[] args) {

    }

    /**
     * 纯粹静态代理
     * 代理谁，就持有谁，然后代替被代理者去执行业务，并夹带其他操作；
     * 类爆炸！！！
     */
    public static void proxy1(){
        Car1 car = new Car1();
        Car1Proxy car1Proxy = new Car1Proxy();
        car1Proxy.setProxyed(car);
        car1Proxy.move();

        Plane1 plane = new Plane1();
        Plane1Proxy plane1Proxy = new Plane1Proxy();
        plane1Proxy.setProxyed(plane);
        plane.move();
    }

    /**
     * 优化版：
     * 代理类内部不再持有具体被代理者的引用，而是持有被代理者的父类会接口，这样被代理者兄弟类，可以共用这有个代理类。
     * 不会产生类爆炸，与JDK动态代理非常相似，只不过代理类是手写，而不是基于字节码动态生成的。
     */
    public static void proxy2(){
        Moveable1 car = new Car1();
        Moveable1 plane = new Plane1();

        MoveableProxy proxy = new MoveableProxy();

        proxy.setMoveable(car);
        proxy.move();

        proxy.setMoveable(plane);
        proxy.move();
    }



}
