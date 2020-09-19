package com.bigdata.sort;

import org.junit.Test;

/**
 * 计数排序，桶排序思想简单表现形式
 * 适合：数据量大，数据范围小（分布集中）场景
 * 1.查找数组最小、最大边界值
 * 2.创建 max - min + 1 长度大小 计数器数组，数组下标对应 元素与最小值差值，存储数据对应相同元素个数 （好比吧相同元素以计数方式压缩在一个下标位上）；
 * 3.直接在原数组上顺序，重复对应计数形式，回放计数器数组，实现排序
 * 时间复杂度 o(n+k) n 为原数组长度，k 为取值范围
 * 空间复杂度 o(n+k) 原数组和取值数组同时存在
 * 稳定性：稳定
 */
public class M7CountSort extends BaseSort{

    @Test
    public void testSolve(){
        solve1(arr);
    }

    public static void solve1(int[] arr){
        int[] bounds = findBounds(arr);
        int min = bounds[0];
        int max = bounds[1];
        int bound = max - min + 1;
        int[] counts = new int[bound];

        for(int e:arr){
            int i = e - min;
            counts[i]++;
        }

        int m = 0;
        for(int i=0;i<counts.length;i++){
            int count = counts[i];
            for(int j=count;j>0;j--){
                arr[m++] = i+min;
            }
        }
    }

    public static int[] findBounds(int[] arr){
        int minPos = 0;
        int maxPos = arr.length-1;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<arr[minPos]){
                minPos = i;
            }
            if(arr[i]>arr[maxPos]){
                maxPos = i;
            }
        }
        return new int[]{arr[minPos],arr[maxPos]};
    }
}
