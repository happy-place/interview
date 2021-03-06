package com.bigdata.designpattern.strategy;

/**
 * 策略模式：
 * 1.同类不同种操作，抽象公共接口，然后分别实现各自逻辑，函数入参面向接口，保证开闭原则;
 * 2.操作(operate)与操作者(operator)解除耦合；
 *
 * 示例1：坦克大战中坦克是开火动作的操作者，开火是公共接口，发射一颗子弹，还是两个是开火接口的具体实现类；
 *
 *
 */