package frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private static JButton loginButton, exitButton;
    private static JTextField emailField;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        LoginFrame rf = new LoginFrame();
        rf.setTitle("Warehouse");
        rf.setResizable(false);
        rf.setSize(500, 250);
        rf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        emailField = new JTextField();
        emailField.setBounds(125, 75, 250, 25);
        emailField.setText("Enter the email");

        loginButton = new JButton("Увійти");
        loginButton.setBounds(175, 150, 150, 25);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        exitButton = new JButton("Назад");
        exitButton.setBounds(40, 150, 80, 25);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AuthFrame.frame();
                rf.dispose();
            }
        });

        rf.add(emailField);
        rf.add(loginButton);
        rf.add(exitButton);
        rf.add(panel);

        rf.setVisible(true);
    }
}

