package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private final String name;
    private final float weight;
    private final String manufacture;
    private final int article_type_id;
    
    public Article(String name, float weight, String manufacture, int article_type_id) {
        this.name = name;
        this.weight = weight;
        this.manufacture = manufacture;
        this.article_type_id = article_type_id;
    }

    public static void addArticle(Article article) {
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

    public static List<String> getArticle() {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT product_name FROM articles";
        List<String> names = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                names.add(rs.getString("product_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return names;
    }

    public static int getArticleID(String name) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT id FROM articles WHERE product_name = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

             pstmt.setString(1, name);
             ResultSet rs = pstmt.executeQuery();

             while (rs.next()) {
                 return rs.getInt("id");
             }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static Article getArticleByID(int id) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT * FROM articles WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String productName = rs.getString("product_name");
                float weight = rs.getFloat("weight");
                String manufacturer = rs.getString("manufacture");
                int categoryId = rs.getInt("category_id");

                return new Article(productName, weight, manufacturer, categoryId);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public String getManufacture(){
        return manufacture;
    }

    public static void updateArticle(int ID, Article newArticle) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "UPDATE articles SET product_name = ?, weight = ?, manufacture = ?, category_id = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newArticle.name);
            pstmt.setFloat(2, newArticle.weight);
            pstmt.setString(3, newArticle.manufacture);
            pstmt.setInt(4, newArticle.article_type_id);
            pstmt.setInt(5, ID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static void deleteArticle(int ID) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "DELETE FROM articles WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static String getArticleNames() {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT product_name FROM articles";
        StringBuilder names = new StringBuilder();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                names.append(rs.getString("product_name")).append("\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return names.toString();
    }
}
