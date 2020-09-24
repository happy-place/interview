package com.bigdata.designpattern.visitor.v1;

public class PersonalVisitor implements Visitor {
    double totalPrice = 0.0;
    @Override
    public void vistCpu(CPU cpu) {
        totalPrice += cpu.getPrice() * 0.9;
    }

    @Override
    public void vistMemory(Memory memory) {
        totalPrice += memory.getPrice() * 0.85;
    }

    @Override
    public void vistBoard(Board board) {
        totalPrice += board.getPrice() * 0.95;
    }
}
