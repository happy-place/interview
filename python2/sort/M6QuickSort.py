#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys

# 递归分为n层，每层分为 log(2,n) 块
# 时间复杂度 o(nlog(2,n)) 最差为 o(n^2) 排好序，每层pivot已经在边界，最好 o(nlog(2,n))
# 空间复杂度 o(log(2,n)) 每层有 log(2,n) 个小数组
# 稳定性：稳定（相同元素排序后，相对位置不变）
class M6QuickSort:
    @staticmethod
    def solve1(arr):
        M6QuickSort.partition(arr,0,len(arr)-1)

    @staticmethod
    def partition(arr,left,right):
        if left>= right:
            return
        mid = M6QuickSort.pivot_pos(arr,left,right)
        M6QuickSort.partition(arr,left,mid-1)
        M6QuickSort.partition(arr,mid+1,right)

    @staticmethod
    def pivot_pos(arr,left,right):
        i = left
        j = right-1
        pivot = arr[right]
        while(i<=j):
            while(i<=j and arr[i]<=pivot):
                i +=1
            while(i<=j and arr[j]>pivot):
                j -=1
            if i<j:
                M6QuickSort.swap(arr,i,j)
        M6QuickSort.swap(arr,i,right)
        return i

    @staticmethod
    def swap(arr,i,j):
        arr[i],arr[j] = arr[j],arr[i]

