package frames;

import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {

    private static JButton loginButton, registerButton;

    public static void frame() {
        AuthFrame af = new AuthFrame();
        af.setTitle("Warehouse");
        af.setResizable(false);
        af.setSize(450, 250);
        af.setDefaultCloseOperation(EXIT_ON_CLOSE);

        MyPanel panel = new MyPanel();

        loginButton = new JButton("Увійти");
        loginButton.setBounds(40, 75, 150, 25);

        loginButton.addActionListener(e -> {
            af.dispose();
            LoginFrame.frame();
        });

        registerButton = new JButton("Зареєструватися");
        registerButton.setBounds(240, 75, 150, 25);

        registerButton.addActionListener(e -> {
            RegistrationFrame.frame();
            af.dispose();
        });

        af.add(loginButton);
        af.add(registerButton);
        af.add(panel);

        af.setVisible(true);
    }


}

