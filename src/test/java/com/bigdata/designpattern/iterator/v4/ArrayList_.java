package com.bigdata.designpattern.iterator.v4;


public class ArrayList_<E> implements Collection_<E> {

    E[] objects = (E[])new Object[10];
    private int index = 0;
    Iterator_<E> iterator = new ArrayListIterator();

    @Override
    public int size(){
        return index;
    }

    @Override
    public void add(E o){
        if(index==objects.length){
            // 容器装满后，直接两倍扩容
            E[] newObjects = (E[])new Object[objects.length*2];
            System.arraycopy(objects,0,newObjects,0,objects.length);
            objects = newObjects;
        }
        objects[index] = o;
        index ++;
    }

    @Override
    public Iterator_<E> iterator() {
        return iterator;
    }

    private class ArrayListIterator implements Iterator_<E> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex<index && index>0;
        }

        @Override
        public E next() {
            return objects[currentIndex++];
        }
    }

}


