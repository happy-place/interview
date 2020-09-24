package com.bigdata.designpattern.visitor.v1;

public class Computer{

    ComputerPart cpu = new CPU();
    ComputerPart memory = new Memory();
    ComputerPart board = new Board();

    void accept(Visitor visitor) {
        cpu.accept(visitor);
        memory.accept(visitor);
        board.accept(visitor);
    }
}
