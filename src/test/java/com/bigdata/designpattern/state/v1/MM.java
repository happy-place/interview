package com.bigdata.designpattern.state.v1;

public class MM {
    String name;
    MMState state;

    public MM() {
        state = new MMHappyState();
    }

    public void simle(){
        state.simle();
    }

    public void cry(){
        state.cry();
    }

    public void say(){
        state.say();
    }

    public void changeState(MMState state){
        this.state = state;
    }

}
