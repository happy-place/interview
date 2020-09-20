package com.bigdata.game.snake;

import java.awt.*;
import java.util.Random;

/**
 * 蛇吃的东西
 */
public class Egg {
    // 蛋的网格坐标
    int row,col;
    Random ramdom = new Random();

    public Egg(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public void paint(Graphics g) {
        // get开头，set结尾 恢复现场，中间代码为具体节点展示时的逻辑
        Color c = g.getColor();

        // 位移参照 原点坐标 和 行列数，现算
        // 当前节点的相对于白板左上角（原点）的相对位移坐标
        // x 与 列数有关
        int x = Yard.x + col * Yard.NodeSize;
        // y 与 行数有关
        int y = Yard.y + row * Yard.NodeSize;

        g.setColor(Color.RED);
        g.fillOval(x,y,Yard.NodeSize,Yard.NodeSize);

        g.setColor(c);
    }

    public void reAppear() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.row = ramdom.nextInt(Yard.NodeSize);
        this.col = ramdom.nextInt(Yard.NodeSize);
    }

}
