package com.acme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDB {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;


        // Step 1 : Connecting to server
        String connectionUrl = "jdbc:mysql://localhost:3306/STOREDB";
        String userName = "root";
        String password = "password";
        try {
            con = DriverManager.getConnection(connectionUrl, userName, password);

            System.out.println("Database STOREDB selected successfully");
        } catch (SQLException e) {
            System.out.println("Cannot connect!");
            e.printStackTrace();
        } finally {
            System.out.println("Closing the connection.");

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
