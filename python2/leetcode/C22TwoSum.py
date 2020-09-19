#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/12 10:23
Info:

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两 个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
给定 nums = [2, 7, 11, 15], target = 9 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]

"""

import os, sys

# 暴力匹配
# 0~i逐个循环，i+1~len(arr) 挨个与前半匹配，直至成功过
# 时间复杂度 o(n^2), 双层循环
# 空间复杂度 o(1), 基于数组下标索引值，无额外空间开销
def solve1(arr,target):
    length = len(arr)
    for i in range(length-1):
        for j in range(i+1,length):
            if arr[i]+arr[j] == target:
                return [i,j]
    raise RuntimeError("no solution")

# 借助hash结构记住已经遍历过的数值和其索引
# 时间复杂度 o(n)，单层循环
# 空间复杂度 o(n), 借助hash存储已经遍历过的数据信息
def solve2(arr,target):
    temp = dict()
    for i in range(len(arr)):
        x = arr[i]
        y = target - x
        if y in temp:
            return (temp[y],i)
        temp[x] = i
    raise RuntimeError("no solution")

if __name__ == "__main__":
    arr = [3,2,3]
    target = 6
    solve_ = solve2(arr, target)
    print(solve_)
    pass    
    
