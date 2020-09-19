#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/16 06:59
Info:
给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
 

示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/edit-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""

import os, sys

# 动态规划
# 两个单词任意为空，则编辑距离就为非空字符串长度
# A,B 两个单词，分别执行一步(add,delete,replace)，可能存在情况有 2*3=6 种
# 实质上 对doge 删除一个g 与 对 dog 插入e 是等价的，排除等价操作，得到的可能操作只有3种
# 因此对A，B变换一步，得到相同字符串，的解法可以抽象为 A=horse B=ros
# 假设 horse -> ro 解法为 a ,则 horse -> ros 对A 插入s最小解法为 a+1 (A 后面加)
# 假设 hors -> ros 解法为 b ,则 horse -> ros 对B 插入e最小解法为 b+1 (B 后面加)
# 假设 hors -> ro 解法为 c ,则 horse -> ros  对A 变e为s最小解法为 c+1 (A修改尾)
# 因此 horse-ros 最短编辑距离为 min(a+1,b+1,c+1)
# dp[i][j-1] 为 A 的前 i个字符与B的前j-1个字符之间编辑距离，所以对于B截止到j下标，A的编辑距离，即在末尾加上 B[j] 即 dp[i][j] = dp[i][j-1] + 1
# dp[i-1][j] 为 A 的前 i-1个字符与B的前j个字符之间编辑距离，所以对于A截止到i下标，B的编辑距离，即在末尾加上 A[i] 即 dp[i][j] = dp[i-1][j] + 1
# dp[i-1][j-i] 为 A 的前 i-1个字符与B的前j-1个字符之间编辑距离，所以对于A截止到i下标，B的编辑距离
# 1.如果 A[i]=B[j], 什么都不用做 dp[i][j] = dp[i-1][j-1]
# 2.如果A[i]!=B[j]，对A做一次修改 dp[i][j] = dp[i-1][j-1] + 1
# 即最终 dp[i][j] = min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]) 或 min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+1)
# 时间复杂度 o(m*n) 双层循环
# 空间复杂度 o(m*n) 二维数组存储解
def solve1(word1,word2):
    m =  len(word1)
    n =  len(word2)

    # A,B存在空字符串，编辑距离直接等于对方字符串长度
    if m * n == 0:
        return m + n

    # 创建二维数组，代表编辑距离解析解，range 左闭右开
    dp = [[0]*(m+1) for _ in range(n+1)]

    for i in range(1,n+1):
        for j in range(1,m+1):
            left = dp[i-1][j] + 1 # A 增加 dp[i][j] = dp[i-1][j] + 1
            down = dp[i][j-1] + 1 # B 增加 dp[i][j] = dp[i][j-1] + 1
            left_down = dp[i-1][j-1] # A 修改A[i]=B[j]: dp[i][j] = dp[i-1][j-1]
            if word1[j-1] != word2[i-1]:
                left_down += 1 # A 修改A[i]!=B[j]: dp[i][j] = dp[i-1][j-1] + 1
            dp[i][j] = min(left,down,left_down)
    return dp[n][m]

def test(func):
    words = [('horse','ros')]
    for word1,word2 in words:
        print 'edit',word1,word2,func(word1,word2)

if __name__ == "__main__":
    test(solve1)

    pass
    
