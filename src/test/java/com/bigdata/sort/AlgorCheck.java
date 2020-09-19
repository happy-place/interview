package com.bigdata.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 对数器
 * 1.足够随机样本；
 * 2.已知标准方法和自定义方法分别处理样本和样本副本；
 * 3.处理次数足够多，每次处理完后，一一对比，如果结果仍旧一致，就可以判断问一致
 */
public class AlgorCheck {

    public static int[] prepare(int n){
        Random random = new Random();
        int[] arr = new int[n];
        for(int i=0;i<arr.length;i++){
//            arr[i] = random.nextInt(10000);
            arr[i] = random.nextInt(100);
            arr[i] = arr[i] * (random.nextInt(10)%2==0?1:-1);
        }
        return arr;
    }

    public static void check(int n,int m){
        for(int i=0;i<m;i++){
            int[] arr1 = prepare(n);
            int[] arr2 = new int[n];
            System.arraycopy(arr1,0,arr2,0,n);
            Arrays.sort(arr1);
//            M1SelectionSort.solve1(arr2);
//            M2BubbleSort.solve1(arr2);
//            M3InsertionSort.solve1(arr2);
//            M4ShellSort.solve1(arr2);
//            M5MergeSort.solve1(arr2);
//            M6QuickSort.solve1(arr2);
//            M7CountSort.solve1(arr2);
//            M8RadixSort.solve1(arr2);
//            M9BucketSort.solve1(arr2);
            M10HeapSort.solve1(arr2);

            try{
                for(int j=0;j<n;j++){
                    if(arr1[j]!=arr2[j]){
                        throw new RuntimeException(String.format("arr1[%d]!=arr2[%d]",j,j));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        check(1000,100);
    }

}

