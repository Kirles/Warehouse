package frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthFrame extends JFrame {

    private static JButton loginButton, registerButton;

    public static void frame() {
        AuthFrame af = new AuthFrame();
        af.setTitle("Warehouse");
        af.setResizable(false);
        af.setSize(450, 250);

        af.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        loginButton = new JButton("Увійти");
        loginButton.setBounds(40, 75, 150, 25);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                af.setVisible(false);

            }
        });

        registerButton = new JButton("Зареєструватися");
        registerButton.setBounds(240, 75, 150, 25);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                af.setVisible(false);
                RegistrationFrame.frame();
            }
        });

        af.add(loginButton);
        af.add(registerButton);
        af.add(panel);

        af.setVisible(true);
    }

}

