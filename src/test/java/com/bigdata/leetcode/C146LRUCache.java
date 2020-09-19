package com.bigdata.leetcode;

import org.junit.Test;
import sun.misc.LRUCache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName C146LRUCache
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/13 16:58
 * @Version 1.0
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 *
 * LRUCache cache = new LRUCache( 2/ ); //缓存容量
* cache.put(1,1);
* cache.put(2,2);
* cache.get(1);       // 返回  1
* cache.put(3,3);    // 该操作会使得关键字 2 作废
* cache.get(2);       // 返回 -1 (未找到)
* cache.put(4,4);    // 该操作会使得关键字 1 作废
* cache.get(1);       // 返回 -1 (未找到)
* cache.get(3);       // 返回  3
* cache.get(4);       // 返回  4

 **/


/**
 * 双向链表 + hash
 * 时间复杂度 o(1)
 * 空间复杂度 o(capacity+1)
 */
class LRUCache1 {

    private int capacity = 2;
    private int size = 0;
    private Map<Object, LinkedNode> cache = null;
    private LinkedNode head = null;
    private LinkedNode tail = null;

    public LRUCache1() {
        this.cache = new HashMap<>();
        this.head = new LinkedNode(null, null);
        this.tail = new LinkedNode(null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public LRUCache1(int capacity) {
        this();
        this.capacity = capacity;
    }

    public Object get(Object key) {
        Object result = null;
        if (cache.containsKey(key)) {
            LinkedNode node = cache.get(key);
            result = node.value;
            moveToHead(node);
        }
        return result;
    }

    public void put(Object key, Object value) {
        if (cache.containsKey(key)) {
            LinkedNode node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            LinkedNode node = new LinkedNode(key, value);
            cache.put(key,node);
            addToHead(node);
            if (size > capacity) {
                cache.remove(removeTail());
            }
        }
    }

    private void moveToHead(LinkedNode node) {
        remove(node);
        addToHead(node);
    }

    private void addToHead(LinkedNode node) {
        LinkedNode second = head.next;
        node.prev = head;
        node.next = second;
        second.prev = node;
        head.next = node;
        size ++;
    }

    private Object removeTail() {
        LinkedNode prev = tail.prev;
        remove(prev);
        return prev.key;
    }

    private void remove(LinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size --;
    }

    class LinkedNode {
        Object key = null;
        Object value = null;
        LinkedNode prev = null;
        LinkedNode next = null;

        public LinkedNode(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * 基于 LinkedHashMap 自带容量约束，复写 过期函数 removeEldestEntry
 */
class LRUCache2 extends LinkedHashMap<Object,Object> {

    int capacity = 2;

    public LRUCache2(int capacity) {
        // accessOrder 维持顺序
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    @Override
    public Object get(Object key) {
        return super.getOrDefault(key,null);
    }

    @Override
    public Object put(Object key, Object value) {
        return super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
        return size()>capacity;
    }
}


public class C146LRUCache {

    @Test
    public void solve1Test(){
        LRUCache1 lruCache = new LRUCache1(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));// 刚满，可以取出
        lruCache.put(3, 3); // 挤出一个，由于2最近使用最少，所以挤出2
        System.out.println(lruCache.get(2)); // 取不到 null
        lruCache.put(4, 4); // 挤出一个，3 是刚存进去的，所以1被挤出
        System.out.println(lruCache.get(1)); // 取不到 null
        System.out.println(lruCache.get(3)); // 取出3 （且3被前置）
        System.out.println(lruCache.get(4)); // 取出4 （且4被前置）
    }

    @Test
    public void solve2Test(){
        LRUCache2 lruCache = new LRUCache2(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));// 刚满，可以取出
        lruCache.put(3, 3); // 挤出一个，由于2最近使用最少，所以挤出2
        System.out.println(lruCache.get(2)); // 取不到 null
        lruCache.put(4, 4); // 挤出一个，3 是刚存进去的，所以1被挤出
        System.out.println(lruCache.get(1)); // 取不到 null
        System.out.println(lruCache.get(3)); // 取出3 （且3被前置）
        System.out.println(lruCache.get(4)); // 取出4 （且4被前置）
    }


}
