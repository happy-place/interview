package com.bigdata.ma.game.tank;

import java.awt.*;

public class Tank {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int SPEED = 10;

    private boolean toMove = false;

    private int x, y;
    private Dir dir;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(color);
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
}
