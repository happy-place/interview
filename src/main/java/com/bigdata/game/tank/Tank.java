package com.bigdata.game.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int WIDTH = ResourceMgr.tankD.getWidth();
    private static final int HEIGHT = ResourceMgr.tankD.getHeight();
    private static final int SPEED = 3;

    // 默认坦克已创建，就能动
    private boolean moving = true;

    private int x, y;
    private Dir dir;

    private boolean living = true;

    private static Random random = new Random();

    private Group group;

    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(living){
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
        // 敌方坦克 1/10概率打子弹
        if(random.nextInt(10)%8==1){
            this.fire();
        }
    }

    public void fire() {
        // 要想让tank开火中能够像画面交出bullet，tank 对象创建时，需要持有tankFrame 对象引用
        // 子弹中心对齐坦克中心
        int bulletX = x;
        int bulletY = y;
        switch (dir){
            case UP:
                bulletX += Tank.HEIGHT/2 - Bullet.HEIGHT/2;
                bulletY += Tank.WIDTH/2 - Bullet.WIDTH/2;
                break;
            case DOWN:
                bulletX += Tank.HEIGHT/2 - Bullet.HEIGHT/2;
                bulletY += Tank.WIDTH/2 - Bullet.WIDTH/2;
                break;
            case LEFT:
                bulletX += Tank.WIDTH/2 - Bullet.WIDTH/2 ;
                bulletY += HEIGHT/2 - Bullet.WIDTH/2;
                break;
            case RIGHT:
                bulletX += Tank.WIDTH/2 - Bullet.WIDTH/2 ;
                bulletY += Tank.HEIGHT/2 - Bullet.WIDTH/2;
                break;
            default:
                break;
        }
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

    public void die() {
        this.living = false;
    }
}
