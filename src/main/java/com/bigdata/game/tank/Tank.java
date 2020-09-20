package com.bigdata.game.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    private static final int HEIGHT = ResourceMgr.goodTankD.getHeight();
    private static final int SPEED = 6;

    // 默认坦克已创建，就能动
    private boolean moving = true;

    private int x, y;
    private Dir dir;

    private Rectangle rect;

    private boolean living = true;

    private static Random random = new Random();

    private Group group;

    private TankFrame tf;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        this.rect = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
    }

    public void paint(Graphics g) {
        if(living){
            boolean isBad = this.getGroup() == Group.BAD;
            switch (dir){
                case LEFT:
                    g.drawImage(isBad?ResourceMgr.badTankL:ResourceMgr.goodTankL,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(isBad?ResourceMgr.badTankR:ResourceMgr.goodTankR,x,y,null);
                    break;
                case UP:
                    g.drawImage(isBad?ResourceMgr.badTankU:ResourceMgr.goodTankU,x,y,null);
                    break;
                case DOWN:
                    g.drawImage(isBad?ResourceMgr.badTankD:ResourceMgr.goodTankD,x,y,null);
                    break;
            }
            move();
        }else{
            // 坦克牺牲需要移除
            tf.tanks.remove(this);
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void move() {
        if (moving) {
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
        // 敌方坦克 5%概率开火，10%概率改变方向
        if(this.getGroup()==Group.BAD){
            if(random.nextInt(100)>95) {
                this.fire();
            }
            if(random.nextInt(100)>95){
                randomDir();
            }
        }

        boundsCheck();

        this.rect.x = this.x;
        this.rect.y = this.y;

    }

    /**
     * 边界检查
     */
    private void boundsCheck() {
        // 防止太靠边
        int keep = 2;
        if(this.x<keep){
            x = keep;
        }
        if(this.y < 30 - keep){
            y=30 - keep;
        }
        if(this.x>TankFrame.getWIDTH() - Tank.getWIDTH()-keep){
            x = TankFrame.getWIDTH() - Tank.getWIDTH()-keep;
        }

        if(this.y>TankFrame.getHEIGHT() - Tank.getHEIGHT()-keep){
            y = TankFrame.getHEIGHT() - Tank.getHEIGHT()-keep;
        }

    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        // 要想让tank开火中能够像画面交出bullet，tank 对象创建时，需要持有tankFrame 对象引用
        // 子弹中心对齐坦克中心
        int bulletX = x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = y +  Tank.HEIGHT/2 - Bullet.WIDTH/2;
        tf.getBullets().add(new Bullet(bulletX,bulletY, this.dir,this.group,tf));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void die() {
        this.living = false;
    }



}
