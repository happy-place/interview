package com.bigdata.designpattern.singleton;

/**
 * 饿汉单例
 * 1.私有化构造器
 * 2.将唯一实例设置为静态类型，并且在声明时，就进行初始化，静态加载机制，保证只会被创建一次
 * 3.使用静态方法获取此静态实例；
 *
 * 优点：简洁明了、使用最广
 * 缺点：不支持懒加载（吃毛求疵）
 *
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        // 测试多线程安全
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
