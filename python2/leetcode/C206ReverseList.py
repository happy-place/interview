#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/13 06:36
Info:
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-linked-list
"""

import os, sys

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

# 迭代法
# 从链头开始循环，每次循环，将当前节点的下个节点先取出，然后将当前下个节点位置赋值为上轮循环寄存的节点，然后吧当前节点寄存，并将遍历引用拨到下一个节点
# 时间复杂度为 o(n) 逐个遍历
# 空间复杂度为 o(1) 只申请一个临时存储空间
def solve1(head):
    # 申请临时空间，保存当前节点，在下次循环中使用
    pre = None
    # 链尾为None
    while(head!=None):
        # 取出下个节点备用
        nextTemp = head.next
        # 上次循环保留的节点(上个节点) 交给下个节点
        head.next = pre
        # 当前节点交给，临时变量，传递到下次循环
        pre = head
        # 当前节点拨到下个节点
        head = nextTemp
    return pre

# 递归法
# n1->n2->...->nk->nk+1->...->nm
# 假设迭代到nk节点，即 nk为head，则 nk 以后的 nk+1->...->nm 交给递归处理 得到了想要顺序 solve2(head.next)
# nk 要加入新顺序需要满足 nk.next.next=nk，即 head.next.next=head
# nk 顺序调整完毕，切断 nk 与 之前下个节点 nk+1 的联系，即 head.next = None
# 抛出新链头 p
# if head is None or head.next is None: return head 是为了保证 只有两个节点时链表陷入死循环
# 时间复杂度o(n) 遍历所有元素
# 空间复杂度o(n) 每次遍历，递归搜索一次 递归会使用到隐式栈空间，递归深度可能会达到n层
def solve2(head):
    if head is None or head.next is None:
        return head
    p = solve2(head.next)
    head.next.next = head
    head.next = None
    return p

def makeList(n):
    nodes = []
    for i in range(n):
        nodes.append(ListNode(i))
    for i in range(n-1):
        nodes[i].next = nodes[i+1]
    return nodes[0]

def printList(head):
    temp = []
    while(head!=None):
        temp.append(head.val)
        head = head.next
    print(temp)

if __name__ == "__main__":
    head = makeList(2)
    printList(head)
    # res = solve1(head)
    res = solve2(head)
    printList(res)
