package com.bigdata.designpattern.responsibilitychain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


class Request{
    private String message;

    public Request(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Request{" +
                "message='" + message + '\'' +
                '}';
    }
}


class Response{
    private String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                '}';
    }
}

interface Filter3 {
    void doFilter(Request request, Response response, FilterChain5 chain);
}

class FaceFilter3 implements Filter3 {

    @Override
    public void doFilter(Request request, Response response, FilterChain5 chain) {
        String message = request.getMessage();
        message = message.replace("):", "^v^");
        request.setMessage(message);

        chain.doFilter(request,response,true);

        message = response.getMessage().replace("):", "^v^2");
        response.setMessage(message);
    }
}

class BodyFilter3 implements Filter3 {

    @Override
    public void doFilter(Request request, Response response, FilterChain5 chain) {
        String message = request.getMessage();
        message = message.replace("somebody", "everybody");
        request.setMessage(message);

        chain.doFilter(request,response,true);

        message = response.getMessage().replace("somebody", "everybody2");
        response.setMessage(message);
    }
}

class NetFilter3 implements Filter3 {

    @Override
    public void doFilter(Request request, Response response, FilterChain5 chain) {
        String message = request.getMessage();
        message = message.replace("somebody", "everybody");
        message = message.replace("xxx.com", "http://xxx.com");
        request.setMessage(message);

        chain.doFilter(request,response,true);

        message = response.getMessage().replace("xxx.com", "http://xxx.com2");
        response.setMessage(message);
    }
}


class ClosingFilter3 implements Filter3 {

    @Override
    public void doFilter(Request request, Response response, FilterChain5 chain) {
        String message = request.getMessage();
        message = message + ".";
        request.setMessage(message);

        chain.doFilter(request,response,true);

        message = response.getMessage()+".2";
        response.setMessage(message);
    }
}

/**
 * chain  doFilter 方法中 传递布尔类型 toContinue 字段，控制链条是否继续
 * 单独定制 Request Response 对象，并在链条上传递阶段被处理的请求和响应
 */
class FilterChain5 implements Filter3 {
    private List<Filter3> chain = new ArrayList<>();
    private int current = 0;

    public FilterChain5 addFilter(Filter3 filter){
        chain.add(filter);
        return this;
    }

    public void doFilter(Request request,Response response,boolean toContinue){
        if(toContinue && current<chain.size()){
            chain.get(current++).doFilter(request,response,this);
        }
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain5 filterChain) {
        doFilter(request,response,true);
    }
}

public class Filter07 {

    private Request request = new Request("): hello somebody, welcome to xxx.com");
    private Response response = new Response("): hello somebody, welcome to xxx.com");

    /**
     * Chain 自身实现Filter 接口，方便 Chain 合并 以及连续调用
     */
    @Test
    public void test(){
        FilterChain5 filterChain = new FilterChain5();
        FilterChain5 extraChain = new FilterChain5().addFilter(new ClosingFilter3());

        filterChain.addFilter(new FaceFilter3()).addFilter(new BodyFilter3()).addFilter(new NetFilter3()).addFilter(extraChain);

        filterChain.doFilter(request,response,filterChain);

        System.out.println(response);
    }

}
