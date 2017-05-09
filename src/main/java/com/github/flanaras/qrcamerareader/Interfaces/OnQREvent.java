package com.github.flanaras.qrcamerareader.Interfaces;

import java.awt.image.BufferedImage;

/**
 * @author flanaras
 */
public interface OnQREvent {
    void QRText(String QRText);
    void QRImage(BufferedImage image);
}
