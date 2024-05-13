package database;

import java.sql.*;

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


    public static String getAllWarehouse() {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT w.name, a.street, a.building FROM warehouses w JOIN addresses a ON w.address_id = a.id";
        StringBuilder result = new StringBuilder();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql);

             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String warehouseName = rs.getString("name");
                String street = rs.getString("street");
                String building = rs.getString("building");

                result.append(warehouseName).append(": ").append(street).append(", ").append(building).append("\n\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return String.valueOf(result);
    }

}
