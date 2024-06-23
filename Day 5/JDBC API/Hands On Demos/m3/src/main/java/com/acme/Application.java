package com.acme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application {

    static String url = "jdbc:mysql://localhost:3306/loboticket";
    static String userName = "root";
    static String password = "password";

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection(url, userName, password)
//             PreparedStatement stmt = conn.prepareStatement("select * from Acts")
        ) {
            PreparedStatement stmt = conn.prepareStatement("select * from Acts");
            var results = stmt.executeQuery();

            while (results.next()) {
                System.out.println(results.getString("Name"));
            }

        }


    }
}
