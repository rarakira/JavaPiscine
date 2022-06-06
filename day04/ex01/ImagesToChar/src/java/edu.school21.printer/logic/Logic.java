package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Logic {
    public byte [][] parseImage() throws IOException {

        BufferedImage image = ImageIO.read(Logic.class.getResource("/resources/image.bmp"));

        byte[][] array2D = new byte[image.getHeight()][image.getWidth()];

        for (int xPixel = 0; xPixel < image.getHeight(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getWidth(); yPixel++) {
                int color = image.getRGB(yPixel, xPixel);
                if (color == Color.BLACK.getRGB()) {
                    array2D[xPixel][yPixel] = 1;
                } else {
                    array2D[xPixel][yPixel] = 0;
                }
            }
        }
        return array2D;
    }
}
