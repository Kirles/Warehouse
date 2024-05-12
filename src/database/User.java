package database;

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

    public static void addUser(User user) {

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
}
