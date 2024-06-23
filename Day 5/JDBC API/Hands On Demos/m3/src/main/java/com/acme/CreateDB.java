package com.acme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;


        // Step 1 : Connecting to server
        String connectionUrl = "jdbc:mysql://localhost:3306";
        String userName = "root";
        String password = "password";
        try {
            con = DriverManager.getConnection(connectionUrl, userName, password);
            // Step 2 : Initialize statement
            stmt = con.createStatement();
            // Step 3 : SQL Query
            String query = "CREATE database STOREDB";
            // Step 4 : Run Query
            stmt.executeUpdate(query);

            System.out.println("Database STOREDB created successfully");
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
