#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys

class M3InsertionSort:
    # 选择排序
    # 1.外层循环为轮，内层循环为每轮的次
    # 2.每次比较时，外层循环对应目标元素i，依次与签名i-1 ~ 0 对比，发现比其大的就往2前移，这样本次循环过后签名 0~i 都是有序的
    # 3.每次新的目标元素 i 插入到前面 0~i-1 中合适位置
    # 时间复杂度 o(n^2)，最坏情况为 o(n^2), 最好情况o(n)
    # 空间复杂度 o(1) 随数据规模扩大，空间不变
    #
    # 稳定性：稳定 (相同元素，拍完序后，相对顺序不变)
    # 插入排序比冒泡快，冒泡每次命中时需要交换数据
    # 插入排序比选择排序快，前面地都有序情况下，直接过，选择还是需要交换
    @staticmethod
    def solve1(arr):
        for i in range(1,len(arr)):
            for j in range(i-1,-1,-1):
                if arr[j]>arr[j+1]:
                    M3InsertionSort.swap(arr,j,j+1)

    # 选择排序优化
    # 2.为运算代替交换
    # a = a ^ b
    # b = a ^ b
    # a = a ^ b
    @staticmethod
    def solve2(arr):
        for i in range(0,len(arr)):
            for j in range(i-1,-1,-1):
                if arr[j]>arr[j+1]:
                    M3InsertionSort.swap2(arr, j, j + 1)

    @staticmethod
    def swap(arr,i,j):
        temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp

    @staticmethod
    def swap2(arr, i, j):
        arr[j] = arr[j] ^ arr[j + 1]
        arr[j + 1] = arr[j] ^ arr[j + 1]
        arr[j] = arr[j] ^ arr[j + 1]


