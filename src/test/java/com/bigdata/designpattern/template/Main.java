package com.bigdata.designpattern.template;


abstract class Kitchen{

    // 控制流程的方法，在父类已经实现
    public void cook(){
        prepare();
        fire();
        close();
    }

    // 具体执行流程，保持抽象，方便不同子类拓展
    abstract void prepare();
    abstract void fire();
    abstract void close();

}

class ChineseKitchen extends Kitchen{
    @Override
    void prepare() {
        System.out.println("rice");
    }

    @Override
    void fire() {
        System.out.println("match");
    }

    @Override
    void close() {
        System.out.println("~~");
    }
}

class USKitchen extends Kitchen{
    @Override
    void prepare() {
        System.out.println("beef");
    }

    @Override
    void fire() {
        System.out.println("fire stone");
    }

    @Override
    void close() {
        System.out.println("!!!");
    }
}



public class Main {

    public static void main(String[] args) {
        Kitchen chineseKitchen = new ChineseKitchen();
        chineseKitchen.cook();

        Kitchen usKitchen = new USKitchen();
        usKitchen.cook();
    }

}
