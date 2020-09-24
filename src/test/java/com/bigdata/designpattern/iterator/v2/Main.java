package com.bigdata.designpattern.iterator.v2;


public class Main {

    public static void main(String[] args) {
        test(new ArrayList_());
        test(new LinkedList_());
    }

    public static void test(Collection_ collection){
        for(int i=0;i<12;i++){
            collection.add(i);
        }
        System.out.println(collection.size());
    }


}
