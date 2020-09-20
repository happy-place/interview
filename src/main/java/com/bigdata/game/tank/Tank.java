package com.bigdata.game.tank;

import java.awt.*;

public class Tank {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int SPEED = 5;

    private boolean toMove = false;

    private int x, y;
    private Dir dir;

    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }
        moving();
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isToMove() {
        return toMove;
    }

    public void setToMove(boolean toMove) {
        this.toMove = toMove;
    }

    public void moving() {
        if (toMove) {
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
    }

    public void fire() {
        // 要想让tank开火中能够像画面交出bullet，tank 对象创建时，需要持有tankFrame 对象引用
        int bulletX = x;
        int bulletY = y;
        switch (dir){
            case UP:
                bulletX += HEIGHT/2;
                bulletY += WIDTH/2;
                break;
            case DOWN:
                bulletX += HEIGHT/2;
                bulletY += WIDTH/2;
                break;
            case LEFT:
                bulletX += WIDTH/2;
                bulletY += HEIGHT/2;
                break;
            case RIGHT:
                bulletX += WIDTH/2;
                bulletY += HEIGHT/2;
                break;
            default:
                break;
        }
        tf.getBullets().add(new Bullet(bulletX,bulletY,this.dir,tf));
    }
}
