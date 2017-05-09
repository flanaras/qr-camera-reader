package com.github.flanaras.qrcamerareader.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author flanaras
 */
public class ImageUtils {

    //adapted from http://stackoverflow.com/questions/11487251/best-way-to-access-web-camera-in-java
    public static boolean saveImage(BufferedImage image, String path) {
        try {
            ImageIO.write(image, "PNG", new File(path));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static BinaryBitmap imageToBinaryBitmap(BufferedImage image) {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        return new BinaryBitmap(new HybridBinarizer(source));
    }

    public static Result recogniseQR(BinaryBitmap bitmap) {
        try {
            return new MultiFormatReader().decode(bitmap);
        } catch (NotFoundException e) {
            return null;
        }
    }
}
