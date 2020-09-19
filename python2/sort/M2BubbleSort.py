#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys

class M2BubbleSort:
    # 标准 冒泡排序
    # 1.外层为轮，内层为每轮循环次数；
    # 2.相邻元素比较，大的往后移
    # 时间复杂度 o(n^2) n x n*(n-1)/2，最坏o(n^2) 最好 o(n)
    # 空间复杂度 o(1)，水平线性
    @staticmethod
    def solve1(arr):
        size = len(arr)
        for i in range(size-1):
            for j in range(size-i-1):
                if arr[j]>arr[j+1]:
                    M2BubbleSort.swap(arr,j,j+1)

    # 优化后冒泡排序
    # 某轮循环中，如果监控到内层循环没有数据交换，则证明已经排好序，直接退出外层循环，此时对于最好情况，即数组本身是有序，
    # 时间复杂度 o(n) 只经历一次内层循环
    # 空间复杂度 o(1) 随数据集膨胀，额外空间需求无变化
    @staticmethod
    def solve2(arr):
        size = len(arr)
        for i in range(size-1):
            swaped = False
            for j in range(size-1-i):
                if arr[j]>arr[j+1]:
                    M2BubbleSort.swap(arr,j,j+1)
                    swaped = True
            if not swaped:
                break

    @staticmethod
    def swap(arr,i,j):
        arr[i],arr[j] = arr[j],arr[i]


