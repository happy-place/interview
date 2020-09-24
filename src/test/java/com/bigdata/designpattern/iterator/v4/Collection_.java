package com.bigdata.designpattern.iterator.v4;

public interface Collection_<E> {
    void add(E o);
    int size();
    Iterator_<E> iterator();
}
