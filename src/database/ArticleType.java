package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleType {
    private String type;

    public ArticleType(String type) {
        this.type = type;
    }

    public static List<String> getArticleTypes() {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT type FROM article_type";
        List<String> types = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                types.add(rs.getString("type"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return types;
    }
}
