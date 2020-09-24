package com.bigdata.designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class Car3 {
    public void move() {
        System.out.println("wu~~~");
    }
}

class Plane3 {

    String name;

    public Plane3(String name) {
        this.name = name;
    }

    public void move() {
        System.out.println(name+": cua~~~");
    }
}

class MyMethodInterceptor implements MethodInterceptor{
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

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        startTiming();
        preLogging();

        Object result = methodProxy.invokeSuper(o, objects); // methodProxy 代理对象的目标方法

        postLogging();
        stopTiming();
        return result;
    }
}

/**
 * CGlib 动态代理
 * 1.直接继承被代理类，因此被代理类不能被 final 修饰；
 * 2.创建Enhancer实例 -> 注册通过setSuperClass 注册被代理类 -> setCallback 设置包含编织代码的回调对象MethodInterceptor -> 然后调用create() 创建被代理对象 ->发起调用；
 * 3.缺点就是1中提到的，基于继承实现，被代理类不能是 final 类，但使用 instrument 可以直接拷贝被代理类的属性和方法，生成一个类似的普通类，然后突破final限制，实现动态代理
 *
 */
public class CGLibDynamicProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        MyMethodInterceptor interceptor = new MyMethodInterceptor();
        enhancer.setCallback(interceptor);

        enhancer.setSuperclass(Car3.class);
        Car3 car = (Car3)enhancer.create(); // 空参构造器
        car.move();

        enhancer.setSuperclass(Plane3.class);
        // create(Class[] argumentTypes, Object[] arguments)
        Plane3 plane = (Plane3)enhancer.create(new Class[]{String.class},new Object[]{"Ha"}); // 参数构造器
        plane.move();


    }

}
