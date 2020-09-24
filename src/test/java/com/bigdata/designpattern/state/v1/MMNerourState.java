package com.bigdata.designpattern.state.v1;

public class MMNerourState  extends MMState{
    @Override
    void simle() {
        System.out.println("nervour simle");
    }

    @Override
    void cry() {
        System.out.println("nervour cry");
    }

    @Override
    void say() {
        System.out.println("nervour say");
    }
}
