package com.bigdata.designpattern.command;

/**
 * 命令设计模式
 * 操作打包成命令，可以执行，也可以撤销；
 *
 * 1.命令的 do 和 undo 功能；
 * 2.与责任链模式搭配实现命令连续回退功能；
 * 3.与组合设计模式搭配实现宏 (树状结构，封装了一系列其他宏或命令)
 * 4.实现事务Translaction的提交和回滚功能
 *
 */
