package com.github.flanaras.qrcamerareader;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;

/**
 * @author flanaras
 * Adapted from WebcamQRCodeExample
 */
public class Window extends JFrame {
    private JTextArea textArea = null;

    public Window(Webcam webcam) {
        super();

        setLayout(new FlowLayout());
        setTitle("QR Console");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setPreferredSize(WebcamResolution.PAL.getSize());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setPreferredSize(WebcamResolution.QVGA.getSize());

        add(panel);
        add(textArea);

        pack();
        setVisible(true);
    }

    public void setText(String text) {
        textArea.setText(text);
    }
}
