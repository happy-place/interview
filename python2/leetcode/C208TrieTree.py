#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/15 20:27
Info:
208. 实现 Trie (前缀树)
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   # 返回 true
trie.search("app");     # 返回 false
trie.startsWith("app"); # 返回 true
trie.insert("app");
trie.search("app");     # 返回 true
说明:

你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。
https:#leetcode-cn.com/problems/implement-trie-prefix-tree/

"""

import os, sys

# 前缀树节点
# 每个节点保存向下连接的26中可能性
# 内部维护数组结构，由于26个字母是有序的，因此可以基于下标实现类型hash效果
# 使用 bool 变量标记当前节点是否是单词尾节点
class TrieNode(object):
    def __init__(self):
        self.probs = 26
        self.links = [None] * self.probs
        self.is_end = False
    
    def __getitem__(self, ch):
        # ord 字符转换为 ascii
        return self.links[ord(ch)-ord('a')]

    def __setitem__(self, ch, node):
        self.links[ord(ch)-ord('a')] = node
        
    def __contains__(self,ch):
        return self.links[ord(ch)-ord('a')]!=None
    
    def set_end(self):
        self.is_end = True

# 前缀树 （多杈树）
# 使用场景:
# 1.搜索框推荐
# 2.语法检测
# 3.IP最长前缀匹配
# 4.输入法预测
# 5.单词游戏
# hash 结构由于存在碰撞，n取余无穷大时，时间复杂度为 o(n)，
# trie 时间复杂度为o(n)，相同前缀存储时占用更少空间，
# 平衡二叉树 时间复杂度 o(mlogn)
class Trie(object):
        
    def __init__(self):
        self.root = TrieNode()
        
    def insert(self, word):
        current_node = self.root
        for ch in word:
            if ch not in current_node:
                current_node[ch] = TrieNode()
            current_node = current_node[ch]
        current_node.set_end()

    def search_max_prefix(self,word):
        current_node = self.root
        for ch in word:
            if ch in current_node:
                current_node = current_node[ch]
            else:
                return None
        return current_node
        
    def search(self, word):
        max_prefix = self.search_max_prefix(word)
        return bool(max_prefix and max_prefix.is_end)

    def starts_with(self, word):
        max_prefix = self.search_max_prefix(word)
        return bool(max_prefix)

def solve1():
    trie = Trie()
    trie.insert("apple")
    print(trie.search("apple"))  # 返回 true
    print(trie.search("app"))    # 返回 false
    print(trie.starts_with("app")) # 返回 true
    trie.insert("app")
    print(trie.search("app"))     # 返回 true


if __name__ == "__main__":
    solve1()
