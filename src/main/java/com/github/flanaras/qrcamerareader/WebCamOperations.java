package com.github.flanaras.qrcamerareader;

import com.github.flanaras.qrcamerareader.Interfaces.OnQREvent;
import com.github.flanaras.qrcamerareader.utils.ImageUtils;
import com.github.flanaras.qrcamerareader.utils.ThreadUtils;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;
import com.google.zxing.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author flanaras
 */
public class WebCamOperations implements Runnable {

    private List<OnQREvent> onQREvents = null;

    private String previousQR = null;

    protected Webcam webcam;
    private boolean executeRun = false;

    public WebCamOperations() {
        onQREvents = new ArrayList<>();
        executeRun = true;
    }

    public void addOnQREventListener(OnQREvent onQREvent) {
        onQREvents.add(onQREvent);
    }

    protected void set720pResolution() {
        setupCameraDimensions(WebcamResolution.HD720.getSize());
    }

    private void setupCameraDimensions(Dimension dimension) {
        Dimension[] nonStandardResolutions = new Dimension[] {
                dimension,
        };

        webcam.setCustomViewSizes(nonStandardResolutions);
        webcam.setViewSize(dimension);
    }

    public BufferedImage takePicture() {
        if (webcam.isOpen()) {
            return webcam.getImage();
        }

        return null;
    }

    public Webcam getWebcam() {
        return webcam;
    }

    @Override
    public void run() {

        if (webcam != null) {
            while (executeRun) {
                ThreadUtils.sleep(200);

                if (webcam.isOpen()) {

                    final BufferedImage image = webcam.getImage();
                    if (hasNoImage(image)) {
                        System.err.println("Not image");
                        continue;
                    }

                    BinaryBitmap bitmap = ImageUtils.imageToBinaryBitmap(image);

                    final Result result = ImageUtils.recogniseQR(bitmap);

                    if (hasResult(result)) {
                        if (!result.getText().equals(previousQR)) {


                            ImageUtils.saveImage(image, "QR_" + System.currentTimeMillis() + ".png");

                            onQREvents.forEach(onQREvent -> onQREvent.QRText(result.getText()));
                            onQREvents.forEach(onQREvent -> onQREvent.QRImage(image));

                            previousQR = result.getText();
                        }
                    }
                } else {
                    System.err.println("closed web camera");
                }
            }
        } else {
            System.err.println("Camera is null");
        }
    }

    private boolean hasResult(Result result) {
        return result != null;
    }

    private boolean hasNoImage(BufferedImage image) {
        return image == null;
    }
}
