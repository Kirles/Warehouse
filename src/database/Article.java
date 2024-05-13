package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Article {
    private String name;
    private float weight;
    private String manufacture;
    private int article_type_id;
    
    public Article(String name, float weight, String manufacture, int article_type_id) {
        this.name = name;
        this.weight = weight;
        this.manufacture = manufacture;
        this.article_type_id = article_type_id;
    }

    public static void addArticle (Article article) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "INSERT INTO articles (product_name, weight, manufacture, category_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, article.name);
            pstmt.setDouble(2, article.weight);
            pstmt.setString(3, article.manufacture);
            pstmt.setInt(4, article.article_type_id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
