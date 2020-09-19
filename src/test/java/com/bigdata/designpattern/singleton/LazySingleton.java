package com.bigdata.designpattern.singleton;

/**
 * 懒汉相比于饿汉差异在于
 * 1.将唯一静态实例，初始化移到了静态方法中，多线程访问时，存在安全问题（重复创建），需要考虑上锁；
 * 2.唯一静态实例使用 volatile（限制指令重排序） 保证写操作发送在读之前；
 *每次修改volatile变量都会同步到主存中
 * 每次读取volatile变量的值都强制从主存读取最新的值(强制JVM不可优化volatile变量,如JVM优化后变量读取会使用cpu缓存而不从主存中读取)
 * 线程 A 中写入 volatile 变量之前可见的变量, 在线程 B 中读取该 volatile 变量以后, 线程 B 对其他在 A 中的可见变量也可见.
 * 换句话说, 写 volatile 类似于退出同步块, 而读取 volatile 类似于进入同步块
 *
 * volatile 能保证多线程之间共享变量的可见性，读写都发生在主存，但不能保证操作的原子性，即判断可以执行操作后，正准备操作时目标已经被修改了
 *
 * volatile并不能保证非源自性操作的多线程安全问题得到解决,volatile解决的是多线程间共享变量的可见性问题
 * 原因是i++和++i并非原子操作,我们若查看字节码,会发现
 */
public class LazySingleton {

    private static volatile LazySingleton instance =  null;

    private LazySingleton(){}

    /**
     * 只将初始化移到静态方法中，不采取上锁操作，存在线程安全问题
     * @return
     */
    public static LazySingleton getInstance0(){
        if(null==instance){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazySingleton();
        }
        return instance;
    }

    /**
     * 懒汉1：
     * 静态同步方法中获取 唯一实例，且此实例，在静态
     * @return
     */
    public static synchronized LazySingleton getInstance1(){
        if(null==instance){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazySingleton();
        }
        return instance;
    }

    /**
     * 最标准懒汉
     * 懒汉2：
     * 静态同步方法，锁定粒度太大，效率不高，使用静态代码块代替，但需要使用双重检锁机制，在静态代码块内外，各检查一次实例是否已经存在
     * @return
     */
    public static LazySingleton getInstance2(){
        if(null==instance){
            synchronized (LazySingleton.class){
                if(null==instance){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }


}
