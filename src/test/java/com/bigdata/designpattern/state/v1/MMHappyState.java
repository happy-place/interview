package com.bigdata.designpattern.state.v1;

public class MMHappyState  extends MMState{

    @Override
    void simle() {
        System.out.println("happy simle");
    }

    @Override
    void cry() {
        System.out.println("happy cry");
    }

    @Override
    void say() {
        System.out.println("happy say");
    }
}
