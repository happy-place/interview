package com.bigdata.designpattern.prototype.light;


public class Person implements Cloneable{
    public int age = 8;
    public int score = 100;

    public Location loc = new Location("bj", 22);

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", score=" + score +
                ", loc=" + loc +
                '}';
    }
}
