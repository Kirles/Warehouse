import frames.AuthFrame;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        String startDate = "1714694300000";
//        String endDate = "1714694500000";
//        retrieveOrdersByDateRange(startDate, endDate);
        AuthFrame.frame();
    }

    public static void retrieveOrdersByDateRange(String startDate, String endDate) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT * FROM orders WHERE date BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);

            ResultSet rs = pstmt.executeQuery();

            // Обработка результатов запроса
            while (rs.next()) {
                int orderId = rs.getInt("id");
                int orderTypeId = rs.getInt("order_type_id");
                int userId = rs.getInt("user_id");
                boolean completed = rs.getBoolean("completed");
                // Дополнительные операции с данными заказа, если необходимо
                System.out.println("Заказ ID: " + orderId + ", Тип заказа ID: " + orderTypeId + ", Пользователь ID: " + userId + ", Завершен: " + completed);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}
