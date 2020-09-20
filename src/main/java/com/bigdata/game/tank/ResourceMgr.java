package com.bigdata.game.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 资源管理器
 * 管理需要用到资源
 */
public class ResourceMgr {

    public static BufferedImage tankL,tankU,tankR,tankD;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/GoodTank1.png"));
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD =  ImageUtil.rotateImage(tankU,180);
            tankL = ImageUtil.rotateImage(tankU,-90);
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/bulletU.gif"));
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
