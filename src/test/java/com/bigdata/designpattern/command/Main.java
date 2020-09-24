package com.bigdata.designpattern.command;

public class Main {

    public static void main(String[] args) {
        Content content = new Content("hello java ");

        Command insertCommand = new InsertCommand(content);
        insertCommand.doit();
        System.out.println(content.str);
        insertCommand.undo();
        System.out.println(content.str);

        DeleteCommand deleteCommand = new DeleteCommand(content);
        deleteCommand.doit();
        System.out.println(content.str);
        deleteCommand.undo();
        System.out.println(content.str);
    }
}
