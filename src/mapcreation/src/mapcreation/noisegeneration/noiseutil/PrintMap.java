package mapcreation.noisegeneration.noiseutil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Used to draw grey scale images for manual testing
 */
public class PrintMap {

    /**
     * Creates a grey scale image representing the noise past in
     *
     * @param imageData Map topology that gets converted to a grey scale image
     */
    public static void greyImage(double[][] imageData) {
        BufferedImage image = new BufferedImage(imageData.length, imageData[0].length, BufferedImage.TYPE_INT_RGB);
        float colorValue;
        for (int x = 0; x < imageData.length; x++) {
            for (int y = 0; y < imageData[0].length; y++) {
                colorValue = (float) (imageData[x][y]);
                //Clears float rounding errors
                if (colorValue > 1) colorValue = 1;
                if (colorValue < 0) colorValue = 0;
//                System.out.println("c: "+colorValue);
                //Sets the pixel to be a strength of grey
                image.setRGB(x, y, new Color(colorValue, colorValue, colorValue).getRGB());
            }
        }
        //Creates the image file
        try {
            File file = new File("topology.png");
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            throw new RuntimeException("Image drawing failed.");
        }
    }

    /**
     * Produces a grey scale image representing the forest tiles in the map
     *
     * @param imageData A field of booleans that state where forest tiles can be
     */
    public static void greyImage(boolean[][] imageData) {
        BufferedImage image = new BufferedImage(imageData.length, imageData[0].length, BufferedImage.TYPE_INT_RGB);
        int colorValue;
        for (int x = 0; x < imageData.length; x++) {
            for (int y = 0; y < imageData[0].length; y++) {
                //Sets the pixel to be either black or white
                colorValue = imageData[x][y] ? 255 : 0;
                image.setRGB(x, y, new Color(colorValue, colorValue, colorValue).getRGB());
            }
        }
        //Creates the image file
        try {
            File file = new File("forest.png");
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            throw new RuntimeException("Image drawing failed.");
        }
    }
}
