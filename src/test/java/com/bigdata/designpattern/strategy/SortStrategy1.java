package com.bigdata.designpattern.strategy;

import java.util.Arrays;

class Cat1 implements Comparable<Cat1>{
    String name;
    int weight;
    int length;

    public Cat1(String name, int weight, int length) {
        this.name = name;
        this.weight = weight;
        this.length = length;
    }

    @Override
    public int compareTo(Cat1 o) {
        return (int)(this.length - o.length);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", length=" + length +
                '}';
    }
}

/**
 * 被比较对象实现Comparable接口或Comparator接口，深度耦合(写死了比较逻辑)，对后期拓展不利
 */
public class SortStrategy1 {

    static Cat1[] cats = new Cat1[]{
            new Cat1("A",10,21),
            new Cat1("C",11,23),
            new Cat1("B",12,22)
    };

    public static void main(String[] args) {
        sort(cats);
        System.out.println(Arrays.toString(cats));
    }

    public static void sort(Cat1[] arr){
        int length = arr.length;
        for(int i=0;i<length-1;i++){
            for(int j=0;j<length-1-i;j++){
                if(arr[j].compareTo(arr[j+1])>0){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    protected static void swap(Cat1[] arr, int i, int j){
        Cat1 temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
