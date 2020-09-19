#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys

class M5MergeSort:
    # 归并排序
    # 1.数组以递归方式拆分为两半，然后逐渐细分为只有两个元素；
    # 2.合并两个对称数组，在合并过程实现排序
    # 时间复杂度 o(nlog(2,n))，最好和最坏都是 o(n)
    # 空间复杂度 o(n) 需要申请中间数组
    # python  java 对象排序使用的就是优化过后的归并排序 TimSort 多路归并排序(直接上来就分成8个数组，然后两两归并)
    @staticmethod
    def solve1(arr):
        M5MergeSort.partition_sort(arr, 0, len(arr)-1)

    @staticmethod
    def partition_sort(arr,left,right):
        if left==right:
            return
        mid = left + (right-left)/2
        M5MergeSort.partition_sort(arr,left,mid)
        M5MergeSort.partition_sort(arr,mid+1,right)
        M5MergeSort.merge(arr,left,mid+1,right)

    @staticmethod
    def merge(arr,left_pos,right_pos,right_bound):
        temp = [0]*(right_bound-left_pos+1)
        i,j,k = left_pos,right_pos,0
        while(i<right_pos and j<= right_bound):
            if arr[i]<=arr[j]:
                temp[k] = arr[i]
                i += 1
            else:
                temp[k] = arr[j]
                j += 1
            k += 1

        while(i<right_pos):
            temp[k] = arr[i]
            k += 1
            i += 1

        while(j<=right_bound):
            temp[k]=arr[j]
            k+=1
            j+=1

        arr[left_pos:right_bound+1] = temp
