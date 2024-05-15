import frames.AuthFrame;

import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        String startDate = "1714694300000";
//        String endDate = "1714694500000";
//        retrieveOrdersByDateRange(startDate, endDate);
        AuthFrame.frame();
    }

    public static String showComboBoxInputDialog(JFrame parent, String[] options, String title, String message) {
        JComboBox<String> comboBox = new JComboBox<>(options);

        int result = JOptionPane.showConfirmDialog(parent, comboBox, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            return (String) comboBox.getSelectedItem();
        } else {
            return null;
        }
    }
}
