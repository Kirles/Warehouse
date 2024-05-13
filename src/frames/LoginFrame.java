package frames;

import database.User;
import database.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class LoginFrame extends JFrame {

    private static JButton loginButton, exitButton;
    private static JTextField emailField;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        LoginFrame lf = new LoginFrame();
        lf.setTitle("Warehouse");
        lf.setResizable(false);
        lf.setSize(500, 250);
        lf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        emailField = new JTextField();
        emailField.setBounds(125, 75, 250, 25);
        emailField.setText("Enter the email");

        loginButton = new JButton("Увійти");
        loginButton.setBounds(175, 150, 150, 25);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String email = emailField.getText();
                    if(!User.getUser(email)){
                        JOptionPane.showMessageDialog(new AddArticleFrame(), "Користувача з такою поштою не існує.", "Помилка!", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String role = User.getUserRole(email);
                        if(Objects.equals(role, "Адміністратор")){
                            if(User.adminConfirmation()){
                                AdminFrame.frame();
                                lf.dispose();
                            }
                            else {
                                JOptionPane.showMessageDialog(new AddArticleFrame(), "Пароль не вірний.", "Помилка!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if(Objects.equals(role, "Волонтер")){

                            lf.dispose();
                        }
                        if(Objects.equals(role, "Потерпілий")){

                            lf.dispose();
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        exitButton = new JButton("Назад");
        exitButton.setBounds(40, 150, 80, 25);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AuthFrame.frame();
                lf.dispose();
            }
        });

        lf.add(emailField);
        lf.add(loginButton);
        lf.add(exitButton);
        lf.add(panel);

        lf.setVisible(true);
    }
}

