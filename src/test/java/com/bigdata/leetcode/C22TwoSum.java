package com.bigdata.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @ClassName C22TwoSum
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/12 10:40
 * @Version 1.0
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两 个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 给定 nums = [2, 7, 11, 15], target = 9 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 **/

public class C22TwoSum {

    private List<int[]> arrs = null;
    private List<Integer> targets = null;

    @Before
    public void before(){
        arrs = new ArrayList<>();
        targets = new ArrayList<>();
        arrs.add(new int[]{3, 2, 3});
        targets.add(6);
    }


    /**
     # 暴力匹配
     # 0~i逐个循环，i+1~len(arr) 挨个与前半匹配，直至成功过
     # 时间复杂度 o(n^2), 双层循环
     # 空间复杂度 o(1), 基于数组下标索引值，无额外空间开销
     * @param
     * @param
     * @return
     */
    @Test
    public void solve1Test(){
        for(int i=0;i<targets.size();i++){
            int[] nums = arrs.get(i);
            Integer target = targets.get(i);
            int[] ints = solve1(nums, target);
            System.out.println(ints[0]+","+ints[1]);
        }
    }

    public int[] solve1(int[] nums,int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("no solution");
    }

    @Test
    public void solve2Test(){
        for(int i=0;i<targets.size();i++){
            int[] nums = arrs.get(i);
            Integer target = targets.get(i);
            int[] ints = solve2(nums, target);
            System.out.println(ints[0]+","+ints[1]);
        }
    }

    /**
     * # 借助hash结构记住已经遍历过的数值和其索引
     * # 时间复杂度 o(n)，单层循环
     * # 空间复杂度 o(n), 借助hash存储已经遍历过的数据信息
     * @param
     * @param
     * @return
     */
    public int[] solve2(int[] nums,int target) {
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            if (temp.containsKey(y)) {
                return new int[]{temp.get(y), i};
            }
            temp.put(x, i);
        }
        throw new IllegalArgumentException("no solution");
    }

}
