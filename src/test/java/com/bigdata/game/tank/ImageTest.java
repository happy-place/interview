package com.bigdata.game.tank;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ImageTest {

    /**
     * 绝对路径加载图片到内存，断言片段是否加载成功(isNull)
     * @throws IOException
     */
    @Test
    public void loadImageByAbsolutePath() throws IOException {
        String imagePath = "/Users/huhao/softwares/idea_proj/interview/src/main/resources/tank/images/BadTank1.png";
        BufferedImage image = ImageIO.read(new File(imagePath));
        assertNotNull(image);
    }

    /**
     * 类加载器加载基于类路径相对位置加载图片
     * @throws IOException
     */
    @Test
    public void loadImageByClassLoader() throws IOException {
        String imagePath = "tank/images/BadTank1.png";
        BufferedImage image =  ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath));
        assertNotNull(image);
    }



}
