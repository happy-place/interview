package com.bigdata.sort;

import org.junit.Test;

/**
 * 冒泡排序，稳定，但是太慢（两两比较、相邻元素交换次数过多），生产基本不用
 */
public class M2BubbleSort extends BaseSort{

    @Test
    public void testSolve(){
        solve1(arr);
//        solve2(arr);
    }

    /**
     * 标准 冒泡排序
     * 1.外层为轮，内层为每轮循环次数；
     * 2.相邻元素比较，大的往后移
     * 时间复杂度 o(n^2) n x n*(n-1)/2，最坏o(n^2) 最好 o(n)
     * 空间复杂度 o(1)，水平线性
     * 稳定性：稳定 (相同元素，拍完序后，相对顺序不变)
     *  INFO - [5, 8, 5, 2, 9]
     * DEBUG - 5,(8),(5),2,9
     * DEBUG - 5,5,(8),(2),9
     * DEBUG - 5,(5),(2),8,9
     * DEBUG - (5),(2),5,8,9
     *  INFO - [2, 5, 5, 8, 9]
     */
    public static void solve1(int[] arr){
        int length = arr.length;
        for(int i=0;i<length-1;i++){
            for(int j=0;j<length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 优化后冒泡排序
     * 某轮循环中，如果监控到内层循环没有数据交换，则证明已经排好序，直接退出外层循环，此时对于最好情况，即数组本身是有序，
     * 时间复杂度 o(n) 只经历一次内层循环
     * 空间复杂度 o(1) 随数据集膨胀，额外空间需求无变化
     */
    public static void solve2(int[] arr){
        int length = arr.length;
        for(int i=0;i<length-1;i++){
            boolean swaped = false;
            for(int j=0;j<length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    swaped = true;
                }
            }
            // 内循环未发生交换时，说明已经稳定了
            if(!swaped){
                break;
            }
        }
    }

}
