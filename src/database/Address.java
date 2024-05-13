package database;

import java.sql.*;

public class Address {
    private String street;
    private String building;

    public Address(String street, String building) {
        this.street = street;
        this.building = building;
    }

    public static void addAddress(Address address) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "INSERT INTO addresses (street, building) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, address.street);
            pstmt.setString(2, address.building);

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getAddressID(Address address) {
        String url = "jdbc:sqlite:warehouse.db";
        String sql = "SELECT id FROM addresses WHERE street = ? AND building = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, address.street);
            pstmt.setString(2, address.building);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

}
