package com.bigdata.game.tank;

import java.awt.*;

/**
 * 子弹类
 * 初始具有速度，直接在pain中执行moving操作
 */
public class Bullet {

    private static final int SPEED = 10;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    private int x,y;
    private Dir dir;

    private boolean live = true;

    // 子弹也需要持有 TankFrame 引用，来删除 tf.bullets 中的自身，避免内存溢出
    private TankFrame tf = null;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        // 生命周期结束吗，子弹从容器删除，避免内存溢出
        if(!live){
            tf.getBullets().remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            default:
                break;
        }
        move();

        // 坐标越界，生命周期结束
        if(x < 0 || x > tf.getWidth() ||y< 0 || y> tf.getHeight()){
            this.live = false;
        }
    }

    private void move(){
        // 算数运算自动实现移动效果
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }

    public boolean isLive() {
        return live;
    }
}
