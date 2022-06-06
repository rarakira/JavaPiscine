package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.io.File;
import java.io.IOException;

public class Program {

    public static void main(String[] args) {
        if (args.length != 3 || !(new File(args[2]).exists())) {
            System.err.println("The program takes following arguments: arg1 arg2 path/to/image.bmp");
            System.exit(-1);
        }
        char blank = args[0].charAt(0);
        char pixel = args[1].charAt(0);
        String imageFile = args[2];
        Logic image = new Logic();

        try {
            byte [][] imgArr = image.parseImage(imageFile);
            printToConsole(imgArr, pixel, blank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printToConsole(byte[][] imgArr, char pixel, char blank) {
        for (int x = 0; x < imgArr.length; x++) {
            for (int y = 0; y < imgArr[x].length; y++) {
                if (imgArr[x][y] == 1) {
                    System.out.print(pixel);
                } else {
                    System.out.print(blank);
                }
            }
            System.out.println();
        }
    }
}
