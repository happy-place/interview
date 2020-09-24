package com.bigdata.designpattern.visitor.v1;

public class Memory extends ComputerPart{
    @Override
    void accept(Visitor visitor) {
        visitor.vistMemory(this);
    }

    @Override
    double getPrice() {
        return 500;
    }
}
