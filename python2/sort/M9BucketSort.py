#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys


# 桶排序
# 1.找到数组最大、最下值，得到数据分部范围；
# 2.基于数据量选择合适分桶数，组件分桶数组；
# 3.从最小桶开始收集数据，收集满后进行排序，然输出到中间数组
# 4.中间数组拷贝到原数组
# 时间复杂度 o(n+k), 空间复杂度 o(n+k) 稳定

class M9BucketSort:

    @staticmethod
    def solve1(arr):
        num = M9BucketSort.get_boucket_num(len(arr))
        min,max = M9BucketSort.get_boundary(arr)
        buckets = M9BucketSort.get_buckets(num, min, max)
        temp = []
        for i in range(len(buckets)-1):
            sorted_bucket = M9BucketSort.entry_bucket(arr, buckets[i], buckets[i + 1])
            temp.extend(sorted_bucket)
        M9BucketSort.copy_array(temp,arr)

    @staticmethod
    def copy_array(src,dest):
        for i in range(len(dest)):
            dest[i] = src[i]

    @staticmethod
    def entry_bucket(arr,left,right):
        temp = []
        for e in arr:
            if e>=left and e < right:
                temp.append(e)
        M9BucketSort.sort(temp)
        return temp

    @staticmethod
    def sort(arr):
        for i in range(1,len(arr)):
            for j in range(i-1,-1,-1):
                if arr[j] > arr[j+1]:
                    M9BucketSort.swap(arr,j,j+1)

    @staticmethod
    def swap(arr,i,j):
        arr[i],arr[j] = arr[j],arr[i]

    @staticmethod
    def get_buckets(num,min,max):
        width = max - min
        slide = width / num
        buckets = [0] * (num+1)
        buckets[0] = min
        buckets[-1] = max + 1
        for i in range(1,len(buckets)-1):
            buckets[i] = buckets[i-1] + slide
        return buckets

    @staticmethod
    def get_boucket_num(length):
        num = 0
        if length<100:
            num  = 2
        elif length<1000:
            num = 4
        else:
            num = 6
        return num

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

def test_solve():
    arr = [-10, -9, 3, 2, 5, 10, 20]
    print(arr)
    M9BucketSort.solve1(arr)
    print(arr)

if __name__ == "__main__":
    test_solve()
    pass