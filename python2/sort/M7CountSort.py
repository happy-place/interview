#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys

# 计数排序，桶排序思想简单表现形式
# 适合：数据量大，数据范围小（分布集中）场景
# 1.查找数组最小、最大边界值
# 2.创建 max - min + 1 长度大小 计数器数组，数组下标对应 元素与最小值差值，存储数据对应相同元素个数 （好比吧相同元素以计数方式压缩在一个下标位上）；
# 3.直接在原数组上顺序，重复对应计数形式，回放计数器数组，实现排序
# 时间复杂度 o(n+k) n 为原数组长度，k 为取值范围
# 空间复杂度 o(n+k) 原数组和取值数组同时存在
# 稳定性：稳定
class M7CountSort:

    @staticmethod
    def solve1(arr):
        min_value,max_value = M7CountSort.find_bounds(arr)
        counts = [0]*(max_value-min_value+1)
        for e in arr:
            counts[e-min_value]+=1
        m = 0
        for i in range(len(counts)):
            cnt = counts[i]
            for j in range(cnt):
                arr[m]= i + min_value
                m += 1

    @staticmethod
    def find_bounds(arr):
        min_pos = 0
        max_pos = len(arr)-1
        for i in range(len(arr)):
            if arr[i] < arr[min_pos]:
                min_pos = i
            if arr[i] >arr[max_pos]:
                max_pos = i
        return arr[min_pos],arr[max_pos]

