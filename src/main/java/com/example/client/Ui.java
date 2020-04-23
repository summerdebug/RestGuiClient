package com.example.client;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Ui {

    private static final Border EMPTY_BORDER =
            BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private static final JLabel URL_LABEL = new JLabel("URL: ");
    public static final JTextField URL_FIELD = new JTextField();
    private static final JLabel REQUEST_TYPE_LABEL = new JLabel("Request type: ");
    private static final String[] requestTypes = {"GET", "POST"};
    public static final JComboBox<String> REQUEST_TYPE_SELECT = new JComboBox<>(requestTypes);
    private static final JButton SUBMIT_BUTTON = new JButton("Submit");
    private static final JLabel REQUEST_LABEL = new JLabel("Request body");
    private static final JLabel RESPONSE_LABEL = new JLabel("Response");
    public static final JTextArea REQUEST_AREA = new JTextArea();
    public static final JTextArea RESPONSE_AREA = new JTextArea();
    private static final String DEFAULT_URL = "http://localhost:8080/employees";

    static void showApplicationWindow() {
        URL_FIELD.setMaximumSize(new Dimension(800, 50));
        URL_FIELD.setText(DEFAULT_URL);
        SUBMIT_BUTTON.addActionListener(RestClient::submit);

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

        buttonPanel.add(REQUEST_TYPE_LABEL);
        REQUEST_TYPE_SELECT.setMaximumSize(new Dimension(200, 50));
        buttonPanel.add(REQUEST_TYPE_SELECT);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
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
