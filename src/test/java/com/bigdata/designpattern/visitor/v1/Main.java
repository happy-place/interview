package com.bigdata.designpattern.visitor.v1;

/**
 * 访问者设计模式：
 * 1.被访问者内部结构固定，如：商品品类、单价；
 * 2.对不同访问者，存在差异化访问动作，如：给到不同类型采购商折扣力度不同；
 * 3.将差异化的接待访问策略，定义搭配被访问者中，即被访问者为保持内部稳定性，不承担拓展变更复杂性，如：打折策略定义在采购商内部
 * 4.具体接待访问的过程，在被访问者内部元素中完成，如:：CPU、Memory、Board 在accept中，执行打折操作，但折扣力度定义在采购商内部；
 *
 * 一句话：不同访问者的vist()方法定义各自不同访问策略，但vist()的调用，在被访问者的accept()方法中完成；
 *
 */
public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();

        CorpVisitor corpVisitor = new CorpVisitor();
        computer.accept(corpVisitor);
        System.out.println(corpVisitor.totalPrice);

        PersonalVisitor personalVisitor = new PersonalVisitor();
        computer.accept(personalVisitor);
        System.out.println(personalVisitor.totalPrice);

    }
}
