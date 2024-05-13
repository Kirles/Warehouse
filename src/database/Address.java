package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Address {
    private String street;
    private String building;

    public Address(int ID, String street, String building) {
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
}
