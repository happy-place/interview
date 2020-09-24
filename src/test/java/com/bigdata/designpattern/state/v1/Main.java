package com.bigdata.designpattern.state.v1;

public class Main {

    public static void main(String[] args) {
        MM mm = new MM();

        mm.cry();
        mm.simle();
        mm.say();

        mm.changeState( new MMSadState());
        mm.cry();
        mm.simle();
        mm.say();

        mm.changeState( new MMNerourState());
        mm.cry();
        mm.simle();
        mm.say();

    }

}
