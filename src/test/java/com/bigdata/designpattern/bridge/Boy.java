package com.bigdata.designpattern.bridge;

public class Boy {

    public void chase(Girl mm){
        // 风格聚合品类维，摆脱继承体系类爆炸
        Gift g = new WarmGift(new Flower());
        give(mm,g);
    }

    private void give(Girl mm, Gift g) {
    }

}

