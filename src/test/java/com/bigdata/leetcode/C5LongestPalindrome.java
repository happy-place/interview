package com.bigdata.leetcode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName C5LongestPalindrome
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/13 21:30
 * @Version 1.0
 **/

public class C5LongestPalindrome {

    private List<String> texts = null;

    @Before
    public void before() {
        texts = new ArrayList<String>();
        texts.add("a");
        texts.add("aa");
        texts.add("abac");
    }

    @After
    public void after() {

    }

    @Test
    public void solve1Test(){
        for (String text : texts) {
            System.out.println(solve1(text));
        }
    }

    /**
     * # 动态规划
     * # P(i,j) = True / False 表示 i~j 字符串是回文 或 不是
     * # s[i] 表示下标为元素
     * # P(i,j) = True 满足前提条件是 P(i+1,j-1) & s[i] == s[j]
     * # 边界条件：
     * # 1. i=j 单字符，任何时候都成立;
     * # 2. j = i+1 双字符，只有当 s[i]=s[j] 时成立；
     * # 3. 2 <= j-i < len - 1 ，只有当 P(i+1,j-1) & s[i]==s[j] 是成立
     * # 初始化二维数组，指定下标(i,j)存储值，代表P(i,j)是否为回文
     * # 初始化存储最终回文字符串的变量 result
     * # 创建双层循环，外层为i~j步长 从0到len-1，内层待检测字符串为起始下标i，终止下标j = i + step
     * # 确定判断内层循环终止条件 j >= len
     * # 然后 解决边界条件:
     * # 1 i=j 即 step == 0 时， P(i,j) = True
     * # 2 i=j+1 即 step == 1 时，P(i,j) = s[i]==s[j] ，即只有相同元素双字符串才成立
     * # 3 2 <= j-i < len -1 时，P(i,j) = P(i+1,j-1) & s[i]==s[j]，即当前字符串同时向左，向右收敛一步得到字符串也成立，且最左和最右元素相同时，成立
     * # 当前字符串成立，且大于已经存在符合条件的字符串，则将当前字符串赋值给寄存变量
     * # 双层循环完毕，输出最终寄存变量
     * # 时间复杂度o(n2)，双层循环，每层循环次数为n;
     * # 空间复杂度o(n2)，存储动态规划状态需要的空间 probs
     *
     * @param
     * @return
     */
    public String solve1(String text) {
        int size = text.length();
        String result = "";
        boolean[][] probs = new boolean[size][size];

        for (int step = 0; step < size; step++) {
            for (int i = 0; i < size; i++) {
                int j = i + step;
                if (j >= size) {
                    // 长度超限，停止搜索
                    break;
                }
                if (step == 0) {
                    // 单字符
                    probs[i][j] = true;
                } else if (step == 1) {
                    // 双字符
                    probs[i][j] = text.charAt(i) == text.charAt(j);
                } else {
                    // size >= 3
                    probs[i][j] = probs[i + 1][j - 1] && text.charAt(i) == text.charAt(j);
                }
                if (probs[i][j] && size + 1 > result.length()) {
                    result = text.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    private int[] centerExtand(String text, int left, int right) {
        while (left >= 0 && right < text.length() && text.charAt(left) == text.charAt(right)) {
            left -= 1;
            right += 1;
        }
        return new int[]{left + 1, right - 1};
    }


    @Test
    public void solve2Test(){
        for (String text : texts) {
            System.out.println(solve2(text));
        }
    }

    /**
     * # 中心扩散搜索
     * # 回文可能出现两种形式1.单核扩散；2.双核扩散
     * # 思路：
     * # 1.编写扩散搜索函数，入参为完整字符串，和初始起止搜索下标，当左侧或右侧抵达边界，或出现 左右不对称时，
     * # 结束搜索，就当前失败位置开始左右各收敛一位，然后返回本次搜索成果，即满足回文要求的最左最右边界
     * # 2.初始化存储最左，最右边界寄存器
     * # 3.完整串以逐个元素为中心，同时分别单核和双核回文范本，执行搜索，找到最长边界，然后返回
     * # 时间复杂度o(2n2)=o(n2) 双层循环
     * # 空间复杂度o(1)，2个寄存器
     *
     * @param
     * @return
     */
    public String solve2(String text) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < text.length(); i++) {
            int[] ints1 = centerExtand(text, i, i);
            int[] ints2 = centerExtand(text, i, i + 1);
            if (ints1[1] - ints1[0] > end - start) {
                end = ints1[1];
                start = ints1[0];
            }
            if (ints2[1] - ints2[0] > end - start) {
                end = ints2[1];
                start = ints2[0];
            }
        }
        return text.substring(start, end + 1);
    }


}
