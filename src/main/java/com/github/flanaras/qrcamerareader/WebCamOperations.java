package com.github.flanaras.qrcamerareader;

import com.github.flanaras.qrcamerareader.Interfaces.OnQREvent;
import com.github.flanaras.qrcamerareader.utils.ImageUtils;
import com.github.flanaras.qrcamerareader.utils.ThreadUtils;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;
import com.google.zxing.*;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author flanaras
 */
public class WebCamOperations {

    private List<OnQREvent> onQREventList = null;

    private Webcam webcam;

    public WebCamOperations() {
        Webcam.setDriver(new V4l4jDriver());
        onQREventList = new ArrayList<>();

        webcam = Webcam.getWebcams().get(0);
        set720pResolution();
        webcam.open();
    }

    public void addOnQREventListener(OnQREvent onQREvent) {
        onQREventList.add(onQREvent);
    }

    private void set720pResolution() {
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

    public void execute() {

        BufferedImage image;
        while (true) {
            ThreadUtils.sleep(200);

            if (webcam.isOpen()) {

                image = webcam.getImage();
                if (hasNoImage(image)) {
                    continue;
                }

                BinaryBitmap bitmap = ImageUtils.imageToBinaryBitmap(image);

                final Result result = ImageUtils.recogniseQR(bitmap);

                if (hasResult(result)) {
                    ImageUtils.saveImage(image, "test.png");

                    onQREventList.forEach(onQREvent -> onQREvent.QRRead(result.getText()));
                    break;
                }
            }
        }
    }

    private boolean hasResult(Result result) {
        return result != null;
    }

    private boolean hasNoImage(BufferedImage image) {
        return image == null;
    }
}
