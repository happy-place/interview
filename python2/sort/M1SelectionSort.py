#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys


class M1SelectionSort:

    # 外层控制循环轮数，外层控制循环次数，次数随轮数收敛
    # 每轮筛选出对应轮数的极值，往前排，，下轮循环起点对应后移
    # 时间复杂度 o(n^2) 双层循环
    # 空间复杂度 o(1) 只申请了一个额外空间
    # 不稳定排序：相同元素2,2经过排序后顺序会倒置，理论上稳定排序应该是平移关系
    @staticmethod
    def solve1(arr):
        print(arr)
        size = len(arr)
        min_pos = 0
        for i in range(size-1):
            for j in range(i,size):
                if arr[min_pos] > arr[j]:
                    min_pos = j
            M1SelectionSort.swap(arr,i,min_pos)
        print(arr)


    # 选择排序优化解(高低位排序)
    # 1.外层循环为轮数，内层循环为每轮次数
    # 2.每轮循环同时找最大值，最最小值，当前轮循环次数2倍速度收敛
    # 3.调换元素时，先调换者可能对后调换者有扰动，需要通过分支判断
    # 时间复杂度 o(n^2)
    # 空间复杂度 o(2)
    # 稳定性：排序过程相同元素相对顺序可能颠倒，不稳定
    # 计算时间复杂度时初始化语句和打印语句，开销不考虑，重点关注递归和循环，忽略常数项、低次项和系数
    @staticmethod
    def solve2(arr):
        print(arr)
        size = len(arr)
        loop = (size+1)/2
        for i in range(loop):
            min_pos = i
            max_pos = size-1-i
            if arr[max_pos]<arr[min_pos]:
                M1SelectionSort.swap(arr,min_pos,max_pos)
            for j in range(i+1,size-1-i):
                if arr[min_pos]>arr[j]:
                    min_pos = j
                if arr[max_pos]<arr[j]:
                    max_pos = j
            M1SelectionSort.swap(arr,i,min_pos)
            if max_pos == i:
                M1SelectionSort.swap(arr,size-1-i,min_pos)
            else:
                M1SelectionSort.swap(arr, size - 1 - i, max_pos)
        print(arr)

    @staticmethod
    def swap(arr,i,j):
        arr[i],arr[j] = arr[j],arr[i]


