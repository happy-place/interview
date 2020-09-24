package com.bigdata.designpattern.memento;

/**
 * 备忘录设计模式
 * 1.记录状态，方便场景重现，通常需要配合序列化使用；
 * 2.java自带序列化协议（ObjectInputStream，ObjectOutputStream）附带过多类型描述信息，使用google开源Protobuf进行序列化，更精简；
 * 3.凡是需要被序列化的对象都要集成Serializable类，不需要包含到序列化中的结构使用 transient修饰；
 * 4.复杂对象序列化方案有两种：
 *      方案1：序列化属性，save()顺序存盘，load()顺序读取，然后重新组装出复杂对象；
 *      方案2：直接让该复杂对象实现序列化解决，对整体进行存盘和度盘操作。
 *
 * 注：备忘录模式与命令逐级回撤不同，是一步到位的复原。
 * 常用在游戏开发场景
 *
 */