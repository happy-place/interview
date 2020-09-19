package com.bigdata.designpattern.responsibilitychain;

import org.junit.Test;

class Message{
    private String message;

    public Message() {
    }

    public Message(String message) {
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
        return "Message{" +
                "message='" + message + '\'' +
                '}';
    }
}

public class Filter01 {

    private Message msg = new Message("): hello somebody, welcome to xxx.com");

    /**
     * 增加修改，增加代码
     */
    @Test
    public void test() {
        String message = msg.getMessage();

        message = message.replace("):", "^v^");
        message = message.replace("somebody", "everybody");
        message = message.replace("xxx.com", "http://xxx.com");

        msg.setMessage(message);

        System.out.println(msg);
    }

    private void filterFace(Message msg){
        String message = msg.getMessage();
        message = message.replace("):", "^v^");
        msg.setMessage(message);
    }

    private void filterBody(Message msg){
        String message = msg.getMessage();
        message = message.replace("somebody", "everybody");
        msg.setMessage(message);
    }

    private void filterHttp(Message msg){
        String message = msg.getMessage();
        message = message.replace("xxx.com", "http://xxx.com");
        msg.setMessage(message);
    }

}
