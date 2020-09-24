package com.bigdata.designpattern.iterator.v3;

public class LinkedList_ implements Collection_ {
    private Node head;
    private Node tail;

    private int size;

    Iterator_ iterator = new LinkedListIterator();

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

    @Override
    public Iterator_ iterator() {
        return iterator;
    }

    private class LinkedListIterator implements Iterator_{
        private Node currentNode;
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex<size && size>0;
        }

        @Override
        public Object next() {
            Node result = null;
            if(currentNode==null){
                currentNode = head;
            }
            result = currentNode;
            currentNode = currentNode.next;
            currentIndex ++;
            return result.o;
        }
    }




}
