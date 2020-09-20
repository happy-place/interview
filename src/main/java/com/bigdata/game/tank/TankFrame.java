package com.bigdata.game.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    // 窗口尺寸
    private static final int HEIGHT = 1080;
    private static final int WIDTH = 960;

    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);

    List<Tank> tanks = new ArrayList<>();

    List<Explode> explodes = new ArrayList<>();

    // 坦克发射子弹，换成批
    private List<Bullet> bullets = new ArrayList<>();

    // 双缓冲解决画面闪烁问题: 图片先写入缓存，然后一次性提交给显卡
    Image offScreenImage = null;

    public TankFrame() throws HeadlessException {
        // 尺寸
        setSize(WIDTH, HEIGHT);
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
        // 需要谁，就把画笔交给谁
        drawString(g);
        myTank.paint(g);

        // 绘制子弹
        // 方案1：普通for循环，bullet paint() 内部进行越界删除
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }

        // 绘制地方坦克
        for(int i=0;i<tanks.size();i++){
            tanks.get(i).paint(g);
        }


        // 绘制爆炸
        for(int i=0;i<explodes.size();i++){
            explodes.get(i).paint(g);
        }

        // 碰撞检测
        // 方案1：普通for循环，bullet paint() 内部进行越界删除
        for(int i=0;i<bullets.size();i++){
            for(int j=0;j<tanks.size();j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

    }

    /**
     * 显示打印坦克数量，子弹数据在画框，防止内存溢出
     * @param g
     */
    private void drawString(Graphics g){
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量："+bullets.size(),10,60);
        g.drawString("坦克数量："+tanks.size(),10,80);
        g.drawString("爆炸数量："+explodes.size(),10,100);
        g.setColor(color);
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
                case KeyEvent.VK_CONTROL:
                    // 子弹在坦克中创建，但绘制时还是交给 frame，fire 将采用策略模式封装所有操作
                    myTank.fire();
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
            if(!bL && !bR && !bU && !bD){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if(bL){
                    myTank.setDir(Dir.LEFT);
                }
                if(bR){
                    myTank.setDir(Dir.RIGHT);
                }
                if(bU){
                    myTank.setDir(Dir.UP);
                }
                if(bD){
                    myTank.setDir(Dir.DOWN);
                }
            }
        }
    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            // 内存中创建图片
            offScreenImage = this.createImage(WIDTH,HEIGHT);
        }
        // 获取画笔
        Graphics gOffScreen = offScreenImage.getGraphics();
        // 获取画笔之前颜色
        Color color = gOffScreen.getColor();
        // 绘制黑色图片背景
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,WIDTH,HEIGHT);
        // 设置画笔之前颜色
        gOffScreen.setColor(color);
        // 图片写入缓存
        paint(gOffScreen); // 往内存里话需要的东西
        // 画完后一次性提交显卡
        g.drawImage(offScreenImage,0,0,null);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
