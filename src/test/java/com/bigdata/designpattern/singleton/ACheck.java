package com.bigdata.designpattern.singleton;

import org.junit.Test;

public class ACheck {

    @Test
    public void test(){
        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(HungrySingleton.getInstance().hashCode());
//                    System.out.println(LazySingleton.getInstance0().hashCode());
//                    System.out.println(LazySingleton.getInstance1().hashCode());
//                    System.out.println(LazySingleton.getInstance2().hashCode());
//                    System.out.println(InnerStaticClassSingleton.getInstance().hashCode());
                    System.out.println(InnerEnumSingleton.INSTANCE.hashCode());
//                    InnerEnumSingleton.INSTANCE.sayHi();
                }
            }).start();

            // java 8 支持 lambda 表达式简写代码块
//            new Thread(()-> System.out.println(HungrySingleton.getInstance().hashCode())).start();
        }
    }

}
