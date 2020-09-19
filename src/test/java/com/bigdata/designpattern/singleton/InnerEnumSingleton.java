package com.bigdata.designpattern.singleton;

/**
 * 枚举类实现单例
 * 1.枚举类没有构造器，天生就是单例；
 * 2.由于没有构造器，能避免通过反射，调用被限制访问的构造器，生成新对象，从而破坏单例限制；
 * 3.需要扩充方法时，直接在对象中定义，然后在枚举类外部抽象此方法，就可以在外面引用
 * 是 effective-java 中被推荐的单例，能避免反射破坏单例原则
 */
public enum InnerEnumSingleton {

    INSTANCE {
        public void sayHi(){
            System.out.println("hi");
        }
    };

    public abstract void sayHi();

}
