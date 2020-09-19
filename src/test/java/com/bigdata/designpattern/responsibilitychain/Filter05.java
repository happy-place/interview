package com.bigdata.designpattern.responsibilitychain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class FilterChain3 implements Filter {
    private List<Filter> chain = new ArrayList<>();

    public FilterChain3 addFilter(Filter filter){
        chain.add(filter);
        return this;
    }

    public void doFilter(Message msg){
        for(Filter filter:chain){
            filter.doFilter(msg);
        }
    }
}

public class Filter05 {

    private Message msg = new Message("): hello somebody, welcome to xxx.com");

    /**
     * Chain 自身实现Filter 接口，方便 Chain 合并 以及连续调用
     * 不足：不能中间中断 chain 调用
     */
    @Test
    public void test(){
        FilterChain3 filterChain = new FilterChain3();
        FilterChain3 extraChain = new FilterChain3().addFilter(new ClosingFilter());

        filterChain.addFilter(new FaceFilter()).addFilter(new BodyFilter()).addFilter(new NetFilter()).addFilter(extraChain);

        filterChain.doFilter(msg);

        System.out.println(msg);
    }

}
