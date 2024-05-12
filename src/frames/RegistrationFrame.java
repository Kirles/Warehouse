package frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        registButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(roleBox.getSelectedItem() == "Волонтер"){

                }
                if(roleBox.getSelectedItem() == "Потерпілий"){

                }
                if(roleBox.getSelectedItem() == "Адміністратор"){

                }
            }
        });

        exitButton = new JButton("Назад");
        exitButton.setBounds(40, 325, 80, 25);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AuthFrame.frame();
                rf.dispose();
            }
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
