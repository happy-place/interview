package com.bigdata.source.juc;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

enum ReadyToRun {
    T1, T2
}

/**
 * 两个线程交替打印
 * 线程1：A        B       C       D
 * 线程2：     1       2       3       4
 * <p>
 * 要诀：
 * 1.先行线程，先打印、后唤醒对方，然后自己阻塞；
 * 2.后行线程，先阻塞、后打印、然后唤醒对方
 */
public class A1B2C3 {

    static Thread t1, t2 = null;
    static char[] tasks1 = "ABCD".toCharArray();
    static char[] tasks2 = "123".toCharArray();

    /**
     * 方案1 使用 LockSuppert 的 part 和 unpark
     * 效率最高
     */
    @Test
    public void solve1() {
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char ch : tasks1) {
                    // 先打印，再唤醒对方，然后阻塞自身
                    System.out.print(ch);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char ch : tasks2) {
                    // 先阻塞，等待被唤醒时再打印，然后唤醒对方，接着自身又进入阻塞
                    LockSupport.park();
                    System.out.print(ch);
                    LockSupport.unpark(t1);
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        lazyStop(1000);
    }

    /**
     * 方案2：volatile 枚举类 + 自旋锁
     * 1.volatile 共享变量内存可见性
     * 2.枚举保证，共享变量可选状态是固定的
     * 3.自旋锁，在用户空间实现锁机制，相比于synchronized在内核级别上锁，更轻
     */
    // volatile 写优先于读，每次直接取原对象，而不是副本
    static volatile ReadyToRun readyToRun = ReadyToRun.T1;

