package com.bigdata.sort;

import org.junit.Test;

import java.util.ArrayList;

import java.util.List;

/**
 * 桶排序
 * 1.找到数组最大、最下值，得到数据分部范围；
 * 2.基于数据量选择合适分桶数，组件分桶数组；
 * 3.从最小桶开始收集数据，收集满后进行排序，然输出到中间数组
 * 4.中间数组拷贝到原数组
 * 时间复杂度 o(n+k), 空间复杂度 o(n+k) 稳定
 */
public class M9BucketSort extends BaseSort{

    @Test
    public void testSolve(){
        solve1(arr);
    }

    public static void solve1(int[] arr){
        // 根据数据质量，决定分桶数
        int bucketNum = getbucketNum(arr.length);
        // 根据分桶数划分桶边界数组
        int[] buckets = getBuckets(arr,bucketNum);
        // 顺序接收排好序的桶数据
        int[] result = new int[arr.length];
        //
        int j=0;
        for(int i=1;i<buckets.length;i++){
            int left = buckets[i-1];
            int right = buckets[i];
            int[] sortedBucket = enterBucket(arr, left, right);
            System.arraycopy(sortedBucket,0,result,j,sortedBucket.length);
            j += sortedBucket.length;
        }
        System.arraycopy(result,0,arr,0,arr.length);
    }

    // 按起止边界抽取数据入桶，并对桶内数据进行排序，然后输出
    public static int[] enterBucket(int[] arr,int left,int right){
        List<Integer> temp = new ArrayList<>();
        for(int num:arr){
            if(num>=left && num < right){
                temp.add(num);
            }
        }
        int[] bucket = new int[temp.size()];
        for(int i=0;i<bucket.length;i++){
            bucket[i] = temp.get(i);
        }
        insertSort(bucket);
        return bucket;
    }

    // 根据数据量决定分桶数
    public static int getbucketNum(int length){
        int num = 0;
        if(length<100){
            num = 2;
        }else if(length<1000){
             num = 5;
        }else if(length<10000){
            num = 8;
        }else{
            num = 10;
        }
        return num;
    }

    // 获取分桶边界数组
    public static int[] getBuckets(int[] arr,int bucketNum){
        int minPos = 0;
        int maxPos = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<arr[minPos]){
                minPos = i;
            }
            if(arr[i]>arr[maxPos]){
                maxPos = i;
            }
        }

        int len = arr.length % bucketNum !=0 ? (arr.length / bucketNum) +1 : arr.length / bucketNum;
        int[] result = new int[len];
        result[0] = arr[minPos];
        result[len-1] = arr[maxPos]+1;
        for(int i=1;i<result.length-1;i++){
            result[i] = result[i-1] + bucketNum;
        }
        return result;
    }

    // 桶内部使用插入排序
    public static void insertSort(int[] arr){
//        Arrays.sort(arr);
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0;j--){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

}
