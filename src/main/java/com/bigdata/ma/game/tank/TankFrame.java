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

    private final int SPEED = 10;
    Dir dir = Dir.DOWN;

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
        addKeyListener(new MyKeyListener());

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
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // 绘制矩形
        g.fillRect(x, y, width, height);
        // 算数运算自动实现移动效果
        switch (dir){
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



    /**
     * 自定义按键监听事件
     */
    class MyKeyListener implements KeyListener {
        boolean bL,bR,bU,bD;

        /**
         * 输入字符
         *
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         * 按压，开启按压方向
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        /**
         * 按键释放，停止移动方向
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        /**
         * 通过按键事件透传移动方向
         */
        private void setMainTankDir() {
            if(bL){
                dir = Dir.LEFT;
            }
            if(bR){
                dir = Dir.RIGHT;
            }
            if(bU){
                dir = Dir.UP;
            }
            if(bD){
                dir = Dir.DOWN;
            }
        }

    }


}
