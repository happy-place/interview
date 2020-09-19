package com.bigdata.ma.game.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    // 窗口尺寸
    int frameWidth = 800;
    int frameHeigth = 600;

    // 坦克初始坐标
    int x = 200;
    int y = 200;

    // 坦克尺寸
    int width = 50;
    int height = 50;

    public TankFrame() throws HeadlessException {
        // 尺寸
        setSize(frameWidth, frameHeigth);
        // 不可缩放窗口
        setResizable(false);
        // 窗口标题
        setTitle("tank war");
        // 可见性
        setVisible(true);

        // 注册按键监听
//        addKeyListener(new MyKeyListener());

        // 添加监听器，窗口关闭程序退出
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 每次移动 frame 重新渲染
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // 绘制矩形
        g.fillRect(x, y, width, height);
        // 算数运算自动实现移动效果
        x += 10;
        y += 10;
    }

    /**
     * 自定义按键监听事件
     */
    class MyKeyListener implements KeyListener{

        /**
         * 输入字符
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println("key typed");
        }

        /**
         * 按压
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key press");
            // 重新绘制frame，底层调动 paint()
            repaint();
        }

        /**
         * 按键释放
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key release");
        }
    }


}
