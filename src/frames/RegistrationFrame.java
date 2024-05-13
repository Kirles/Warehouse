package frames;

import database.User;
import database.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class RegistrationFrame extends JFrame {

    private static JButton registButton, exitButton;
    private static JTextField nameField, emailField;
    private static JComboBox roleBox;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        RegistrationFrame rf = new RegistrationFrame();
        rf.setTitle("Warehouse");
        rf.setResizable(false);
        rf.setSize(500, 450);
        rf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        nameField = new JTextField();
        nameField.setBounds(125, 100, 250, 25);
        nameField.setText("Enter the name");

        emailField = new JTextField();
        emailField.setBounds(125, 175, 250, 25);
        emailField.setText("Enter the email");

        String[] options = { "Потерпілий", "Волонтер", "Адміністратор"};
        roleBox = new JComboBox<>(options);
        roleBox.setBounds(125, 250, 250, 25);

        registButton = new JButton("Зареєструватися");
        registButton.setBounds(175, 325, 150, 25);

        registButton.addActionListener(e -> {
            try {
                if(User.getUser(emailField.getText())){
                    JOptionPane.showMessageDialog(new AddArticleFrame(), "Користувач з такою поштою вже існує.", "Помилка!", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    String role = Objects.requireNonNull(roleBox.getSelectedItem()).toString();
                    int role_id = UserType.getType(role);
                    User user = new User(nameField.getText(), emailField.getText(), role_id);
                    if(Objects.equals(role, "Адміністратор")){
                        if(User.adminConfirmation()){
                            User.addUser(user);
                            AdminFrame.frame();
                            rf.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(new AddArticleFrame(), "Пароль не вірний.", "Помилка!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        User.addUser(user);
                        if(Objects.equals(role, "Волонтер")){

                            rf.dispose();
                        }
                        if(Objects.equals(role, "Потерпілий")){

                            rf.dispose();
                        }
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        exitButton = new JButton("Назад");
        exitButton.setBounds(40, 325, 80, 25);

        exitButton.addActionListener(e -> {
            AuthFrame.frame();
            rf.dispose();
        });

        rf.add(nameField);
        rf.add(emailField);
        rf.add(registButton);
        rf.add(exitButton);
        rf.add(roleBox);
        rf.add(panel);

        rf.setVisible(true);
    }

}
