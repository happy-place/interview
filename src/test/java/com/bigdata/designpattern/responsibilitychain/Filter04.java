package com.bigdata.designpattern.responsibilitychain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class FilterChain2 {
    private List<Filter> chain = new ArrayList<>();

    public FilterChain2 addFilter(Filter filter){
        chain.add(filter);
        return this;
    }

    public void doFilter(Message msg){
        for(Filter filter:chain){
            filter.doFilter(msg);
        }
    }
}

class ClosingFilter implements Filter{

    @Override
    public void doFilter(Message msg) {
        String message = msg.getMessage();
        message = message + ".";
        msg.setMessage(message);
    }
}

public class Filter04 {

    private Message msg = new Message("): hello somebody, welcome to xxx.com");

    /**
     * Chain 中的 addFilter 返回自身，实现连缀操作
     * 存在问题：多条chain 如何合并，连续调用？
     */
    @Test
    public void test(){
        FilterChain2 filterChain1 = new FilterChain2();
        FilterChain2 filterChain2 = new FilterChain2();

        filterChain1.addFilter(new FaceFilter()).addFilter(new BodyFilter()).addFilter(new NetFilter());
        filterChain2.addFilter(new ClosingFilter());

        filterChain1.doFilter(msg);
        filterChain2.doFilter(msg);

        System.out.println(msg);
    }

}
