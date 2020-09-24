package com.bigdata.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Moveable2 {
    void move();
}

class Car2 implements Moveable2 {
    @Override
    public void move() {
        System.out.println("wu~~~");
    }
}

class Plane2 implements Moveable2 {
    @Override
    public void move() {
        System.out.println("cua~~~");
    }
}

/**
 * JDK动态代理
 * 1.基于反射实现，即直接通过类加载器获取类的属性和方法信息，而不需要了解源码；
 * 2.代理类与被代理类直接的继承关系，是JDK动态生成的，不体现在手动编写代码中，具体是通过asm实现（可直接编辑二进制字节码的类库）；
 * 3.由于asm的存在，导致java也具备动态语言特性，即使用类同时可以修改类，并且立即生效；
 * 4.同静态代理一样，代理谁，就持有谁，只不过这里持有的是被代理类的接口；
 *
 * 缺点：被代理类必须实现了接口，才能使用。
 *
 * 调用Proxy.newProxyInstance()，分别传入被代理类的类加载器、可能使用到的被代理类的父类、封装代理编织代码的InvocationHander实例，
 * InvocationHander类中聚合了被代理类，通过set方式注入，
 * 然后发起调用
 */
class MoveableInvocationHandler implements InvocationHandler{

    private Moveable2 moveable;

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

    public void setMoveable(Moveable2 moveable) {
        this.moveable = moveable;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // proxy 是代理类自身, 不是被代理对象
        preLogging();
        startTiming();

        Object result = method.invoke(this.moveable,args);

        stopTiming();
        postLogging();

        return result;
    }
}

/**
 * JDK动态代理
 * 被代理对象
 */
public class JDKDynamicProxy {

    public static void main(String[] args) {
        // 保存JDK动态代理自动生成的代理类对象字节码文件 $Proxy0.class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        MoveableInvocationHandler handler = new MoveableInvocationHandler();

        Moveable2 car = new Car2();
        handler.setMoveable(car);
        Moveable2 movable = (Moveable2) Proxy.newProxyInstance(
                Moveable2.class.getClassLoader(), // 类加载器
                Car2.class.getInterfaces(), // 被代理类可能存在多个父类或接口，这里只传入需要用到的父类数组即可 new Class[]{Car2.class}
                handler // 处理器封装了目标方法调用，与增强函数的编织过程
        );
        movable.move();

        Moveable2 plane = new Plane2();
        handler.setMoveable(plane);
        movable = (Moveable2) Proxy.newProxyInstance(
                Moveable2.class.getClassLoader(),
                Car2.class.getInterfaces(),
                handler
        );
        movable.move();

    }


}
