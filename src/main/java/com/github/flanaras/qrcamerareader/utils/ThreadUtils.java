package com.github.flanaras.qrcamerareader.utils;

/**
 * @author flanaras
 */
public class ThreadUtils {

    public static boolean sleep(long millis) {
        try {
            Thread.sleep(millis);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
