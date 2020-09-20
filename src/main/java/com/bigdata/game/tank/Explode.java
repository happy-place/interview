package com.bigdata.game.tank;

import java.awt.*;

/**
 * 爆炸效果
 * 初始具有速度，直接在pain中执行moving操作
 */
public class Explode {

    static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x,y;

    private TankFrame tf = null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("tank/audio/explode.wav").run();
    }


    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step>=ResourceMgr.explodes.length){
            step = 0;
        }

    }

}
