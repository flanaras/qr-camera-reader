package com.github.flanaras.qrcamerareader;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;

/**
 * @author flanaras
 */
public class PCWebCamOperations extends WebCamOperations {
    public PCWebCamOperations() {
        super();

        Webcam.setDriver(new V4l4jDriver());
        webcam = Webcam.getWebcams().get(0);
        set720pResolution();
        webcam.open();
    }
}
