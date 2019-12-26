package com.example.client;

import javax.swing.*;
import java.awt.*;

public class RestGuiClient {

    public static void main(String... args) {
        showApplicationWindow();
    }

    private static void showApplicationWindow() {
        JFrame frame = new JFrame("Rest client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Test label");
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> label.setText("Submit button pressed"));

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(submitButton);
        frame.add(panel);
        frame.setSize(300, 200);
        positionToScreenCenter(frame);
        frame.setVisible(true);
    }

    private static void positionToScreenCenter(JFrame frame) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screen.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
