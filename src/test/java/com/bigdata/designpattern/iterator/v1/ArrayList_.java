package com.bigdata.designpattern.iterator.v1;

/**
 * 模拟：顺序存储集合
 */
public class ArrayList_ {

    Object[] objects = new Object[10];
    private int index = 0;

    public int size(){
        return index;
    }

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

}


