package com.bigdata.game.tank;


public class Main {

    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();

        for(int i=0;i<5;i++){
            tankFrame.tanks.add(new Tank(50 + i*80,200,Dir.DOWN, Group.BAD,tankFrame));
        }

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
