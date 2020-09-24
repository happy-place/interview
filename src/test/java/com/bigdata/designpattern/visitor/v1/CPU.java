package com.bigdata.designpattern.visitor.v1;

public class CPU extends ComputerPart{
    @Override
    void accept(Visitor visitor) {
        visitor.vistCpu(this);
    }

    @Override
    double getPrice() {
        return 2000;
    }

}
