#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys
from math import floor


class M10HeapSort:

    @staticmethod
    def solve1(arr):
        global size
        size = len(arr)
        M10HeapSort.build_max_heap(arr)
        for i in range(len(arr)-1,0,-1):
            M10HeapSort.swap(arr,0,i)
            size -= 1
            M10HeapSort.heapify(arr,0)

    @staticmethod
    def build_max_heap(arr):
        for i in range(int(floor(len(arr)/2)),-1,-1):
            M10HeapSort.heapify(arr,i)

    @staticmethod
    def heapify(arr,i):
        top = i
        left = 2 * i +1
        right = 2 * i +2
        if left<size and arr[left]>arr[top]:
            top = left
        if right<size and arr[right]>arr[top]:
            top = right
        if top !=i:
            M10HeapSort.swap(arr,i,top)
            # 此时 top 对应的就是刚才的i
            M10HeapSort.heapify(arr,top)

    @staticmethod
    def swap(arr,i,j):
        arr[i],arr[j] = arr[j],arr[i]


if __name__ == "__main__":
    pass