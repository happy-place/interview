package com.bigdata.sort;

import org.junit.Test;
import java.util.Arrays;

/**
 * 基数排序 同计数排序一样也属于桶排序思想
 *
 * 1.计数排序 桶个数 = 数据UV，时间复杂度 o(n+k) ，空间复杂度 o(n+k) ，n为元素个数，k为桶数，稳定排序，适合数据量大，分部范围小 场景
 * 2.基数排序 桶个数 = 十进制可取数(10)，依次将数据按 取个位、十位、百位 上数值，归档搭配计数器数组中，然后将计数器数组从左往右累加，逆序遍历原数组，存储到对应结果数组，并将计数器-1
 *      时间复杂度 o(n*k) 空间复杂度 o(n+k) 稳定排序，速度快于比较排序，如快排；
 * 3.属于关键字排序一种，遵循低位优先元组
 *
 */
public class M8RadixSort extends BaseSort{

    @Test
    public void testSolve(){
        arr = new int[]{-103,-9,1,7,15,25,109,209,5};
        logger.debug("input:"+Arrays.toString(arr));
        solve1(arr);
        logger.debug("output:"+Arrays.toString(arr));
    }

    @Test
    public void testFindBoundary(){
        int[] arr = {-3,-1,6,8,3,7};

        int[] boundary = findBoundary(arr);
        logger.debug("findBoundary: min="+boundary[0]+", max="+boundary[1]);

        logger.debug("before adjusted: "+ Arrays.toString(arr));
        int adjustedMax = adjustNegative(arr,boundary[0],boundary[1]);
        logger.debug("adjustNegative: adjustedMax="+adjustedMax);
        logger.debug("after adjusted: "+ Arrays.toString(arr));

        reappear(arr,boundary[0]);
        logger.debug("reappear: "+ Arrays.toString(arr));
    }



    public static void solve1(int[] arr){
        // 获取数组边界(最大、最小值)
        int[] boundary = findBoundary(arr);
        int min = boundary[0];
        int max = boundary[1];

        // 适配存在负数元素情况，，当存在负数时，所有元素 + abs(min)
        int adjustedMax = adjustNegative(arr,min,max);

        // 基数排序
        radixSort(arr,adjustedMax);

        // 存在负数元素时，还原数组 所有元素 - abs(min)
        reappear(arr,min);
    }

    // 基数排序
    public static void radixSort(int[] arr,int max){
        // 每轮基数排序后输出结果
        int[] result = new int[arr.length];
        // 每轮基数排序，计数器数组
        int[] count = new int[10];
        // 数组最大值的最大基数（决定基数排序循环次数）
        int maxRadix = getMaxRadix(max);
        // 依次按基数=0,1,2 即 exp=1,10,100 排序
        for(int i=0;i<=maxRadix;i++){
            // 取指基数位上数值，归档到对应计数器数组上
            for(int j=0;j<arr.length;j++){
                int num = getRadixNum(arr[j],i);
                count[num]++;
            }

            // 计数器数组从左往右累加
            for(int m=1;m<count.length;m++){
                count[m] = count[m] + count[m-1];
            }

            // 安置本轮基数排序结束时，元素位置到输出数组上
            for(int n=arr.length-1;n>=0;n--){
                // 找对应计数器数组下标
                int num = getRadixNum(arr[n],i);
                // 安置原数组元素 到 计数器数组指定下标计数 - 1 位置
                result[--count[num]] = arr[n];
            }

            // 拷贝输出数组 到 原数组
            System.arraycopy(result,0,arr,0,arr.length);

            // 计数器清空
            Arrays.fill(count,0);
        }
    }

    // 给定数据指基数位上的数值
    public static int getRadixNum(int num,int radix){
        for(int i=0;i<radix;i++){
            num /= 10;
        }
        return num % 10;
    }

    // 最大值的最大基数，所处位数，个位记0，十位记1，依次递增
    public static int getMaxRadix(int num){
        return (int)Math.log10(num);
    }

    public static int adjustNegative(int[] arr,int min,int max){
        if(min<0){
            int addition = Math.abs(min);
            for(int i=0;i<arr.length;i++){
                arr[i] += addition;
            }
            max += addition;
        }
        return max;
    }

    public static void reappear(int arr[],int minPos){
        if(minPos<0){
            for(int i=0;i<arr.length;i++){
                arr[i] -= Math.abs(minPos);
            }
        }
    }

    // 找最大最小值
    public static int[] findBoundary(int[] arr){
        int minPos = 0;
        int maxPos = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[minPos]>arr[i]){
                minPos = i;
            }
            if(arr[maxPos]<arr[i]){
                maxPos = i;
            }
        }
        return new int[]{arr[minPos],arr[maxPos]};
    }

}
