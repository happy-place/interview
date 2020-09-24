package com.bigdata.designpattern.state.statemachine;

public class RunningState extends ThreadState_{
    private Thread_ t;

    public RunningState(Thread_ t) {
        this.t = t;
    }

    @Override
    void move(Action action) {
        if(action.msg=="terminate"){
            t.state = new TerminatedState(t);
        }
    }

    @Override
    void run() {
        System.out.println("change to terminated");
    }
}
