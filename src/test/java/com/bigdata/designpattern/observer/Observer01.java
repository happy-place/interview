package com.bigdata.designpattern.observer;

import org.junit.Test;

public class Observer01 {

    @Test
    public void test(){
        child();
    }

    // 观察源（产生事件，同时拥有多个观察者，事件触发观察者相应操作）
    public void child(){
        String msg = "cry";
        dad(msg);
        mom(msg);
        dog(msg);
    }

    public void dad(String msg){
        System.out.println("Oh, let me feed U.");
    }

    public void mom(String msg){
        System.out.println("Oh, baby let me see what`s happening.");
    }

    public void dog(String msg){
        System.out.println("Wa~wa~wa.");
    }

}
