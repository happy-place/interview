package com.bigdata.designpattern.adapter;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;

        try {
            fis = new FileInputStream(new File("")); // 字节流
            isr = new InputStreamReader(fis);  /// 转换器
            bufferedReader = new BufferedReader(isr); // 字符流

            String line = bufferedReader.readLine(); // 按行读取
            while(line!=null && !line.equals("")){
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(isr!=null){
                isr.close();
            }
            if(fis!=null){
                fis.close();
            }
        }

    }

}
