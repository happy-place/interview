package com.bigdata.designpattern.bridge;

public class WildGift extends Gift {

    public WildGift(GiftImpl impl) {
        this.impl = impl;
    }
}
