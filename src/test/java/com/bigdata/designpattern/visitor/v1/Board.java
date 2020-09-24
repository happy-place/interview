package com.bigdata.designpattern.visitor.v1;

public class Board extends ComputerPart{

    @Override
    void accept(Visitor visitor) {
        visitor.vistBoard(this);
    }

    @Override
    double getPrice() {
        return 200;
    }
}
