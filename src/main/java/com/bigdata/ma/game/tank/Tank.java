package com.bigdata.ma.game.tank;

import java.awt.*;

public class Tank {
    private static final int width = 50;
    private static final int height = 50;
    private static final int SPEED = 10;

    private boolean toMoving = false;

    private int x, y;
    private Dir dir;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, width, height);
        moving();
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isToMoving() {
        return toMoving;
    }

    public void setToMoving(boolean toMoving) {
        this.toMoving = toMoving;
    }

    public void moving() {
        if (toMoving) {
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
}
