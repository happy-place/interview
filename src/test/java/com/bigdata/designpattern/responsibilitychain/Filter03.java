package com.bigdata.designpattern.responsibilitychain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class FilterChain {
    private List<Filter> chain = new ArrayList<>();

    public void addFilter(Filter filter){
        chain.add(filter);
    }

    public void doFilter(Message msg){
        for(Filter filter:chain){
            filter.doFilter(msg);
        }
    }
}

public class Filter03 {

    private Message msg = new Message("): hello somebody, welcome to xxx.com");

    /**
     * 添加Filter 和批量调用doFilter 操作封装到Chain
     */
    @Test
    public void test(){
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new FaceFilter());
        filterChain.addFilter(new BodyFilter());
        filterChain.addFilter(new NetFilter());
        filterChain.doFilter(msg);
        System.out.println(msg);
    }

}
