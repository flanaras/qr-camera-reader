package com.github.flanaras.qrcamerareader;

import com.github.sarxos.webcam.Webcam;

/**
 * @author flanaras
 */
public class TestMain {

    private Webcam mWebcam;

    public TestMain() {
        mWebcam = Webcam.getWebcams().get(0);
    }

    void takePicture() {

    }

    public static void main(String[] args) {
        TestMain main = new TestMain();

        main.takePicture();
    }
}
