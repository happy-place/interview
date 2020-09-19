#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/14 07:22
Info:
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""

import os, sys
from random import randint
import heapq,functools

# 暴力排序1 (使用自带排序，默认为快速排序)
def solve1(arr,k):
    arr.sort()
    print(arr)
    return arr[-k]


# 暴力排序2（自定义冒泡降序），然后返回下标为k元素即可
# 时间复杂度 o(n^2)，最坏时候内层也循环了n次
# 空间复杂度 o(1)，只有一个临时变量开销
def solve2(arr,k):
    # 0~n-1开始 逐渐收敛，界定内层循环次数
    for i in range(len(arr)-1):
        # 内层循环每轮都把最大值往后抛，下次循环需要次数逐渐-1
        for j in range(0,len(arr)-1-i):
            # 大在前则往后抛
            if arr[j]>arr[j+1]:
                temp = arr[j]
                arr[j] = arr[j+1]
                arr[j+1] = temp
    print arr
    # 输出倒数第k位
    return arr[-k]

# 暴力破解优化,找到第k大，就停止搜索
# 时间复杂度o(kn)，外层固定循环次数为k，内层为次数最大为n
# 空间复杂度o(1)，只占用一个变量
def solve3(arr,k):
    # 外层循环觉得内层循环次数
    for i in range(len(arr)-1):
        # 随着最大值逐渐往后移，内层每次循环逐渐-1
        for j in range(0,len(arr)-1-i):
            # 大在前，则往后抛
            if arr[j]>arr[j+1]:
                temp = arr[j]
                arr[j] = arr[j+1]
                arr[j+1] = temp
        # 搜索到第 k 大元素就终止
        if i==k-1:
            break
    print arr
    return arr[-k]

# 快速排序（无需排完全部的，只需要按快排思路排序，当某时刻某位置i的左侧都小，右侧都大，且i=k时，即满足需求）
# 时间复杂度o(n) 数组长度
# 空间复杂度为 o(logn)，递归使用的是栈空间 n * (1/2)^k = 1 => n = log(2,n)
def solve4(arr,k):
    def quick_sort(arr,k):
        left = 0
        right = len(arr) - 1
        index = len(arr) - k
        #  随机取一个元素，找到其在数组升序排列时最终位置
        while(True):
            prob_index = random_partition(arr, left, right)
            # 然后判断这个数的下标x是否为需要的下标
            if prob_index==index: # 匹配成功直接返回
                print arr
                return arr[index]
            elif prob_index<index: # 否则收敛搜索空间，继续递归搜索
                left = prob_index+1
            else:
                right = prob_index-1

    def random_partition(arr,left,right):
        print("left: %d, right: %d"%(left,right));
        # 获取随机下标
        rand_index = randint(0,right-left) + left
        # 为避免极端情况(最大值在最后)，使用随机下标，与最右侧互换
        swap(arr,rand_index,right)
        # 然后进入partition 筛选，确定头元素在数组升序时最终真实位置
        return partition(arr, left, right)

    def partition(arr,left,right):
        # 以首元素作为需要定位目标
        pivot = arr[left]
        # 首元素位置
        pivot_place = left
        for i in range(left+1,right+1): # 需要定位目标与其余元素比对 (range 左闭右开)
            if arr[i]<pivot: # 小于目标的往目标方向移动
                pivot_place += 1 # 记录目标理论所处位置
                swap(arr,i,pivot_place) # 小于目标的往靠近目标方向(前)扔
        swap(arr,left,pivot_place) # 目标移动到最终升序数组中所处位置
        return pivot_place # 返回目标最终位置

    def swap(arr,i,j):
        # 元素交换
        temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
        # arr[i],arr[j] = arr[j],arr[i]

    return quick_sort(arr,k)

# 两种快排 (循环,递归)
def quick_select(arr,k):

    # 循环思路
    def sort1(arr,k):
        left = 0
        right = len(arr)-1
        index = len(arr) - k

        while True:
            prob_index = random_partition(arr, left, right)
            if prob_index == index:
                print(arr)
                return arr[index]
            elif prob_index<index:
                left = prob_index + 1
            else:
                right = prob_index - 1

    # 递归思路
    def sort2(arr,left,right,index):
        prob_index = random_partition(arr, left, right)
        if prob_index == index:
            return arr[index]
        elif prob_index<index:
            return sort2(arr, prob_index+1, right,index)
        else:
            return sort2(arr, left, prob_index-1,index)

    def random_partition(arr,left,right):
        rand_right = randint(0,right-left)+left
        swap(arr,rand_right,right)
        return partition(arr,left,right)

    def partition(arr,left,right):
        pivoit = arr[left]
        pivoit_index = left
        for i in range(left+1,right+1):
            if arr[i]<pivoit:
                pivoit_index += 1
                swap(arr,i,pivoit_index)
        swap(arr, left, pivoit_index)
        return pivoit_index

    def swap(arr,i,j):
        arr[i],arr[j] = arr[j],arr[i]

    # return sort1(arr,k)
    return sort2(arr,0,len(arr)-1,len(arr) - k)

