package com.bigdata.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 堆排序
 * 1.构建大顶堆，保证定元素始终是最大；
 * 2.顶堆元素与末尾元素0交换，把本轮极值固定下来；
 * 3.收敛堆，继续重复1，2，直至全部元素沉淀下来
 * <p>
 * 时间复杂度 初始化最大顶堆复杂度为 o(Math.floor(arr.length/2) + 1) -> o(n)，调整堆复杂度为 o(n*log(2,n)) -> o(n*log(2,n)) ，相加关系 o(n + n(log(2,n))
 * 空间复杂度 o(1)
 */
public class M10HeapSort extends BaseSort {

    private static int size = 0;

    @Test
    public void testSolve() {
        arr = new int[]{51, -11, 6, -82, 58, 82, -53, -86, -64, 82};
        logger.debug(Arrays.toString(arr));
        solve1(arr);
        logger.debug(Arrays.toString(arr));
    }

    public static void solve1(int[] arr) {
        // 堆有效元素个数
        size = arr.length;
        // 首次构建大顶堆
        buildMaxHeap(arr);
        // 摘顶，挪到堆尾，收敛，重新调整堆(重新获取对顶)，直至摘完所有顶
        for (int i = arr.length-1; i > 0; i--) {
            swap(arr, 0,i);
            size--;
            heapify(arr, 0);
        }
    }

    // 首次构建堆，从最大非叶子节点开始遍历，直至最小非叶子节点稳定为最大值
    private static void buildMaxHeap(int[] arr) {
        // 底部元素序号大，顶部元素序号小
        // 最大非叶子节点为Math.floor(arr.length/2) + 1
        for (int i = (int) Math.floor(arr.length / 2); i >= 0; i--) {
            heapify(arr, i);
        }
    }

    // 调整将指定非叶子节点所在树枝，是其元素最大，然后将动过的枝重新调整（递归）
    private static void heapify(int[] arr, int i) {
        int top = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && arr[left] > arr[top]) {
            top = left;
        }
        if (right < size && arr[right] > arr[top]) {
            top = right;
        }
        if (top != i) {
            swap(arr, i, top);
            // 此时 top 对应的就是刚才的i
            heapify(arr, top);
        }
    }

}
