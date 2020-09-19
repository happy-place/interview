#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: HuHao <whohow20094702@163.com>
Date: 2020/8/13 11:59
Info:
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。
当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

进阶:

你是否可以在 O(1) 时间复杂度内完成这两种操作？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lru-cache

LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得关键字 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得关键字 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lru-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""

import os, sys
from collections import OrderedDict

class LRUCache(OrderedDict):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        result = -1
        if key in self:
            self.move_to_end(key) # 只有 python3 的OrderDict 具有 move_to_end
            result = self[key]
        return result

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self:
            self.move_to_end(key)
        self[key] = value
        if len(self)>self.capacity:
            self.popitem(last=False)


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
