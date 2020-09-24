package com.bigdata.designpattern.iterator.v2;

public class LinkedList_ implements Collection_{
    private Node head;
    private Node tail;

    private int size;

    @Override
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

    @Override
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
