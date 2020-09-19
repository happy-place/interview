#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/13 20:53
Info:
5. 最长回文子串
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
"""

import os, sys

# 动态规划
# P(i,j) = True / False 表示 i~j 字符串是回文 或 不是
# s[i] 表示下标为元素
# P(i,j) = True 满足前提条件是 P(i+1,j-1) & s[i] == s[j]
# 边界条件：
# 1. i=j 单字符，任何时候都成立;
# 2. j = i+1 双字符，只有当 s[i]=s[j] 时成立；
# 3. 2 <= j-i < len - 1 ，只有当 P(i+1,j-1) & s[i]==s[j] 是成立
# 初始化二维数组，指定下标(i,j)存储值，代表P(i,j)是否为回文
# 初始化存储最终回文字符串的变量 result
# 创建双层循环，外层为i~j步长 从0到len-1，内层待检测字符串为起始下标i，终止下标j = i + step
# 确定判断内层循环终止条件 j >= len
# 然后 解决边界条件:
# 1 i=j 即 step == 0 时， P(i,j) = True
# 2 i=j+1 即 step == 1 时，P(i,j) = s[i]==s[j] ，即只有相同元素双字符串才成立
# 3 2 <= j-i < len -1 时，P(i,j) = P(i+1,j-1) & s[i]==s[j]，即当前字符串同时向左，向右收敛一步得到字符串也成立，且最左和最右元素相同时，成立
# 当前字符串成立，且大于已经存在符合条件的字符串，则将当前字符串赋值给寄存变量
# 双层循环完毕，输出最终寄存变量
# 时间复杂度o(n2)，双层循环，每层循环次数为n;
# 空间复杂度o(n2)，存储动态规划状态需要的空间 probs
def solve1(text):
    size = len(text)
    result = ""
    probs = [[False]*size for _ in range(size)]
    for step in range(size):
        for i in range(size):
            j = i+ step
            if j >= size:
                break
            if step == 0:
                # 步长为0，单字符
                probs[i][j] = True
            elif step == 1:
                probs[i][j] = text[i] == text[j]
            else:
                probs[i][j] = probs[i+1][j-1] and text[i]==text[j]

            if probs[i][j] and step + 1 > len(result):
                result = text[i:j+1]
    return result

# 中心扩散搜索
# 回文可能出现两种形式1.单核扩散；2.双核扩散
# 思路：
# 1.编写扩散搜索函数，入参为完整字符串，和初始起止搜索下标，当左侧或右侧抵达边界，或出现 左右不对称时，
# 结束搜索，就当前失败位置开始左右各收敛一位，然后返回本次搜索成果，即满足回文要求的最左最右边界
# 2.初始化存储最左，最右边界寄存器
# 3.完整串以逐个元素为中心，同时分别单核和双核回文范本，执行搜索，找到最长边界，然后返回
# 时间复杂度o(2n2)=o(n2) 双层循环
# 空间复杂度o(1)，2个寄存器
def solve2(text):

    def center_extand(s,left,right):
        while left >= 0 and right <len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return left+1,right - 1

    start = end = 0
    for i in range(len(text)):
        left1,right1 = center_extand(text, i, i)
        left2,right2 = center_extand(text, i, i+1)
        if right1 - left1 > end - start:
            start,end = left1,right1
        if right2 - left2 > end - start:
            start,end = left2,right2

    return text[start:end+1]


if __name__ == "__main__":
    text = "a"
    text = "aa"
    text = "bcdca"
    # result = solve1(text)
    result = solve2(text)
    print(result)





