package com.bigdata.designpattern.visitor.v1;

abstract public class ComputerPart {

    abstract void accept(Visitor visitor);

    abstract double getPrice();

}
