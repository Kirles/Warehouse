package database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static String[] getAllWarehouseName() {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT name FROM warehouses";
        List<String> warehouseNames = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                warehouseNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String[] result = new String[warehouseNames.size()];
        return warehouseNames.toArray(result);
    }

    public static int getWarehouseID(String name) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT id FROM warehouses where name = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static void deleteWarehouse(String name) {
        String url = "jdbc:sqlite:warehouse.db";
        String deleteWarehouseSQL = "DELETE FROM warehouses WHERE name = ?";
        String deleteAddressSQL = "DELETE FROM addresses WHERE id = (SELECT address_id FROM warehouses WHERE name = ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmtWarehouse = conn.prepareStatement(deleteWarehouseSQL);
             PreparedStatement pstmtAddress = conn.prepareStatement(deleteAddressSQL)) {
            pstmtAddress.setString(1, name);
            pstmtAddress.executeUpdate();
            pstmtWarehouse.setString(1, name);
            pstmtWarehouse.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении склада: " + e.getMessage());
        }
    }

    public static String showComboBoxInputDialog(JFrame parent, String[] options, String message) {
        JComboBox<String> comboBox = new JComboBox<>(options);

        int result = JOptionPane.showConfirmDialog(parent, comboBox, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            return (String) comboBox.getSelectedItem();
        } else {
            return null;
        }
    }

    public static void updateWarehouse(String name, String newName, String newStreet,String newBuilding) {
        String url = "jdbc:sqlite:warehouse.db";
        String updateWarehouseSQL = "UPDATE warehouses SET name = ? WHERE name = ?";

        String updateAddressSQL = "UPDATE addresses SET street = ?, building = ? WHERE id = " +
                "(SELECT address_id FROM warehouses WHERE name = ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmtWarehouse = conn.prepareStatement(updateWarehouseSQL);
             PreparedStatement pstmtAddress = conn.prepareStatement(updateAddressSQL)) {

            pstmtWarehouse.setString(1, newName);
            pstmtWarehouse.setString(2, name);
            pstmtWarehouse.executeUpdate();

            pstmtAddress.setString(1, newStreet);
            pstmtAddress.setString(2, newBuilding);
            pstmtAddress.setString(3, newName);
            pstmtAddress.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static String generateWarehouseInfo() {
        StringBuilder result = new StringBuilder();
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT " +
                "  w.name AS warehouse_name, " +
                "  SUM(CASE WHEN o.order_type_id = 2 THEN 1 ELSE 0 END) AS deliveries_count, " +
                "  SUM(CASE WHEN o.order_type_id = 1 THEN 1 ELSE 0 END) AS aid_count " +
                "FROM warehouses w " +
                "LEFT JOIN orders o ON w.id = o.warehouse_id " +
                "GROUP BY w.id";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String warehouseName = rs.getString("warehouse_name");
                int deliveriesCount = rs.getInt("deliveries_count");
                int aidCount = rs.getInt("aid_count");

                result.append(warehouseName)
                        .append(":\n   Допомог: ").append(aidCount)
                        .append("\n   Постачаннь: ").append(deliveriesCount)
                        .append("\n\n");
            }

        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        return result.toString();
    }

}
