package com.bigdata.sort;

import org.junit.Test;


/**
 * 插入排序，稳定，小样本基本有序数组，排序比较快，生产有在使用
 *
 */
public class M3InsertionSort extends BaseSort{

    @Test
    public void testSolve(){
        solve1(arr);
//        solve2(arr);
    }

    /**
     * 选择排序
     * 1.外层循环为轮，内层循环为每轮的次
     * 2.每次比较时，外层循环对应目标元素i，依次与签名i-1 ~ 0 对比，发现比其大的就往2前移，这样本次循环过后签名 0~i 都是有序的
     * 3.每次新的目标元素 i 插入到前面 0~i-1 中合适位置
     * 时间复杂度 o(n^2)，最坏情况为 o(n^2), 最好情况o(n)
     * 空间复杂度 o(1) 随数据规模扩大，空间不变
     * 稳定性：稳定 (相同元素，拍完序后，相对顺序不变)
     * INFO - [5, 8, 5, 2, 9]
     * DEBUG - 5,(8),(5),2,9
     * DEBUG - 5,5,(8),(2),9
     * DEBUG - 5,(5),(2),8,9
     * DEBUG - (5),(2),5,8,9
     *  INFO - [2, 5, 5, 8, 9]
     * 插入排序比冒泡快，冒泡每次命中时需要交换数据
     * 插入排序比选择排序快，前面地都有序情况下，直接过，选择还是需要交换
     *
     */
    public static void solve1(int[] arr){
        int size = arr.length;
        for(int i=1;i<size;i++){
            for(int j=i-1;j>=0;j--){
              if(arr[j]>arr[j+1]){
                  swap(arr,j,j+1);
              }
            }
        }
    }

    /**
     * 选择排序优化
     * 1.内层循环，交换条件移到循环语句上
     * 2.为运算代替交换
     * a = a ^ b
     * b = a ^ b
     * a = a ^ b
     */
    public static void solve2(int[] arr){
        int size = arr.length;
        for(int i=1;i<size;i++){
            for(int j=i-1;j>=0 && arr[j]>arr[j+1];j--){
//                    swap(arr,j,j+1);
                arr[j] = arr[j] ^ arr[j+1];
                arr[j+1] =  arr[j] ^ arr[j+1];
                arr[j] = arr[j] ^ arr[j+1];
            }
        }
    }

}
