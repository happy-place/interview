#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/13 11:59
Info:

"""

import os, sys

# 双向链表 + hash
# 时间复杂度 o(1)
# 空间复杂度 o(capacity+1)
class LRUCache:
    # 双向链表存储维护使用频率
    class LinkedNode:
        def __init__(self,key=None,value=None):
            self.key = key
            self.value = value
            self.prev = None
            self.next = None

    def __init__(self,capacity=2):
        self.capacity = capacity
        self.size = 0 # 当前容量
        self.cache = dict() # hash 存储
        self.head = LRUCache.LinkedNode() # 链头(标记位 实际不存数据)
        self.tail = LRUCache.LinkedNode() # 链尾(标记位 实际不存数据)
        self.head.next = self.tail # 链头绑定链尾
        self.tail.prev = self.head # 链尾绑定链头

    def get(self,key):
        result = None
        if key in self.cache:
            node = self.cache[key]
            result = node.value
            self._move_to_head(node) # 使用过的往链头移动
        return result

    def put(self,key,value):
        if key in self.cache:
            node = self.cache[key]
            node.value = value
            self._move_to_head(node) # 刚刷新过的往链头移动
        else:
            node = LRUCache.LinkedNode(key, value)
            self._add_to_head(node) # 刚添加的往链头移动
            self.cache[key] = node
            if self.size > self.capacity:
                key = self._remove_tail() # 容量超限的，从尾部开始清除
                self.cache.pop(key) # 注意同步删除数据

    def _remove_node(self,node):
        node.next.prev = node.prev # 下个节点的前，指向当前节点的前
        node.prev.next = node.next # 上个节点的后，指向当前节点的后
        self.size -= 1

    def _remove_tail(self):
        node = self.tail.prev # 名义上是删除 tail，其实是删除倒数第二个
        self._remove_node(node) # 执行删除
        return node.key # 抛出 key ，供hash删除

    def _add_to_head(self,node):
        # 名义上是添加到头，实质是添加到第二位
        second = self.head.next # 先标记旧二
        node.prev = self.head # 新二向前绑定头
        node.next = second # 新二想后绑定旧二
        self.head.next = node # 头向后绑定新二
        second.prev = node # 旧二向前绑定新二
        self.size += 1

    def _move_to_head(self,node):
        self._remove_node(node) # 先删除
        self._add_to_head(node) # 然后重新添加到头

# 方案2 python3/C146LRUCache.py 继承 OrderDict

if __name__ == "__main__":
    lru_cache = LRUCache(2)
    lru_cache.put(1,1)
    lru_cache.put(2,2)
    print(lru_cache.get(1)) # 刚满，可以取出
    lru_cache.put(3,3) # 挤出一个，由于2最近使用最少，所以挤出2
    print(lru_cache.get(2)) # 取不到 -1
    lru_cache.put(4,4) # 挤出一个，3 是刚存进去的，所以1被挤出
    print(lru_cache.get(1)) # 取不到 -1
    print(lru_cache.get(3)) # 取出3 （且3被前置）
    print(lru_cache.get(4)) # 取出4 （且4被前置）
