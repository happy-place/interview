package com.bigdata.designpattern.state.v1;

public class MMSadState extends MMState{
    @Override
    void simle() {
        System.out.println("sad simle");
    }

    @Override
    void cry() {
        System.out.println("sad cry");
    }

    @Override
    void say() {
        System.out.println("sad say");
    }
}
