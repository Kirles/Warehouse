package database;

import javax.swing.*;
import java.sql.*;

public class User {
    private String name;
    private String email;
    private int user_type_id;

    public User(String name, String email, int user_type_id) {
        this.name = name;
        this.email = email;
        this.user_type_id = user_type_id;
    }

    public static void addUser(User user) throws SQLException {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "INSERT INTO users (name, email, user_type_id) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.name);
            pstmt.setString(2, user.email);
            pstmt.setInt(3, user.user_type_id);
            pstmt.executeUpdate();
        }
    }

    public static boolean getUser(String email) throws SQLException {
        String url = "jdbc:sqlite:warehouse.db";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE email=?")) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static String getUserRole(String email) throws SQLException {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT ut.type FROM users u JOIN user_type ut ON u.user_type_id = ut.id WHERE u.email = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("type");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static boolean adminConfirmation() {

        JPasswordField passwordField = new JPasswordField();
        Object[] message = {"Введіть пароль:", passwordField};
        int option = JOptionPane.showConfirmDialog(null, message, "Підтвердження", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            char[] inputPassword = passwordField.getPassword();
            return new String(inputPassword).equals("admin");
        }

        return false;

    }

}
