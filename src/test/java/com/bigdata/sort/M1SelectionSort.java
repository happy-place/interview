package com.bigdata.sort;

import org.junit.Test;

/**
 * 选择排序优化版
 * 选择排序，不稳定，实际生产不用
 */
public class M1SelectionSort extends BaseSort{

    /**
     * 选择排序：
     * 1.外层控制循环轮数，内层控制每轮循环次数，内层循环次数，随外层轮数增大收敛;
     * 2.每轮循环筛选出对应轮数极值，放置到前面
     * 时间复杂度 o(n^2) 双层循环，最好，最坏时间复杂度都为o(n^2)
     * 空间复杂度 o(1) 只申请一个交换空间
     * 稳定性：相同元素在排序过程中可能颠倒顺序，所以是不稳定的
     * DEBUG - (5),8,5,(2),9 （相同元素5的相对位置发送了改变）
     * DEBUG - 2,(8),(5),5,9
     * DEBUG - 2,5,(8),(5),9
     * DEBUG - 2,5,5,(8),9
     */

    @Test
    public void testSolve(){
//        solve1(arr);
        solve2(arr);
    }

    public static void solve1(int[] arr){
        int size = arr.length;
        for(int i=0;i<size-1;i++){
            int minPos = i;
            for(int j=i+1;j<size;j++){
                if(arr[minPos]>arr[j]){
                    minPos = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minPos];
            arr[minPos] = temp;
        }
    }

    /**
     * 选择排序优化解(高低位排序)
     * 1.外层循环为轮数，内层循环为每轮次数
     * 2.每轮循环同时找最大值，最最小值，当前轮循环次数2倍速度收敛
     * 3.调换元素时，先调换者可能对后调换者有扰动，需要通过分支判断
     * 时间复杂度 o(n^2)
     * 空间复杂度 o(2)
     * 稳定性：排序过程相同元素相对顺序可能颠倒，不稳定
     * 计算时间复杂度时初始化语句和打印语句，开销不考虑，重点关注递归和循环，忽略常数项、低次项和系数
     */
    public static void solve2(int[] arr){
        int length = arr.length;
        // 循环轮数 由于每轮循环同时找最大和最小值，因此轮数减半
        int loop = (length+1)/2;
        for(int i=0;i<loop;i++){
            // 初始化最大，最小下标
            int minPos = i;
            int maxPos = length-1-i;
            // 最大最小下标先比较
            if(arr[minPos]>arr[maxPos]){
                swap(arr,minPos,maxPos);
            }
            // 循环找最大值和最小值，两边往中间收敛
            for(int j=i+1;j<length-i;j++){
                if(arr[minPos]>arr[j]){
                    minPos = j;
                }
                if(arr[maxPos]<arr[j]){
                    maxPos = j;
                }
            }
            // 最小值往前放
            swap(arr,i,minPos);

            // 最大值不在刚刚被替换位置时，直接将最大值往后放，否则将最大值与上面调换过后位置对调（此时minPos 实质是i）
            if(i!=maxPos){
                swap(arr,length-1-i,maxPos);
            }else{
                swap(arr,length-1-i,minPos);
            }
        }
    }

}
