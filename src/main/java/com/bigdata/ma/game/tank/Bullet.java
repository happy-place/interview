package com.bigdata.ma.game.tank;

import java.awt.*;

/**
 * 子弹类
 * 初始具有速度，直接在pain中执行moving操作
 */
public class Bullet {

    private static final int SPEED = 5;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    private int x,y;
    private Dir dir=Dir.DOWN;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }


    public void paint(Graphics g) {
        // 画笔更改颜色前，先记录原来颜色
        Color color = g.getColor();
        // 画笔切换为红色，然后绘制圆形坦克
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        // 画笔切回原来颜色
        g.setColor(color);
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
