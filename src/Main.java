import frames.AuthFrame;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static Connection conn;

    public static void main(String[] args) throws Exception {
        String url = "jdbc:sqlite:warehouse.db";
        conn = DriverManager.getConnection(url);
        AuthFrame.frame();
    }
}
