package com.bigdata.designpattern.prototype.deep;


public class Person implements Cloneable{
    int age = 8;
    int score = 100;

    public Location loc = new Location("bj", 22);

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person p = (Person)super.clone();
        p.loc = (Location)loc.clone();
        return p;
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
