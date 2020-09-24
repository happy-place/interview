package com.bigdata.designpattern.iterator;

/**
 * 迭代器设计模式
 * 1.计算机存储数据的物理容器实质只有顺序存储(Array)和跳跃存储(LinkedList)两类，称为为物理存储，其余所有数据结构都称为逻辑结构；
 * 2.迭代器是集合容器的重要组成，在遍历元素时使用到；
 * 3.容器设计主要需要包含以下几点：
 *      实质存储结构 数组 or 链表
 *      记录存储元素个数遍历 size
 *      添加 add() 或 移除 remove() 元素的操作
 *      遍历元素的迭代器，可以直接实现Iterator接口，或使用内部类实现Iterator接口
 *
 * v1: 模拟顺序存储和跳跃存储
 * v2: 抽象公共Collection接口，保证Collection的引用，同时具备两种选择
 * v3: 实现Iterable迭代器协议
 * v4: 容器泛型支持
 *
 */