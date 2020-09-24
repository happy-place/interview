package com.bigdata.flyweight;

/**
 * String 就是典型享元模式
 * 1.String类型对象一旦创建就维护到JVM常量池；
 * 2.直接初始化赋值时，直接从常量池引用；
 * 3.new 方式创建新对象时，先在栈中开辟了内存空间，然后指向常量池，外部引用的是栈内存空间；
 * 4.intern()方法，返回是是栈指向常量池的地址；
 */
public class StringObject {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "ab";
        String s3 = new String("ab");
        String s4 = new String("ab");

        System.out.println(s1==s2); // true
        System.out.println(s1==s3); // false
        System.out.println(s3==s4); // false
        System.out.println(s1==s3.intern()); // true
        System.out.println(s3.intern()==s4.intern()); // true

    }


}
