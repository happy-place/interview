package com.bigdata.designpattern.command;

public class InsertCommand extends Command {

    Content content;
    String strToInsert = "http://www.google.cn";

    public InsertCommand(Content content) {
        this.content = content;
    }

    @Override
    public void doit() {
        content.str += strToInsert;
    }

    @Override
    public void undo() {
        String str = content.str;
        content.str =  str.substring(0,str.length()-strToInsert.length());
    }
}
