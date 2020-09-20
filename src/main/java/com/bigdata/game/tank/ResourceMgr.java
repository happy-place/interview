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

    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/tankL.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/tankR.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/tankU.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/tankD.gif"));
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/bulletL.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/bulletR.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/bulletU.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tank/images/bulletD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
