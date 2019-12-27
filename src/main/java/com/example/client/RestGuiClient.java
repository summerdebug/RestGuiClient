package com.example.client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RestGuiClient {

    private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private static final JLabel URL_LABEL = new JLabel("URL: ");
    private static final JTextField URL_FIELD = new JTextField();
    private static final JButton SUBMIT_BUTTON = new JButton("Submit");
    private static final JLabel REQUEST_LABEL = new JLabel("Request");
    private static final JLabel RESPONSE_LABEL = new JLabel("Response");
    private static final JTextArea REQUEST_AREA = new JTextArea();
    private static final JTextArea RESPONSE_AREA = new JTextArea();

    public static void main(String... args) {
        showApplicationWindow();
    }

    private static void showApplicationWindow() {
        URL_FIELD.setMaximumSize(new Dimension(800, 50));
        SUBMIT_BUTTON.addActionListener(e -> URL_LABEL.setText("Submit button pressed"));

        JPanel urlPanel = createUrlPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel httpPanel = createHttpPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(urlPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(httpPanel);

        JFrame frame = new JFrame("Rest client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setSize(600, 500);
        positionToScreenCenter(frame);
        frame.setVisible(true);
    }

    private static JPanel createHttpPanel() {
        JPanel httpPanel = new JPanel();
        httpPanel.setLayout(new BoxLayout(httpPanel, BoxLayout.X_AXIS));

        JPanel requestPanel = createRequestPanel();
        JPanel responsePanel = createResponsePanel();
        httpPanel.add(requestPanel);
        httpPanel.add(responsePanel);
        return httpPanel;
    }

    private static JPanel createResponsePanel() {
        JPanel responsePanel = new JPanel();
        responsePanel.setLayout(new BoxLayout(responsePanel, BoxLayout.Y_AXIS));
        responsePanel.setBorder(EMPTY_BORDER);
        responsePanel.add(RESPONSE_LABEL);
        responsePanel.add(new JScrollPane(RESPONSE_AREA));
        return responsePanel;
    }

    private static JPanel createRequestPanel() {
        JPanel requestPanel = new JPanel();
        requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS));
        requestPanel.setBorder(EMPTY_BORDER);
        requestPanel.add(REQUEST_LABEL);
        requestPanel.add(new JScrollPane(REQUEST_AREA));
        return requestPanel;
    }

    private static JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(EMPTY_BORDER);
        buttonPanel.add(SUBMIT_BUTTON);
        return buttonPanel;
    }

    private static JPanel createUrlPanel() {
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.X_AXIS));
        urlPanel.setBorder(EMPTY_BORDER);
        urlPanel.add(URL_LABEL);
        urlPanel.add(URL_FIELD);
        return urlPanel;
    }

    private static void positionToScreenCenter(JFrame frame) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screen.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
