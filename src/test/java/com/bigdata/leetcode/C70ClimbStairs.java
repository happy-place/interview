package com.bigdata.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName com.bigdata.leetcode.C70ClimbStairs
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/12 10:50
 * @Version 1.0
 **/

public class C70ClimbStairs {

    private List<Integer> list = null;

    @Before
    public void before(){
        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
    }

    @Test
    public void solve1Test(){
        for(int n:list){
            System.out.println(solve1(n));
        }
    }

    /**
     * 递推 + 三目
     # 递归法(缺点，重复计算很多)
     # 时间复杂度 o(logn)
     # 空间复杂度 o(1)
     * @param n
     * @return
     */
    public int solve1(int n){
        return n==1 || n==2 ? n : solve1(n-1) + solve1(n-2);
    }

    @Test
    public void solve2Test(){
        for(int n:list){
            System.out.println(solve2(n,null));
        }
    }

    /**
     * 记忆递归
     * # 大数递归依赖小数递归，因此小数递归时，顺便保存状态，可以在大树递归时复用，节省时间开销
     * # 时间复杂度
     * # 空间复杂度 o(n)
     * @param n
     * @return
     */
    public int solve2(int n,int[] mem){
        if(null == mem){
            mem = new int[n+1];
        }
        if(n==1||n==2){
            return n;
        }else{
            mem[n] = solve2(n-1,mem) + solve2(n-2,mem);
            return mem[n];
        }
    }

    @Test
    public void solve3Test(){
        for(int n:list){
            System.out.println(solve3(n));
        }
    }

    /**
     * 递推（动态规划）
     * # 递推代替递归(n级在n-i级基础上构建起来)，又称动态规划
     * # 时间复杂度 o(n),单次循环
     * # 空间复杂度 o(n),使用数组辅助
     * @param n
     * @return
     */
    public int solve3(int n){
        int[] mem = new int[n+1];
        mem[1]=1;
        mem[2]=2;
        for(int i=3;i<=n;i++){
            mem[i] = mem[i-1] + mem[i-2];
        }
        return mem[n];
    }

    @Test
    public void solve4Test(){
        for(int n:list){
            System.out.println(solve4(n));
        }
    }

    /**
     * 递推优化(只保留最近两次计算)
     *# 动态规划状态压缩，即相比于slove3只考虑申请两个临时空间存储最近前两次值，拼接得到当前值，然后完成顺序滚动赋值操作
     * # 时间复杂度为 o(n) 挨个遍历
     * # 优化动态规划空间复杂度 o(1)
     * @param n
     * @return
     */
    public int solve4(int n){
        if(n==1||n==2){
            return n;
        }
        int before1 = 2;
        int before2 = 1;
        int temp = -1;
        for(int i=3;i<=n;i++){
            temp = before1 + before2;
            before2 = before1;
            before1 = temp;
        }
        return temp;
    }

    @Test
    public void solve5Test(){
        for(int n:list){
            System.out.println(solve5(n));
        }
    }

    /**
     * # 借助 斐波那契通项公式 fn = (1/√5) x (((1+√5)/2)^n+1 - ((1-√5)/2)^n+1)
     * # https://zhuanlan.zhihu.com/p/26679684
     * @param n
     * @return
     */
    public int solve5(int n){
        if(n==1||n==2){
            return n;
        }
        return (int)Math.floor(1*(Math.pow(((1+Math.sqrt(5))/2),n+1) - Math.pow(((1-Math.sqrt(5))/2),n+1))/Math.sqrt(5));
    }






}
