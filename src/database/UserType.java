package database;

import java.sql.*;

public class UserType {

    private final String type;

    public UserType(String type) {
        this.type = type;
    }

    public static int getType(String type){
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT id FROM user_type WHERE type = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;

    }

}
