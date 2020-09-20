package com.bigdata.game.tank;


public class Main {

    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        for(int i=0;i<initTankCount;i++){
            tankFrame.tanks.add(new Tank(50 + i*80,200,Dir.DOWN, Group.BAD,tankFrame));
        }

        new Thread(()->new Audio("tank/audio/war1.wav").loop()).start();

        while(true){
            try {
                // 每隔0.05秒重新绘制,底层调用 frame.paint()
                Thread.sleep(50);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
