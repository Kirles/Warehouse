package database;

import java.sql.*;

public class Order {
    private int order_type_id;
    private int user_id;
    private boolean completed;
    private String date;
    private int warehouse_id;

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

}
