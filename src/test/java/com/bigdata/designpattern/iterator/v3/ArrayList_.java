package com.bigdata.designpattern.iterator.v3;

public class ArrayList_ implements Collection_ {

    Object[] objects = new Object[10];
    private int index = 0;
    Iterator_ iterator = new ArrayListIterator();

    @Override
    public int size(){
        return index;
    }

    @Override
    public void add(Object o){
        if(index==objects.length){
            // 容器装满后，直接两倍扩容
            Object[] newObjects = new Object[objects.length*2];
            System.arraycopy(objects,0,newObjects,0,objects.length);
            objects = newObjects;
        }
        objects[index] = o;
        index ++;
    }

    @Override
    public Iterator_ iterator() {
        return iterator;
    }

    private class ArrayListIterator implements Iterator_{
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex<index && index>0;
        }

        @Override
        public Object next() {
            return objects[currentIndex++];
        }
    }

}


