package com.bigdata.designpattern.strategy;

import java.util.Arrays;

class Cat2{
    String name;
    int weight;
    int length;

    public Cat2(String name, int weight, int length) {
        this.name = name;
        this.weight = weight;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Cat2{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", length=" + length +
                '}';
    }
}

interface Comparator<T>{
    int compare(T t1,T t2);
}

class WeightComparator implements Comparator<Cat2>{
    @Override
    public int compare(Cat2 t1, Cat2 t2) {
        return t1.weight-t2.weight;
    }
}

class LengthComparator implements Comparator<Cat2>{
    @Override
    public int compare(Cat2 t1, Cat2 t2) {
        return t1.length-t2.length;
    }
}

/**
 * 抽象比较接口
 * 分别基于不同策略实现不同比较逻辑，保证开闭原则
 */
public class SortStrategy2 {

    static Cat2[] cats = new Cat2[]{
            new Cat2("A",10,21),
            new Cat2("C",11,23),
            new Cat2("B",12,22)
    };

    public static void main(String[] args) {
        sort(cats,new WeightComparator());
        System.out.println(Arrays.toString(cats));
        sort(cats,new LengthComparator());
        System.out.println(Arrays.toString(cats));
    }

    public static void sort(Cat2[] arr,Comparator<Cat2> comp){
        int length = arr.length;
        for(int i=0;i<length-1;i++){
            for(int j=0;j<length-1-i;j++){
                if(comp.compare(arr[j],arr[j+1])>0){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    protected static void swap(Cat2[] arr, int i, int j){
        Cat2 temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
