package com.bigdata.designpattern.state.statemachine;

public class NewState extends ThreadState_{

    private Thread_ t;

    public NewState(Thread_ t) {
        this.t = t;
    }

    @Override
    void move(Action action) {
        if(action.msg == "start"){
            t.state = new RunningState(t);
        }
    }

    @Override
    void run() {
        System.out.println("change to running");
    }
}
