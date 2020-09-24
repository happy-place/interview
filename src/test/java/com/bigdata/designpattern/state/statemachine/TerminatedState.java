package com.bigdata.designpattern.state.statemachine;

public class TerminatedState extends ThreadState_{
    private Thread_ t;

    public TerminatedState(Thread_ t) {
        this.t = t;
    }

    @Override
    void move(Action action) {
        if(action.msg=="restart"){
            t.state = new RunningState(t);
        }
    }

    @Override
    void run() {
        System.out.println("change to running");
    }
}
