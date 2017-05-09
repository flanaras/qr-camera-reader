package com.github.flanaras.qrcamerareader;

import com.github.flanaras.qrcamerareader.Interfaces.OnQREvent;

/**
 * @author flanaras
 */
public class Main {

    //Todo: remove static
    static class MyEvent implements OnQREvent {

        @Override
        public void QRRead(String QRText) {
            System.out.println(QRText);
        }
    }


    public static void main(String[] args) {
        WebCamOperations webCamOp = new WebCamOperations();

        webCamOp.addOnQREventListener(new MyEvent());

        webCamOp.execute();
        //main.recogniseQR(main.imageToBinaryBitmap(main.takePicture()));
    }
}
