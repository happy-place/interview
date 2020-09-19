package com.bigdata.designpattern.responsibilitychain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

interface Filter{
    void doFilter(Message msg);
}

class FaceFilter implements Filter{

    @Override
    public void doFilter(Message msg) {
        String message = msg.getMessage();
        message = message.replace("):", "^v^");
        msg.setMessage(message);
    }
}

class BodyFilter implements Filter{

    @Override
    public void doFilter(Message msg) {
        String message = msg.getMessage();
        message = message.replace("somebody", "everybody");
        msg.setMessage(message);

    }
}

class NetFilter implements Filter{

    @Override
    public void doFilter(Message msg) {
        String message = msg.getMessage();
        message = message.replace("xxx.com", "http://xxx.com");
        msg.setMessage(message);
    }
}

public class Filter02 {

    private Message msg = new Message("): hello somebody, welcome to xxx.com");

    /**
     * 抽象接口，使用集合管理接口实现类
     */
    @Test
    public void test(){
        List<Filter> filters = new ArrayList<>();
        filters.add(new NetFilter());
        filters.add(new BodyFilter());
        filters.add(new FaceFilter());

        for(Filter filter:filters){
            filter.doFilter(msg);
        }

        System.out.println(msg);
    }

}
