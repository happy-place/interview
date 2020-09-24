package com.bigdata.designpattern.visitor.v1;

public interface Visitor {

    void vistCpu(CPU cpu);
    void vistMemory(Memory memory);
    void vistBoard(Board board);

}
