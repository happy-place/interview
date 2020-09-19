#!/usr/bin/env python
# -*- coding: utf-8 -*-

from math import pow,sqrt,floor

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/12 10:50
Info:
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
注意：给定 n 是一个正整数。
示例 1：
输入： 1 输出： 1
解释： 有一种方法可以爬到楼顶。
1. 1 阶

示例 2：
输入： 2 输出： 2
解释： 有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶

示例 3：
输入： 3 输出： 3
解释： 有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶

思路：
n级解法在n-1基础上+1，在n-2基础上+2
f(n) = f(n-1)+1
f(n) = f(n-2)+2
f(n) = f(n-1) + f(n-2)


"""

import os, sys

# 递归法(缺点，重复计算很多)
# 时间复杂度 o(logn)
# 空间复杂度 o(1)
def solve1(n):
    if n==1 or n == 2:
        return n
    else:
        return solve2(n-1) + solve2(n-2)
    # 三木表达式
    # return n if n==1 or n == 2 else solve2(n-1) + solve2(n-2)

# 记忆递归
# 大数递归依赖小数递归，因此小数递归时，顺便保存状态，可以在大树递归时复用，节省时间开销
# 时间复杂度
# 空间复杂度 o(n)
def solve2(n,mem=None):
    if not mem:
        # 首次调用一次性申请 n+1 空间
        mem = [-1 for _ in range(n+1)]
    if n in (1,2):
        # 递归收敛条件
        return n
    else:
        # 实际递归操作，下标代表台阶数，下标存储值代表解决方案，mem 为共享记忆空间
        mem[n] = solve2(n-1,mem) + solve2(n-2,mem)
        return mem[n]


# 递推代替递归(n级在n-i级基础上构建起来)，又称动态规划
# 时间复杂度 o(n),单次循环
# 空间复杂度 o(n),使用数组辅助
def solve3(n):
    temp = [1,2]
    for i in range(2,n):
        temp.append(temp[i-1] + temp[i-2])
    return temp[n-1]

# 动态规划状态压缩，即相比于slove3只考虑申请两个临时空间存储最近前两次值，拼接得到当前值，然后完成顺序滚动赋值操作
# 时间复杂度为 o(n) 挨个遍历
# 优化动态规划空间复杂度 o(1)
def solve4(n):
    if n in (1,2):
        return n
    before2 = 1
    before1 = 2
    temp = None
    for i in range(2,n):
        temp = before2 + before1
        before2 = before1
        before1 = temp
    return temp


# TODO 指的继续挖掘通项公式推导过程
# 借助 斐波那契通项公式 fn = (1/√5) x (((1+√5)/2)^n+1 - ((1-√5)/2)^n+1)
# https://zhuanlan.zhihu.com/p/26679684
def solve5(n):
    if n==0:
        return 0
    if n==1 or n==2:
        return n
    return int(floor(1*(pow((1+sqrt(5))/2,n+1) - pow((1-sqrt(5))/2,n+1))/sqrt(5)))

if __name__ == "__main__":
    # solve_ = solve1(4)
    # solve_ = solve2(4)
    # solve_ = solve3(4)
    # solve_ = solve4(5)
    solve_ = solve5(4)
    print(solve_)

    
