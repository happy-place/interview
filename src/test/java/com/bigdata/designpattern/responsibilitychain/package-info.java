package com.bigdata.designpattern.responsibilitychain;

/**
 *
 * 责任链模式，Chain Of Responsitility 仅次于 proxy 代理模式
 * 适合：
 * 1.分段加工处理；
 * 2.通过嵌套调用，实现栈式处理过程(先处理一半，往下游传递，然后在响应过程，再处理另外一半)；
 * 3.中间任意一环可以叫停链式操作
 */