package com.bigdata.ma.game.snake;

import java.awt.*;
import java.awt.event.*;

/**
 * Yarn 对应的是 白板区域，其setSize 属性 是相对于 网格区域来确定的
 *
 * NodeSize，NodeCount，x，y 对应的是网格区域属性（即真正蛇运动区域）
 * 正方形网格区域，可以理解为一个一个的块组成，每个块为NodeSize像素(正方形)大小
 *
 */
public class Yard extends Frame {

    // 每个块像素数
    public static final int NodeSize = 15;
    // 整个面板，行或列对应块的个数
    public static final int NodeCount = 30;

    // 整个绘图面板尺寸（正方形），块个数 * 每个块像素，即绘图区域的边长
    public static final int AreaSize = NodeSize * NodeCount;

    // 设置为静态的，避免其他类访问时，需要重新 new
    // 坐标系 ↘
    // 默认绘图区域原点是白板左上角，由于网格为边长为白板的一半，换算过来，网格左上角，相对于白板左上角位移为如下 （x,y）
    static int x = AreaSize / 2;
    static int y = AreaSize / 2;

    Snake snake = new Snake();
    // 初始化 蛋在 10，10 网格位置
    Egg egg = new Egg(10,10);


    Yard(){
        // 正方形网格 处于白板正中心，且边长为白板的一半
        this.setSize(AreaSize*2,AreaSize*2);
        // 可见
        this.setVisible(true);

        // 注册监听器，当关闭白板时，程序退出
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 添加 键盘监控，具体动作适配 交给snake 自己完成
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }
        });

        // 50毫秒间隔，不停重画，中间会接收键盘动作，改变节点位置，从而在重画时实现移动效果
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }

    }


    // 双缓冲：图片先加载到缓存，然后一次性写入显卡，避免图片一点一点加载，出现卡顿，闪烁
    // 部分大型游戏引擎 内部封装了双缓冲，编写代码时，体会不到双缓冲存在
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage = this.createImage(this.getWidth(),this.getHeight());
        }
        Graphics gOff = offScreenImage.getGraphics();
        this.paint(gOff);
        g.drawImage(offScreenImage,0,0,null); // 一次性交付显卡
    }

    // 重写画网格方法
    @Override
    public void paint(Graphics g) {
        // 背景重画
        // 先取出原始背景，最后 set 获取 （除白板和黑网格以外颜色）
        Color color = g.getColor();

        // 描白 （白板）
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        // 涂黑 （网格）
        g.setColor(Color.BLACK);

        // = 情况保证网格是封闭的
        for(int i=0;i<=NodeCount;i++){
            // 绘制 x 轴（起止点y坐标相同，x长度为AreaSize）
            g.drawLine(x,y + i * NodeSize,x+AreaSize,y + i * NodeSize);
            // 绘制 y 轴（起止点x坐标相同，y长度为AreaSize）
            g.drawLine(x + i * NodeSize,y,x+i * NodeSize,y+AreaSize);
        }
        // 网格绘制完毕，紧接着绘制蛇，基于面向对象思想，蛇绘制由蛇自己解决
        egg.paint(g);
        snake.paint(g);

        snake.eat(egg);

        g.setColor(color);

    }

    public static void main(String[] args) {
        Yard yard = new Yard();
    }



}
