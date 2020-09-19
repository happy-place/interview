package com.bigdata.sort;

import org.junit.Test;


public class M4ShellSort extends BaseSort{

    @Test
    public void testSolve(){
        solve1(arr);
//        solve2(arr);
    }

    /**
     * 希尔排序
     * 1.基于插入排序优化
     * 2.将数组按gap间隔等分为 len/gap 个数组
     * 3.各校数组执行插入排序
     * 4.主键收敛gap至1，每收敛一次，小数组主键变为有序，直至最后对全部元素执行一次插入排序
     * 5.相比于直接全局插入排序，当gap较大时，元素移动次数少，gap较小时，元素移动距离短，因此直接使用插入排序，效率更高
     * 时间复杂度，目前无明确计算公式，平均值为o(n^1.3) 即 o(n^7/6)，最差为 o(n^2) 最好为o(n)
     * 空间复杂度 o(1) 无额外空间开销（随n增长，空间不变）
     * 稳定性：稳定
     * INFO - [5, 8, 5, 2, 9]
     * DEBUG - 5,(8),(5),2,9
     * DEBUG - 5,5,(8),(2),9
     * DEBUG - 5,(5),(2),8,9
     * DEBUG - (5),(2),5,8,9
     *  INFO - [2, 5, 5, 8, 9]
     */
    public static void solve1(int[] arr){
        for(int gap=4;gap>0;gap--){
            for(int i=0;i<arr.length/gap ;i++){
                for(int j=i-gap;j>=0;j-=gap){
                    if(arr[j]>arr[j+gap]){
                        swap(arr,j,j+gap);
                    }
                }
            }
        }
    }

    /**
     * 希尔排序，效率依赖于gap 序列
     * 经过测试 Knuth 序列效率最高
     * 从 h=1 开始，在满足 h < len/ 3 条件下，按 h=h*3 + 1 迭代出最大h，作为初始gap,
     * 以后gap 遵循 (gap-1)/3 比例收敛
     */
    public static void solve2(int[] arr){
        int h =1;
        while(h<arr.length/3){
            h = h*3 + 1;
        }
        for(int gap=h;gap>0;gap=(gap-1)/3){
            for(int i=gap;i<arr.length/gap;i++){
                for(int j=i-gap;j>=0;j-=gap){
                    if(arr[j]>arr[j+gap]){
                        swap(arr,j,j+gap);
                    }
                }
            }
        }
    }

}
