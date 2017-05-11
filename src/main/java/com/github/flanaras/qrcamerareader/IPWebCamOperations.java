package com.github.flanaras.qrcamerareader;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import java.net.MalformedURLException;

/**
 * @author flanaras
 */
public class IPWebCamOperations extends WebCamOperations {

    public IPWebCamOperations() {
        throw new IllegalArgumentException("URL must be provided");
    }

    /**
     * URL is the whole path to the video feed, i.e. http://192.168.42.129:12345/video
     */
    public IPWebCamOperations(String URL) {
        super();

        Webcam.setDriver(new IpCamDriver());
        try {
            IpCamDeviceRegistry.register(new IpCamDevice("Lignano", URL, IpCamMode.PUSH));
            //IpCamDeviceRegistry.register(new IpCamDevice("Lignano", "http://192.168.42.129:12345/video", IpCamMode.PUSH));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        webcam = Webcam.getDefault();
    }

    /**
     * Host should be in the from of protocol://hostname (i.e. http://192.168.1.1
     *
     * Path should be in the form of /path/to/destination (i.e. /foo/bar)
     */
    public IPWebCamOperations(String host, int port, String path) {
        this(host + ":" + port + path);
    }
}
