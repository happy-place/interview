package com.bigdata.ma.game.tank;


public class Main {

    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        while(true){
            // 每隔0.05秒重新绘制
            try {
                Thread.sleep(50);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
