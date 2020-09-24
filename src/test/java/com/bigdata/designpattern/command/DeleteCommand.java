package com.bigdata.designpattern.command;

public class DeleteCommand extends Command {

    Content content;
    String deleted;

    public DeleteCommand(Content content) {
        this.content = content;
    }

    @Override
    public void doit() {
        String str = content.str;
        deleted = str.substring(0,5);
        content.str = str.substring(5,str.length());
    }

    @Override
    public void undo() {
        content.str = deleted +  content.str;
    }
}
