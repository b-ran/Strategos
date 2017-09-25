package mapcreation.noisegeneration.partsNeedsToBeRenamed;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrintMap {
    public static void greyImage(double[][] imageData) {
        BufferedImage image = new BufferedImage(imageData.length, imageData[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imageData.length; x++) {
            for (int y = 0; y < imageData[0].length; y++) {
                Color color = new Color((int) imageData[x][y], (int) imageData[x][y], (int) imageData[x][y]);
                image.setRGB(x, y, color.getRGB());
            }
        }
        try {
            File file = new File("noise.png");
            file.createNewFile();
            ImageIO.write(image, "PNG", file);
        } catch (IOException e){
            throw new RuntimeException("Image drawing failed.");
        }
    }

}
