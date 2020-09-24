package com.bigdata.designpattern.iterator.v4;

public class LinkedList_<E> implements Collection_<E> {
    private Node head;
    private Node tail;

    private int size;

    Iterator_ iterator = new LinkedListIterator();

    @Override
    public void add(E o){
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

    private class Node<E> {
        private E o;
        Node next;

        public Node(E o) {
            this.o = o;
        }
    }

    @Override
    public Iterator_<E> iterator() {
        return iterator;
    }

    private class LinkedListIterator implements Iterator_<E> {
        private Node currentNode;
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex<size && size>0;
        }

        @Override
        public E next() {
            Node<E> result = null;
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
