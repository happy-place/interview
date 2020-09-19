package com.bigdata.designpattern.observer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// 事件 通常使用枚举形式，需要包含 getSource ，setSource 标定事件源，方便监听者分而治之
enum Event {
    CRY, LAUGH, WANG;

    private EventSource source;

    public EventSource getSource() {
        return this.source;
    }

    public void setSource(EventSource source) {
        this.source = source;
    }
}

// 监听器接口，需要包含处理方法
interface EventListener {
    void eventObserve(Event event);
}

class Dad implements EventListener {

    @Override
    public void eventObserve(Event event) {
        String eventName = event.getSource().getClass().getSimpleName();
        switch (event) {
            case CRY:
                System.out.println("> "+eventName+" CRY");
                System.out.println("< Oh, let me feed you.");
                break;
            case LAUGH:
                System.out.println("> "+eventName+" LAUGH");
                System.out.println("< Oh, you like it.");
                break;
            case WANG:
                System.out.println("> "+eventName+" WANG");
                System.out.println("< Xu~, baby is sleeping...");
                break;
        }
    }
}

class Mom implements EventListener {

    @Override
    public void eventObserve(Event event) {
        String eventName = event.getSource().getClass().getSimpleName();
        switch (event) {
            case CRY:
                System.out.println("> "+eventName+" CRY");
                System.out.println("< Oh, baby what`s happening.");
                break;
            case LAUGH:
                System.out.println("> "+eventName+" LAUGH");
                System.out.println("< Oh, mom love you honey.");
                break;
            case WANG:
                System.out.println("> "+eventName+" WANG");
                System.out.println("< Hungry ?");
                break;
        }
    }
}

// 事件源
class EventSource {
    // 监听器列表
    private List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener){
        listeners.add(listener);
    }

    public void fireEvent(Event event) {
        event.setSource(this);
        for (EventListener listener : listeners) {
            listener.eventObserve(event);
        }
    }
}

class Child extends EventSource {

    public void cry() {
        fireEvent(Event.CRY);
    }

    public void laugh() {
        fireEvent(Event.LAUGH);
    }

}

class Dog extends EventSource {

    public void wang() {
        fireEvent(Event.WANG);
    }

}

/**
 * 1.被监听者拥有一个或多个监听器；
 * 2.监听器同时监听一个或多个被监听者；
 * 3.被监听者和监听者之间传递的事件，需要能够溯源
 *
 * 监听者 与 被监听者之间交互 基于事件进行解耦
 */
public class Observer02 {

    @Test
    public void test() {
        // 初始化监听器
        Dad dad = new Dad();
        Mom mom = new Mom();

        // 初始化被监听者
        Child child = new Child();

        // 被监听者注册监听器
        child.addListener(dad);
        child.addListener(mom);

        // 产生事件
        child.cry();
        child.laugh();

        // 初始化被监听者（事件源）
        Dog dog = new Dog();

        // 被监听者注册监听器
        dog.addListener(dad);
        dog.addListener(mom);

        // 产生事件
        dog.wang();
    }

}
