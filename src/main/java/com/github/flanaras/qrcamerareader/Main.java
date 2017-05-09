package com.github.flanaras.qrcamerareader;

import com.github.flanaras.qrcamerareader.Interfaces.OnQREvent;

import java.awt.image.BufferedImage;

/**
 * @author flanaras
 */
public class Main {

    private Window window = null;

    public Main() {}

    class MyEvent implements OnQREvent {

        @Override
        public void QRText(String QRText) {
            System.out.println(QRText);

            if (window != null) {
                window.setText(QRText);
            }
        }

        @Override
        public void QRImage(BufferedImage image) {

        }
    }

    public void start() {
        WebCamOperations webCamOp = new WebCamOperations();

        webCamOp.addOnQREventListener(new MyEvent());

        new Thread(webCamOp).start();
        window = new Window(webCamOp.getWebcam());

        //main.recogniseQR(main.imageToBinaryBitmap(main.takePicture()));
    }


    public static void main(String[] args) {
        new Main().start();
    }
}