# 基于堆手动 实现优先级队列，控制中容量为k，将所有元素都入完堆后，留在对顶的就是目标元素
# 时间复杂度 o(nlogn) 建堆 o(n)，删除为o(klogn)，由于k<n  因此渐进时间复杂度为 o(n+klogn) = o(nlogn)
# 空间复杂度 o(logn),即二分法，递归使用栈空间 n*(1/2)^k = 1 => log(2,n) = k
class PriorityQueue:
    def __init__(self,capacity,cmp=None):
        if not cmp:
            def cmp(x,y):
                if x<y:
                    return -1
                elif x==y:
                    return 0
                else:
                    return 1
        self.capacity = capacity
        self.key = functools.cmp_to_key(cmp)
        self.data = []

    def peek(self):
        return self.data[0].obj if self.data else None

    def push(self,v):
        if len(self.data) == self.capacity:
            self.poll()
        heapq.heappush(self.data,self.key(v))

    def poll(self):
        return heapq.heappop(self.data).obj if self.data else None

    def __bool__(self):
        return bool(self.data)

def solve5(arr,k):
    queue = PriorityQueue(k, lambda x, y: x - y)
    for i in arr:
        queue.push(i)
    result = queue.poll()
    return result


# 堆排序
def head_sort(arr):

    def build_heap(arr):
        from math import floor
        for i in range(int(floor(len(arr)/2)),-1,-1):
            heapify(arr,i)

    def heapify(arr,i):
        largest = i
        left = 2*i + 1
        right = 2*i + 2
        if left < length and arr[left] > arr[largest]:
            largest = left
        if right < length and arr[right] > arr[largest]:
            largest = right
        if largest != i:
            swap(arr,i,largest)
            heapify(arr,largest)

    def swap(arr,i,j):
        arr[i],arr[j] = arr[j],arr[i]

    def sort(arr):
        global length
        length = len(arr)
        build_heap(arr)
        for i in range(len(arr)-1,0,-1):
            swap(arr,0,i)
            length -= 1
            heapify(arr,0)

    sort(arr)

# 测试堆排序
def test_head_sort():
    arr = [3, 2, 1, 5, 6, 4]
    head_sort(arr)
    print(arr)


# 基于堆排序
def solve6(arr,k):

    def build_head(arr):
        # 建堆
        # 从中间开始，直至循环根节点(下标为0位置)
        from math import floor
        for i in range(int(floor(len(arr)/2))+1,-1,-1):
            heapify(arr,i)

    def heapify(arr,i):
        # 调整堆
        # 堆顶 左叶 右叶
        largest = i
        left = 2*i+1
        right = 2*i+2
        # 左叶非根节点，且大于堆顶时，堆顶指向左叶
        # size 随着最大值出现，逐渐收敛
        if left < size and arr[left] > arr[largest]:
            largest = left
        # 右叶非根节点，且大于堆顶时，堆顶指向右叶
        if right < size and arr[right] > arr[largest]:
            largest = right
        # 当前目标节点还不是堆顶时，继续递归
        if i != largest:
            # 交换 目标 和 堆顶
            swap(arr,i,largest)
            # 堆顶继续与左右叶比对，直至目标节点稳定
            heapify(arr,largest)

    def swap(arr,i,j):
        arr[i],arr[j] = arr[j],arr[i]

    def select(arr,k):
        global size
        size = len(arr)
        # 建堆
        build_head(arr)
        index = len(arr) - k
        # 逆序摘顶至尾部
        for i in range(len(arr)-1,index-1,-1):
            # 0 为堆顶，i 为当前堆的顶 （将顶摘到尾部）
            swap(arr,0,i)
            size -= 1 # 收敛堆
            heapify(arr,0) # 重新设置新堆顶
        return arr[index]

    return select(arr,k)

def test(func):
    arr = [3, 2, 1, 5, 6, 4]
    k = 2
    print func(arr,k)
    print(arr)
    arr = [3, 2, 3, 1, 2, 4, 5, 5, 6]
    k = 4
    print func(arr,k)
    print(arr)

def test_range():
    for i in xrange(3,5):
        print i

if __name__ == "__main__":
    # test(solve1)
    # test(solve2)
    # test(solve3)
    # test(solve4)
    # test(quick_select)
    # test(solve5)
    # test_range()
    # test_head_sort()
    test(solve6)
    pass
