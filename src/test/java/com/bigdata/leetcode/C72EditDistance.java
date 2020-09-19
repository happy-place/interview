package com.bigdata.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName C72EditDistance
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/16 07:50
 * @Version 1.0
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class C72EditDistance {

    private List<String[]> words = null;

    @Before
    public void before(){
        words = new ArrayList<>();
        words.add(new String[]{"horse","ros"});
    }

    @Test
    public void testSolve1(){
        for(String[] arr:words){
            String word1 = arr[0];
            String word2 = arr[1];
            int ans = solve1(word1, word2);
            System.out.println(String.format("%s to %s edit-dist is %d",word1,word2,ans));
        }
    }

    // 动态规划
    public int solve1(String word1, String word2) {
        int m = null != word1 ? word1.length() : 0; // x
        int n = null != word2 ? word2.length() : 0; // y

        if (m * n == 0) {
            return m + n;
        }

        int[][] dp = new int[n+1][m+1];

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                int left = dp[i-1][j] + 1;
                int down = dp[i][j-1] + 1;
                int leftDown = dp[i-1][j-1];
                if (word1.charAt(j-1)!=word2.charAt(i-1)){
                    leftDown +=1;
                }
                dp[i][j] = Math.min(Math.min(left,down),leftDown);
            }
        }
        return dp[n][m];
    }

}
