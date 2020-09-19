package com.bigdata.designpattern.responsibilitychain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

interface Filter2{
    boolean doFilter(Message msg);
}

class FaceFilter2 implements Filter2{

    @Override
    public boolean doFilter(Message msg) {
        String message = msg.getMessage();
        message = message.replace("):", "^v^");
        msg.setMessage(message);
        return true;
    }
}

class BodyFilter2 implements Filter2{

    @Override
    public boolean doFilter(Message msg) {
        String message = msg.getMessage();
        message = message.replace("somebody", "everybody");
        msg.setMessage(message);
        return true;
    }
}

class NetFilter2 implements Filter2{

    @Override
    public boolean doFilter(Message msg) {
        String message = msg.getMessage();
        message = message.replace("xxx.com", "http://xxx.com");
        msg.setMessage(message);
        return false;
    }
}

class ClosingFilter2 implements Filter2{

    @Override
    public boolean doFilter(Message msg) {
        String message = msg.getMessage();
        message = message + ".";
        msg.setMessage(message);
        return true;
    }
}

class FilterChain4 implements Filter2 {
    private List<Filter2> chain = new ArrayList<>();

    public FilterChain4 addFilter(Filter2 filter){
        chain.add(filter);
        return this;
    }

    public boolean doFilter(Message msg){
        for(Filter2 filter:chain){
            if(!filter.doFilter(msg)){
                break;
            }
        }
        return true;
    }
}



public class Filter06 {

    private Message msg = new Message("): hello somebody, welcome to xxx.com");

    /**
     * 重写Filter2接口，chain 根据 doFilter 返回布尔值，决定是否 终止循环，且chain 始终返回true
     * 不足：请求 与 响应 混在一起
     */
    @Test
    public void test(){
        FilterChain4 filterChain = new FilterChain4();
        FilterChain4 extraChain = new FilterChain4().addFilter(new ClosingFilter2());

        filterChain.addFilter(new FaceFilter2()).addFilter(new BodyFilter2()).addFilter(new NetFilter2()).addFilter(extraChain);

        filterChain.doFilter(msg);

        System.out.println(msg);
    }

}
