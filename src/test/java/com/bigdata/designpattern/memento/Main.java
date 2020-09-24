package com.bigdata.designpattern.memento;

import java.io.*;

class MyThread extends Thread implements Serializable {

    int count = 0;

    @Override
    public void run() {
        while(count<1000){
            System.out.println(count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count ++;
        }
    }
}


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "/Users/huhao/softwares/idea_proj/interview/src/test/resources/designpattern.memento/thread.data";
        MyThread thread = load(path);
        if(thread==null){
            thread = new MyThread();
            thread.setName("count-thread");
            thread.start();
        }

        Thread.sleep(10000);
        save(thread,path);

        thread.stop();
        Thread.sleep(3000);

        thread = load(path);
        thread.start();
        Thread.sleep(10000);

    }

    public static void stopTest() throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(10000);
        thread.stop();
    }

    public static void save(MyThread thread,String path){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(thread);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static MyThread load(String path){
        ObjectInputStream ois = null;
        MyThread thread = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            thread = (MyThread)ois.readObject();
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return thread;
        }
    }



}
