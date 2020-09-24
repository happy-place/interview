package com.bigdata.designpattern.iterator.v1;

/**
 * 模拟跳跃存储集合
 */
public class LinkedList_ {
    private Node head;
    private Node tail;

    private int size;

    public void add(Object o){
        Node node = new Node(o);
        node.next = null;
        if(head==null){
            head = node;
            tail = node;
        }
        // 旧尾链接到新尾
        tail.next = node;
        // 指定新尾
        tail = node;
        size ++;
    }

    public int size() {
        return size;
    }

    private class Node {
        private Object o;
        Node next;

        public Node(Object o) {
            this.o = o;
        }
    }


}
