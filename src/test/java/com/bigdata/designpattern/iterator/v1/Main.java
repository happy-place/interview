package com.bigdata.designpattern.iterator.v1;

public class Main {

    public static void main(String[] args) {
        ArrayList_ arrayList = new ArrayList_();
        for(int i=0;i<12;i++){
            arrayList.add(i);
        }
        System.out.println(arrayList.size());


        LinkedList_ linkedList = new LinkedList_();
        for(int i=0;i<12;i++){
            linkedList.add(i);
        }
        System.out.println(linkedList.size());
    }


}
