package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItems {
    private int order_id;
    private int article_id;
    private int amount;

    public OrderItems(int order_id, int article_id, int amount) {
        this.order_id = order_id;
        this.article_id = article_id;
        this.amount = amount;
    }

    public static void addOrderItems(OrderItems orderItem) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "INSERT INTO order_items (order_id, article_id, amount) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderItem.order_id);
            pstmt.setInt(2, orderItem.article_id);
            pstmt.setInt(3, orderItem.amount);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
