package com.bigdata.designpattern.singleton;

/**
 * 静态内部类实现单例
 * 亮点：单例对象初始化，封装在静态内部类中，外部类加载时，静态内部类不会加载，所以也就不会初始化，获取实例时，使用到了静态内部类，此时才会加载它，随之初始化单例对象
 *
 */
public class InnerStaticClassHolderSingleton {

    private InnerStaticClassHolderSingleton() {}

    public static InnerStaticClassHolderSingleton getInstance(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return InstanceHolder.instance;
    }

    static class InstanceHolder{
        static InnerStaticClassHolderSingleton instance = new InnerStaticClassHolderSingleton();
    }

}
