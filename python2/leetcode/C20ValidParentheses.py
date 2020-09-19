#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/13 23:14
Info:
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""

import os, sys
from collections import deque

# 压栈法
# 思路：创建左右括号hash字典，遍历字符串，当遇到左括号时，将对应右括号压入栈，反之，则验证弹栈对象与当前字符是否一致，如果不一致，则返回False
# 为避免一开始就弹栈异常，在栈底压入占位符 * ，当循环结束，如果栈底只剩下占位符，则说明压栈顺序与弹栈顺序一致，是合法的，否则是非法的
# 时间复杂度 o(n)，遍历所有元素，每个元素被检查一次
# 空间复杂度o(n+c)，栈中可能压入元素个数为n，栈空间复杂度为o(n)，hash存储元素个数为常量 o(c)，c 为字典数
def solve1(text):
    qutos = {'(':')','{':'}','[':']'}
    stack = deque()
    stack.append("*") # 避免一开始就是右括号
    for c in list(text):
        if c in qutos:
            stack.append(qutos[c])
        elif stack.pop()!=c:
            return False
    return len(stack)==1

def test_deq():
    stack = deque()
    stack.append("*")
    stack.append("?")
    stack.append("&")
    while stack:
        print stack.pop()


if __name__ == "__main__":
    # test_deq()
    text = '()'
    print solve1(text)
    text = '()[]{}'
    print solve1(text)
    text = '(]'
    print solve1(text)
    text = '([)]'
    print solve1(text)
    text = '{[]}'
    print solve1(text)


    pass    
    
