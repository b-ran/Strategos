package mapcreation.noisegeneration.noiseutil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrintMap {
    //TODO This class is not commented.
    public static void greyImage(double[][] imageData) {
        BufferedImage image = new BufferedImage(imageData.length, imageData[0].length, BufferedImage.TYPE_INT_RGB);
        float colorValue;
        for (int x = 0; x < imageData.length; x++) {
            for (int y = 0; y < imageData[0].length; y++) {
                colorValue = (float) (imageData[x][y]);
                if(colorValue>1)colorValue=1;
                if(colorValue<0)colorValue=0;
//                System.out.println("c: "+colorValue);
                image.setRGB(x, y, new Color(colorValue, colorValue, colorValue).getRGB());
            }
        }
        try {
            File file = new File("topology.png");
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            throw new RuntimeException("Image drawing failed.");
        }
    }

    public static void greyImage(boolean[][] imageData) {
        BufferedImage image = new BufferedImage(imageData.length, imageData[0].length, BufferedImage.TYPE_INT_RGB);
        int colorValue;
        for (int x = 0; x < imageData.length; x++) {
            for (int y = 0; y < imageData[0].length; y++) {
                colorValue = imageData[x][y] ? 255 : 0;
                image.setRGB(x, y, new Color(colorValue, colorValue, colorValue).getRGB());
            }
        }
        try {
            File file = new File("forest.png");
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            throw new RuntimeException("Image drawing failed.");
        }
    }
}
