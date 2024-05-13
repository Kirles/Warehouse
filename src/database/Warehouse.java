package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Warehouse {
    private String name;
    private int address_id;

    public Warehouse(String name, int address_id) {
        this.name = name;
        this.address_id = address_id;
    }

    public static void addWarehouse(Warehouse warehouse) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "INSERT INTO warehouses (name, address_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, warehouse.name);

            pstmt.setInt(2, warehouse.address_id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
