package com.bigdata.flyweight;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Bullet{
    UUID id = UUID.randomUUID();
    boolean living=true;

    int count = 0;

    @Override
    public String toString() {
        return "Bullet{" +
                "id=" + id +
                '}';
    }

}

class BulletPoll{
    private int size=5;
    private List<Bullet> bullets = new ArrayList<>();

    public BulletPoll() {
        for(int i=0;i<size;i++){
            bullets.add(new Bullet());
        }
    }

    public BulletPoll(int size) {
        this.size = size;
        for(int i=0;i<size;i++){
            bullets.add(new Bullet());
        }
    }

    public Bullet getBullet(){
        for(Bullet b:bullets){
            if(b.living){
                return b;
            }
        }
        return new Bullet();

    }


}

/**
 * 享元模式：
 * 1.预先将可能用到对象维护到池子里面，需要时直接从池子里面取，外界多处引用可能指向池子里面相同对象；
 * 2.池子里面维护的对象，也能可能是其他对象的组合；
 */
public class Flyweight {

    public static void main(String[] args) {
        BulletPoll bulletPoll = new BulletPoll(5);
        for(int i=0;i<10;i++){
            Bullet bullet = bulletPoll.getBullet();
            System.out.println(bullet);
        }
    }


}
