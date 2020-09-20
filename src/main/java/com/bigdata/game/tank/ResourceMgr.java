package com.bigdata.game.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 资源管理器
 * 管理需要用到资源
 */
public class ResourceMgr {

    public static BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
    public static BufferedImage badTankL,badTankU,badTankR,badTankD;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/GoodTank1.png"));
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankD =  ImageUtil.rotateImage(goodTankU,180);
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/BadTank1.png"));
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankD =  ImageUtil.rotateImage(badTankU,180);
            badTankL = ImageUtil.rotateImage(badTankU,-90);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/bulletU.png"));
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);
            bulletL = ImageUtil.rotateImage(bulletU,-90);

            for(int i=0;i<16;i++){
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/e"+ ++i +".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
