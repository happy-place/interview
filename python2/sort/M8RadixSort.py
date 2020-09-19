#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys
from math import *

# 基数排序 同计数排序一样也属于桶排序思想
#
# 1.计数排序 桶个数 = 数据UV，时间复杂度 o(n+k) ，空间复杂度 o(n+k) ，n为元素个数，k为桶数，稳定排序，适合数据量大，分部范围小 场景
# 2.基数排序 桶个数 = 十进制可取数(10)，依次将数据按 取个位、十位、百位 上数值，归档搭配计数器数组中，然后将计数器数组从左往右累加，逆序遍历原数组，存储到对应结果数组，并将计数器-1
#      时间复杂度 o(n*k) 空间复杂度 o(n+k) 稳定排序，速度快于比较排序，如快排；
# 3.属于关键字排序一种，遵循低位优先元组
class M8RadixSort:

    @staticmethod
    def solve1(arr):
        min,max = M8RadixSort.get_boundary(arr)
        adjusted_max = M8RadixSort.adjustfy_negative(arr, min, max)
        M8RadixSort.radix_sort(arr,adjusted_max)
        M8RadixSort.reappear(arr,min)

    @staticmethod
    def radix_sort(arr,max):
        result = [0]*len(arr)
        counts = [0]*10
        max_radix = M8RadixSort.get_max_radix(max)
        for i in range(max_radix+1):
            for j in range(len(arr)):
                count_idx = M8RadixSort.get_radix_num(arr[j], i)
                counts[count_idx] +=1

            for m in range(1,len(counts)):
                counts[m] += counts[m-1]

            for n in range(len(arr)-1,-1,-1):
                count_idx = M8RadixSort.get_radix_num(arr[n], i)
                counts[count_idx] -= 1
                result[counts[count_idx]] = arr[n]

            # 注：此处必须用拷贝，不能赋值，否则引用传递就失效
            # arr = result
            M8RadixSort.copy_array(result,arr)
            counts = [0] * 10

    @staticmethod
    def copy_array(source,dest):
        for i in range(len(dest)):
            dest[i] = source[i]

    @staticmethod
    def get_radix_num(num,radix):
        div = pow(10,radix)
        return int(num/div%10)

    # 获取指定数值，最高位，对应的位数
    @staticmethod
    def get_max_radix(num):
        return int(log(num,10))

    # 排序完毕，如果之前调整过负数，现在需要调整回去
    @staticmethod
    def reappear(arr,min):
        if min < 0:
            addition = abs(min)
            for i in range(len(arr)):
                arr[i] -= addition

    # 存在负数时，都加上 abs(min) 全部转为非负数
    @staticmethod
    def adjustfy_negative(arr,min,max):
        if min<0:
            addition = abs(min)
            for i in range(len(arr)):
                arr[i] += addition
            max += addition
        return max

    # 找数字最大、最小边界
    @staticmethod
    def get_boundary(arr):
        min_pos = 0
        max_pos = 0
        for i in range(len(arr)):
            if arr[i]<arr[min_pos]:
                min_pos = i
            if arr[i]>arr[max_pos]:
                max_pos = i
        return [arr[min_pos],arr[max_pos]]


def test_get_boundary():
    arr = [-10,-9,3,2,5,10,20]
    min,max = M8RadixSort.get_boundary(arr)
    print("%s min=%d, max=%d"%(arr,min,max))

def adjustfy_negative():
    arr = [-10, -9, 3, 2, 5, 10, 20]
    min, max = M8RadixSort.get_boundary(arr)
    adjusted_max = M8RadixSort.adjustfy_negative(arr, min, max)
    print(arr)
    print(adjusted_max)

def test_get_radix_num():
    num = 431
    radix_num = M8RadixSort.get_radix_num(num, 0)
    print(radix_num)
    radix_num = M8RadixSort.get_radix_num(num,1)
    print(radix_num)
    radix_num = M8RadixSort.get_radix_num(num,2)
    print(radix_num)

def test_get_max_radix():
    max_radix = M8RadixSort.get_max_radix(1)
    print(max_radix)
    max_radix = M8RadixSort.get_max_radix(12)
    print(max_radix)

def test_solve():
    arr = [-10, -9, 3, 2, 5, 10, 20]
    print(arr)
    M8RadixSort.solve1(arr)
    print(arr)

if __name__ == "__main__":
    # test_get_boundary()
    # adjustfy_negative()
    # test_get_radix_num()
    # test_get_max_radix()
    test_solve()