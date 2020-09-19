#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys
from copy import deepcopy
from random import randint
from M1SelectionSort import M1SelectionSort
from M2BubbleSort import M2BubbleSort
from M3InsertionSort import M3InsertionSort
from M4ShellSort import M4ShellSort
from M5MergeSort import M5MergeSort
from M6QuickSort import M6QuickSort
from M7CountSort import M7CountSort
from M8RadixSort import M8RadixSort
from M9BucketSort import M9BucketSort
from M10HeapSort import M10HeapSort
from test import heapSort


# C 嵌入式开发
# C++ 类库开发
# java 企业级应用、高并发后台
# php 中小型后台
# python 爬虫、数据分析、人工智能(调用C++类库)

# 对数器：
# 1.足够随机样本；2.已知标准方法和自定义方法分别处理样本和样本副本；3.处理次数足够多，每次处理完后，一一对比，如果结果仍旧一致，就可以判断问一致
def prepare(n):
    arr = []
    for i in range(n):
        arr.append(randint(-100,100))
        # arr.append(randint(0,n))
    return arr

def check(sort_func,n,times=3):
    for i in range(times):
        arr1 = prepare(n)
        arr2 = deepcopy(arr1)
        arr1.sort()
        sort_func(arr2)
        try:
            for i in range(n):
                if arr1[i]!=arr2[i]:
                    print(arr1)
                    print(arr2)
                    raise RuntimeError("arr1[%d]!=arr2[%d]"%(i,i))
        except Exception as e:
            print(e.message)
            break


if __name__ == "__main__":
    # check(M1SelectionSort.solve1,1000,10)
    # check(M2BubbleSort.solve1,1000,10)
    # check(M3InsertionSort.solve1,1000,10)
    # check(M4ShellSort.solve1,1000,10)
    # check(M5MergeSort.solve1,1000,10)
    # check(M6QuickSort.solve1,1000,10)
    # check(M7CountSort.solve1,10000,10)
    # check(M8RadixSort.solve1,10000,10)
    # check(M9BucketSort.solve1,10000,10)
    check(M10HeapSort.solve1,1000,100)
    # check(heapSort,10000,100)

    pass