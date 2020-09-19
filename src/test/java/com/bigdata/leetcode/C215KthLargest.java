package com.bigdata.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;


/**
 * @ClassName C215KthLargest
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/14 12:13
 * @Version 1.0
 **/

class QuickSelect {

    static Random random = new Random();

    /**
     * 循环
     * @param arr
     * @param k
     * @return
     */
    public static int select(int[] arr, int k) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;

        int index = len - k;

        while (true) {
            int probIndex = randomPartition(arr, left, right);
            if (probIndex == index) {
                return arr[index];
            } else if (probIndex < index) {
                left = probIndex + 1;
            } else {
                right = probIndex - 1;
            }
        }
    }

    /**
     * 递归
     * @param arr
     * @param k
     * @return
     */
    public static int select2(int[] arr,int k){
        return recursive(arr,0,arr.length-1,arr.length - k);
    }

    public static int recursive(int[] arr,int left,int right,int index){
        int probIndex = randomPartition(arr, left, right);
        if(probIndex==index){
            return arr[index];
        }else if(probIndex<index){
            return recursive(arr,probIndex+1,right,index);
        }else{
            return recursive(arr,left,probIndex-1,index);
        }
    }

    private static int randomPartition(int[] arr, int left, int right) {
        System.out.println(String.format("left: %d, right: %d", left, right));
        int randIndex = random.nextInt(right - left + 1) + left;
        swap(arr, randIndex, right);
        return partition(arr, left, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int pivotPlace = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                pivotPlace++;
                swap(arr, i, pivotPlace);
            }
        }
        swap(arr, left, pivotPlace);
        return pivotPlace;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/**
 * 堆排序
 */
class HeadSort {

    private static int size;

    /**
     * 建堆
     * 从中间位置开始，依次找到堆顶(左右都比它小)
     * @param arr
     */
    private static void buildHeap(int[] arr){
        for(int i=(int)Math.floor(arr.length/2) + 1;i>=0;i--){
            heapify(arr,i);
        }
    }

    /**
     * 调整堆
     * 假定给定位置为堆顶
     * 如果左不为堆尾，且值大于堆顶，将堆顶设置为左
     * 如果右不为堆尾，且值大于堆顶，将堆顶设置为右
     * 如果堆顶改变了，将最大元素移到堆顶，针对之前堆顶继续调整堆，直至全部稳定
     * @param arr
     * @param i
     */
    private static void heapify(int[] arr,int i){
        int top = i;
        int left = 2 * i +1;
        int right = 2 * i +2;
        if (left < size && arr[left]>arr[top]){
            top = left;
        }
        if(right < size && arr[right]>arr[top]){
            top = right;
        }
        if(top!=i){
            swap(arr,i,top);
            heapify(arr,top);
        }
    }

    /**
     * 元素交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 堆排序 筛选出第k大源
     * 堆是最接近二叉树的结构
     * 选择第k大元素
     * @param arr
     * @param k
     * @return
     */
    public static int select(int[] arr,int k){
        size = arr.length;
        buildHeap(arr);
        int index = size - k;
        for(int i=size-1;i>=index;i--){
            swap(arr,0,i);
            size --;
            heapify(arr,0);
        }
        return arr[index];
    }

    /**
     * 对排序
     * @param arr
     */
    public static void sort(int[] arr){
        // 存储当前堆有效元素个数，调整堆时要参照此值判断真正堆尾 (老堆尾k已经保存了极值k)
        size = arr.length;
        // 构建堆
        buildHeap(arr);
        // 摘顶，并逐渐收敛有效元素
        for(int i=size-1;i>0;i--){
            // 堆顶0挪到堆尾i
            swap(arr,0,i);
            // 排除挪到堆尾的第i大值，收敛有效元素
            size --;
            // 重新调整堆顶
            heapify(arr,0);
        }
    }

}


public class C215KthLargest {

    private List<int[]> arrs = null;
    private List<Integer> ks = null;

    @Before
    public void before() {
        arrs = new ArrayList<>();
        ks = new ArrayList<>();
        arrs.add(new int[]{3, 2, 1, 5, 6, 4});
        ks.add(2);

        arrs.add(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6});
        ks.add(4);
    }

    private void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void solve1Test() {
        for (int i = 0; i < arrs.size(); i++) {
            int[] arr = arrs.get(i);
            int k = ks.get(i);
            System.out.println(k);
            printArr(arr);
            int result = solve1(arr, k);
            printArr(arr);
            System.out.println(result);
        }
    }

    /**
     * 暴力求解 内置排序 + 索引取值
     * 时间复杂度 o(nlogn) 内置排序默认为快排
     * 空间复杂度 o(1)，下标对调
     *
     * @param arr
     * @param k
     * @return
     */
    public int solve1(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[arr.length - k];
    }

    @Test
    public void solve2Test() {
        for (int i = 0; i < arrs.size(); i++) {
            int[] arr = arrs.get(i);
            int k = ks.get(i);
            System.out.println(String.format("第%d最大值", k));
            printArr(arr);
            int result = solve2(arr, k);
            printArr(arr);
            System.out.println(result);
        }
    }

    /**
     * 手动冒泡 (排序 + 索引)
     *
     * @param arr
     * @param k
     * @return
     */
    public int solve2(int[] arr, int k) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) { // 大的往后仍
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr[arr.length - k];
    }

    @Test
    public void solve3Test() {
        for (int i = 0; i < arrs.size(); i++) {
            int[] arr = arrs.get(i);
            int k = ks.get(i);
            System.out.println(k);
            printArr(arr);
            int result = solve3(arr, k);
            printArr(arr);
            System.out.println(result);
        }
    }

    /**
     * 手动冒泡优化（只排序到指定k极值）
     *
     * @param arr
     * @param k
     * @return
     */
    public int solve3(int[] arr, int k) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) { // 大的往后仍
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (i == k - 1) {
                break;
            }
        }
        return arr[arr.length - k];
    }

    @Test
    public void solve4Test() {
        for (int i = 0; i < arrs.size(); i++) {
            int[] arr = arrs.get(i);
            int k = ks.get(i);
            System.out.println(String.format("第%d最大值", k));
            printArr(arr);
            int result = solve4(arr, k);
            printArr(arr);
            System.out.println(result);
        }
    }

    /**
     * 手动快排 + 随机优化
     *
     * @param arr
     * @param k
     * @return
     */
    public int solve4(int[] arr, int k) {
//        int kValue = QuickSelect.select(arr, k);
        int kValue = QuickSelect.select2(arr, k);
        return kValue;
    }

    /**
     * 堆排序 到第k大元素出现
     * 时间复杂度为 o(nlogn)，建堆复杂度 o(n)，删除复杂度 o(klogn)，总复杂度 (k<n) o(n+klogn) = o(nlogn)；
     * 空间复杂度 o(logn) 递归空间
     */
    @Test
    public void solve5Test() {
        for (int i = 0; i < arrs.size(); i++) {
            int[] arr = arrs.get(i);
            int k = ks.get(i);
            System.out.println(String.format("第%d最大值", k));
            printArr(arr);
            int result = HeadSort.select(arr, k);
            printArr(arr);
            System.out.println(result);
        }
    }

    @Test
    public void heapSortTest() {
        for (int i = 0; i < arrs.size(); i++) {
            int[] arr = arrs.get(i);
            printArr(arr);
            HeadSort.sort(arr);
            printArr(arr);
        }
    }




}
