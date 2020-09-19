package com.bigdata.sort;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseSort {
    protected static Logger logger = Logger.getLogger(BaseSort.class);

    protected int[] arr = {5,8,5,2,9};

    @Before
    public void before(){
        logger.info(Arrays.toString(arr));
    }

    @After
    public void after(){
        logger.info(Arrays.toString(arr));
    }

    protected static void swap(int[] arr,int i,int j){
        traceArr(arr,i,j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    protected static void traceArr(int[] arr,int m,int n){
        List<String> list = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(i == m || i==n){
                list.add("("+arr[i]+")");
            }else{
                list.add(arr[i]+"");
            }

        }
        logger.debug(String.join(",",list));
    }
}