    @Test
    public void test2() {

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char ch : tasks1) {
                    // 自旋锁：不是自身时就空转
                    while (readyToRun != ReadyToRun.T1) {
                    }
                    System.out.print(ch);
                    readyToRun = ReadyToRun.T2;
                }
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char ch : tasks2) {
                    while (readyToRun != ReadyToRun.T2) {
                    }
                    System.out.print(ch);
                    readyToRun = ReadyToRun.T1;
                }
            }
        });

        t1.start();
        t2.start();

        lazyStop(1000);
    }

    /**
     * 方案3：使用 synchronized 同步锁+ wait notify 线程唤醒机制
     * 3_1 能实现线程交替打印，但不能保证A先与1打印
     */
    @Test
    public void solve3_1() {
        Object lock = new Object();
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 普通代码块上包裹同步锁时，代码执行需要拿到锁同时，还需要具备CPU执行权
                synchronized (lock) {
                    for (char ch : tasks1) {
                        System.out.print(ch);
                        lock.notify(); // 通知其他想拥有lock的线程结束阻塞 (相当于释放锁)
                        try {
                            lock.wait(); // 自身线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify(); // 必须要有，否则对方无法退出
                }
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (char ch : tasks2) {
                        System.out.print(ch);
                        lock.notify(); // 通知其他想拥有lock的线程结束阻塞
                        try {
                            lock.wait(); // 自身线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify(); // 必须要有，否则对方无法退出
                }
            }
        });

        t1.start();
        t2.start();

        lazyStop(1000);
    }

    /**
     * 3_2 在 3_1 基础上，在线程2中添加自旋锁，确保先执行线程1优先执行，线程1首次执行完毕，修改共享变量状态，保证线程2自旋锁能是否
     * 此处使用的是 枚举类型volatile变量，使用布尔类型volatile变量亦可
     */
    @Test
    public void solve3_2() {
        Object lock = new Object();
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (char ch : tasks1) {
                        System.out.print(ch);
                        readyToRun=ReadyToRun.T2;
                        lock.notify(); // 通知其他想拥有lock的线程结束阻塞
                        try {
                            lock.wait(); // 自身线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify(); // 必须要有，否则对方无法退出
                }
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while(readyToRun!=ReadyToRun.T2){
                        try {
                            lock.wait(); // 自身线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (char ch : tasks2) {
                        System.out.print(ch);
                        lock.notify(); // 通知其他想拥有lock的线程结束阻塞
                        try {
                            lock.wait(); // 自身线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify(); // 必须要有，否则对方无法退出
                }
            }
        });

        t1.start();
        t2.start();

        lazyStop(1000);
    }

    /**
     * 参照3_2 readToRun 控制谁先执行，使用 CountDownLatch ，在线程2先抢到执行权时，先await，释放执行权，线程1获取锁后，打印输出，然后latch计数
     */


    @Test
    public void solve3_3() {
        Object lock = new Object();
        CountDownLatch latch = new CountDownLatch(1);
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    // 计数器-1，此时线程2 await出被释放
                    latch.countDown();
                    for (char ch : tasks1) {
                        System.out.print(ch);
                        lock.notify(); // 通知其他想拥有lock的线程结束阻塞
                        try {
                            lock.wait(); // 自身线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify(); // 必须要有，否则对方无法退出
                }
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    // 线程2首发时，先阻塞，是否执行权，等到 latch 减为0时，在唤醒执行
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (char ch : tasks2) {
                        System.out.print(ch);
                        lock.notify(); // 通知其他想拥有lock的线程结束阻塞
                        try {
                            lock.wait(); // 自身线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify(); // 必须要有，否则对方无法退出
                }
            }
        });

        t1.start();
        t2.start();

        lazyStop(1000);
    }

    /**
     * ReentrantLock 可重入锁+CountDownLatch
     * ReentrantLock 必须在try中上锁，finally中释放锁
     * CountDownLatch 保证线程1优先执行
     */
    @Test
    public void solve4_1(){
        // 可重入锁（可以多次加锁）
        ReentrantLock lock = new ReentrantLock();
        // 信号通量（等待锁线程唤醒，当前线程阻塞）
        Condition condition = lock.newCondition();
        // 闭锁，保证线程执行优先级
        CountDownLatch latch = new CountDownLatch(1);
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock(); // 必须在try中上锁
                    latch.countDown(); // 如果线程2优先执行，会先进入阻塞，然后被此处释放
                    for(char ch:tasks1){
                        System.out.print(ch);
                        condition.signal(); // 唤醒等待lock的其他阻塞线程
                        try {
                            condition.await(); // 当前线程阻塞，释放CPU执行权
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    condition.signal(); // 当前线程执行完毕，退出时需要唤醒其他等待锁的线程
                } finally {
                    lock.unlock(); // 必须在finally中释放锁
                }
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    try {
                        // 线程2如果首先抢到锁，则先阻塞，并释放执行权，latch归0，在从此次唤醒，继续向下执行
                        // 如果线程1先执行，则 await 什么不做，继续向下执行
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for(char ch:tasks2) {
                        System.out.print(ch);
                        condition.signal();
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();

    }

    /**
     * 在4_1基础上，使用两个Condition 分别管理线程1，线程2
     * synchronized 实质维护的是一个线程执行队列，队列中的任务，谁先抢到锁，谁就执行
     * ReentrantLock 可以维护多个任务队列，一个Condition控制一个队列，从而更细粒度保证线程唤醒机制
     * 如多生产者、消费者场景，将生产者使用一个Condition管理，消费者使用一个Condition管理，触发边界条件时，分而治之，有针对性唤醒，synchronized 一旦唤醒，就全部唤醒了。
     */
    @Test
    public void solve4_2(){
        // 可重入锁
        Lock lock = new ReentrantLock();
        //
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        CountDownLatch latch = new CountDownLatch(1);

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    lock.lock();
                    latch.countDown();
                    for(char ch:tasks1){
                        System.out.print(ch);
                        c2.signal();
                        try {
                            c1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    c2.signal();
                }finally {
                    lock.unlock();
                }
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    lock.lock();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for(char ch:tasks2){
                        System.out.print(ch);
                        c1.signal();
                        try {
                            c2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    c1.signal();
                }finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();
        lazyStop(1000);
    }

    /**
     * TransferQueue 容量为0传输队列
     * 发送者成功发送一个后阻塞，直至消费者取走消息，也就是线程1下发的任务，被线程2执行，线程2下发任务被线程1执行，从而实现交替打印效果
     * 缺点：线程1，2打印元素个数相等
     *
     */
    @Test
    public void solve5(){
        TransferQueue<Character> queue = new LinkedTransferQueue<>();
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(char ch:tasks1){
                    try {
                        queue.transfer(ch);
                        System.out.print(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(char ch:tasks2){
                    try {
                        System.out.print(queue.take());
                        queue.transfer(ch);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        lazyStop(1000);
    }




    public void lazyStop(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
