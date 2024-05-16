package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleType {
    private final String type;

    public ArticleType(String type) {
        this.type = type;
    }

    public static void addArticleType(String type) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "INSERT INTO article_type (type) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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

    public static int getArticleTypeID(String type) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT id FROM article_type WHERE type = ?";

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

    public static void deleteArticleType(String typeName) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "DELETE FROM article_type WHERE type = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, typeName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

}
