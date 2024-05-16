package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final int order_type_id;
    private final int user_id;
    private final boolean completed;
    private final String date;
    private final int warehouse_id;

    public Order(int order_type_id, int user_id, boolean completed, String date, int warehouse_id) {
        this.order_type_id = order_type_id;
        this.user_id = user_id;
        this.completed = completed;
        this.date = date;
        this.warehouse_id = warehouse_id;
    }

    public static int addOrder(Order order) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "INSERT INTO orders (order_type_id, user_id, completed, date, warehouse_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.order_type_id);
            pstmt.setInt(2, order.user_id);
            pstmt.setBoolean(3, order.completed);
            pstmt.setString(4, order.date);
            pstmt.setInt(5, order.warehouse_id);

            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Помилка.");
                }
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateOrderStatus(int id){
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "UPDATE orders SET completed = true WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static String generateOrderString(int order_type_id) {
        StringBuilder result = new StringBuilder();
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT u.name AS user_name, o.date AS order_date, a.product_name, oi.amount " +
                "FROM users u " +
                "JOIN orders o ON u.id = o.user_id " +
                "JOIN order_items oi ON o.id = oi.order_id " +
                "JOIN articles a ON oi.article_id = a.id " +
                "WHERE o.order_type_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order_type_id);
            ResultSet rs = pstmt.executeQuery();

            Map<String, Map<String, StringBuilder>> userOrderProductsMap = new HashMap<>();
            while (rs.next()) {
                String userName = rs.getString("user_name");
                String orderDate = rs.getString("order_date");
                String productName = rs.getString("product_name");
                int amount = rs.getInt("amount");

                Map<String, StringBuilder> orderProductsMap = userOrderProductsMap.computeIfAbsent(userName, k -> new HashMap<>());
                StringBuilder productsForDate = orderProductsMap.computeIfAbsent(orderDate, k -> new StringBuilder());
                productsForDate.append(productName).append(", кількість: ").append(amount).append("\n");
            }
            userOrderProductsMap.forEach((userName, orderProductsMap) -> {
                orderProductsMap.forEach((orderDate, products) -> {
                    result.append(userName).append(", ").append(orderDate).append(":\n").append(products).append("\n");
                });
            });

        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        return result.toString();
    }

}
