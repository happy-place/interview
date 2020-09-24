package com.bigdata.designpattern.adapter;
/**
 * 适配器设计模式
 * 沟通两个不能直接通信的组件时使用，
 * 如：java 字节流转字符流 InputStreamReader、字符流转字节流 OutStreamWriter，
 * jdbc-odbc-bridge.jar jdbc访问微软SQL-Server数据库需要用到驱动转换器。
 *
 * 注：awt包中 WindowAdapter、KeyAdapter 都不是真正的Adapter设计模式，而是在原因WindowListener基础上，
 * 添加空实现层WindowAdapter，方便使用时， 只针对特定方法进行拓展，避免重写太多抽象方法；
 *
 */