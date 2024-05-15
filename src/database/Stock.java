package database;

import java.sql.*;

public class Stock {
    private int article_id;
    private int stock_amount;
    private int warehouse_id;

    public Stock(int article_id, int stock_amount, int warehouse_id) {
        this.article_id = article_id;
        this.stock_amount = stock_amount;
        this.warehouse_id = warehouse_id;
    }

    public static int updateStock(Stock stock, boolean adding) {
        String url = "jdbc:sqlite:warehouse.db";
        int x = 0;
        if (adding) {
            increaseStock(stock, url);
            x++;
        }
        else {
            if(decreaseStock(stock, url)){
                x++;
            }
        }
        return x;
    }

    private static void increaseStock(Stock stock, String url) {
        String sqlSelect = "SELECT * FROM stock WHERE article_id = ? AND warehouse_id = ?";
        String sqlUpdate = "UPDATE stock SET stock_amount = stock_amount + ? WHERE article_id = ? AND warehouse_id = ?";
        String sqlInsert = "INSERT INTO stock (article_id, warehouse_id, stock_amount) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect);
             PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
             PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {

            pstmtSelect.setInt(1, stock.article_id);
            pstmtSelect.setInt(2, stock.warehouse_id);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                pstmtUpdate.setInt(1, stock.stock_amount);
                pstmtUpdate.setInt(2, stock.article_id);
                pstmtUpdate.setInt(3, stock.warehouse_id);
                pstmtUpdate.executeUpdate();
            } else {
                pstmtInsert.setInt(1, stock.article_id);
                pstmtInsert.setInt(2, stock.warehouse_id);
                pstmtInsert.setInt(3, stock.stock_amount);
                pstmtInsert.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка: " + e.getMessage());
        }
    }

    private static boolean decreaseStock(Stock stock, String url) {
        String sqlSelect = "SELECT * FROM stock WHERE article_id = ? AND warehouse_id = ?";
        String sqlUpdate = "UPDATE stock SET stock_amount = stock_amount - ? WHERE article_id = ? AND warehouse_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect);
             PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {

            pstmtSelect.setInt(1, stock.article_id);
            pstmtSelect.setInt(2, stock.warehouse_id);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                int currentAmount = rs.getInt("stock_amount");
                int newAmount = currentAmount - stock.stock_amount;
                if (newAmount >= 0) {
                    pstmtUpdate.setInt(1, stock.stock_amount);
                    pstmtUpdate.setInt(2, stock.article_id);
                    pstmtUpdate.setInt(3, stock.warehouse_id);
                    pstmtUpdate.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка: " + e.getMessage());
        }
        return false;
    }

}
