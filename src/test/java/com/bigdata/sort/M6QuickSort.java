package com.bigdata.sort;

import org.junit.Test;

/**
 * 快排
 * 1.给定任意数组，随机选择一个下标元素作为轴(通常选最后一个元素)，然后从第一个元素 和 倒数第二个元素开始，向中间收缩，逐个与轴比较；
 * 2.直到左侧出现大于轴，右侧小于轴，将左右元素调换，继续向中间收敛，与轴进行比较，
 * 3.直至左右游标交叉，停止循环，此时轴的位置，就是左侧游标位置，将轴移到正确位置，然后返回轴下标
 * 4.上层，参照轴下标，将数组拆分为两半（不包含轴），然后分别使用递归方式，启动针对各自的排序
 * 5.直到左右游标碰撞时，停止递归，此时已经排好序
 * 时间复杂度：o(n*log(2,n)) 拆分为log(2,n)个数组，递归了n次，，最差为 o(n^2)顺序固定好，数组不能裂变，最好为 o(n*log(2,n))
 * 空间复杂度：o(log(2,n))，最多拆分为 log(2,n)个数组，
 * 稳定性：不稳定
 *  INFO - [5, 8, 5, 2, 9]
 * DEBUG - 5,8,5,2,(9)
 * DEBUG - (5),8,5,(2),9
 * DEBUG - 2,(8),(5),5,9
 * DEBUG - 2,5,(8),(5),9
 *  INFO - [2, 5, 5, 8, 9]
 *
 */
public class M6QuickSort extends BaseSort{

    @Test
    public void testSolve(){
        solve1(arr);
    }

    public static void solve1(int[] arr) {
        partitionSort(arr, 0, arr.length - 1);
    }

    public static void partitionSort(int[] arr, int left, int right) {
        if(left>=right){
            return;
        }
        int mid = findMid(arr,left,right);
        partitionSort(arr, left, mid - 1);
        partitionSort(arr, mid + 1, right);
    }

    public static int findMid(int[] arr, int leftBound, int rightBound) {
        int i = leftBound;
        int j = rightBound - 1;
        int pivot = rightBound;

        while(i<=j){
            while (i<=j && arr[i] <= arr[pivot]) {
                i++;
            }
            while(i<=j && arr[j] > arr[pivot]){
                j--;
            }
            if(i<j){
                swap(arr,i,j);
            }
        }
        int mid = i;
        swap(arr,mid,pivot);
        return mid;
    }

}
