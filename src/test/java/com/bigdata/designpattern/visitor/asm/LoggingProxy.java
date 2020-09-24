package com.bigdata.designpattern.visitor.asm;

public class LoggingProxy{

    private long startMills, stopMills;

    private static void logging() {
        System.out.println("start logging");
    }

}