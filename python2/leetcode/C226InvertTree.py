#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/12 20:04
Info:
翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/invert-binary-tree

"""

import os, sys
from collections import deque

def makeTree():
    n1 = TreeNode(1)
    n3 = TreeNode(3)
    n6 = TreeNode(6)
    n9 = TreeNode(9)

    n2 = TreeNode(2)
    n2.left = n1
    n2.right = n3

    n7 = TreeNode(7)
    n7.left = n6
    n7.right = n9

    n4 = TreeNode(4)
    n4.left = n2
    n4.right = n7

    return n4


class TreeNode(object):
    def __init__(self,val):
        self.val = val
        self.left = None
        self.right = None

# 递归
# 迭代从树顶节点开始，左边递归得到新右边，右边递归得到新左边，然后分别安置新左和新右，重新输出树顶(自身不变，左右互换)
# 时间复杂度o(n)，循环遍历所有节点
# 空间复杂度o(n), 树顶迭代时，扫描到所有节点
def solve1(tree):
    if tree == None:
        return None
    newRight = solve1(tree.left) # 翻转后得到的就是反向新的
    newLeft = solve1(tree.right)
    tree.right = newRight
    tree.left = newLeft
    return tree

# 迭代
# 申请有个任务队列，首先将当前节点加入启动循环遍历
# 每次遍历只将当前节点左右互换，然后在当前节点左右枝子节点（非空），分别又加入任务队列
# 迭代到根节点时，不会有新任务添加到队列，随着队列不断弹出新任务，最终收敛，结束迭代，此时树顶已经时完全翻转后的新树
# 时间复杂度o(n)，每个节点都被访问一次，
# 空间复杂度o(n)，顶点迭代时，会有近一半节点都加入队列 [n/2] = o(n)
def solve2(tree):
    nodes = deque()
    nodes.append(tree)
    while(nodes):
        node = nodes.pop()
        temp = node.left
        node.left = node.right
        node.right = temp
        if(node.left!=None):
            nodes.append(node.left)
        if(node.right!=None):
            nodes.append(node.right)
    return tree

def printTree(tree,level=0):
    if tree.left != None:
        print("%s%s->%s<-%s"%(level*"\t",tree.left.val,tree.val,tree.right.val))
        level += 1
        printTree(tree.left,level)
        printTree(tree.right,level)

if __name__ == "__main__":
    tree1 = makeTree()
    printTree(tree1)
    # tree2 = solve1(tree1)
    tree2 = solve2(tree1)
    printTree(tree2)
    
