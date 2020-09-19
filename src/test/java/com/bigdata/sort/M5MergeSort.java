package com.bigdata.sort;

import org.junit.Test;

import java.util.Arrays;


/**
 * 归并排序
 * 1.数组以递归方式拆分为两半，然后逐渐细分为只有两个元素；
 * 2.合并两个对称数组，在合并过程实现排序
 * 时间复杂度 o(nlog(2,n))，最好和最坏都是 o(n)
 * 空间复杂度 o(n) 需要申请中间数组
 * java python 对象排序用的就是归并排序(优化后的多路归并排序TimSort)
 * TimSort 一次性分成多份，每份各自排序，然后两两合并
 *
 * 二分排序：binarySort 变形的插入排序，插入过程，遵循二分查找
 */
public class M5MergeSort extends BaseSort{

    @Test
    public void testMerge(){
        int[] arr = {2,4,6,8,10,3,5,7,9,13};
        logger.info("merge: "+Arrays.toString(arr));
        merge(arr, 1, 5, 7);
        logger.info("merge: "+Arrays.toString(arr));
    }

    @Test
    public void testSolve(){
        solve1(arr);
    }

    public static void solve1(int[] arr){
        partitionSort(arr,0,arr.length-1);
    }

    /**
     * 递归拆分为左右支，知道只包含两个元素为止，然后顺序合并
     * @param arr
     * @param left
     * @param right
     */
    public static void partitionSort(int[] arr,int left,int right){
        if(left == right){
            return;
        }
        int mid = left + (right-left)/2;
        partitionSort(arr,0,mid);
        partitionSort(arr,mid+1,right);
        merge(arr,left,mid+1,right);
    }

    /**
     * 对已经分别拍好序的两段数组 [leftPrt,rightPrt), [rightPrt,rightBound] 进行合并
     * @param arr
     * @param leftPrt
     * @param rightPrt
     * @param rightBound
     */
    public static void merge(int[] arr, int leftPrt, int rightPrt, int rightBound) {
        int[] temp = new int[rightBound-leftPrt+1];
        int i = leftPrt;
        int j = rightPrt;
        int k = 0;

        while(i < rightPrt && j <= rightBound){
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while(i<rightPrt){
            temp[k++] = arr[i++];
        }

        while(j<=rightBound){
            temp[k++] = arr[j++];
        }

        for(int m=0,n=leftPrt;m<temp.length;){
            arr[n++] = temp[m++];
        }
    }

}
