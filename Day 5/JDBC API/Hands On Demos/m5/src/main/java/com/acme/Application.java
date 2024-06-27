package com.acme;

import java.sql.*;
import java.time.LocalDate;

public class Application {
    //    static String url = "jdbc:postgresql://localhost:5432/loboticket";
    static String url = "jdbc:mysql://localhost:3306/loboticket";
    static String userName = "root";
    //    static String userName = "postgres";
    static String password = "password";

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
//            simpleReadWithExecuteQuery(conn);
//            simpleInsertWithExecuteUpdate(conn);
//            simpleUpdateWithExecuteUpdate(conn);
//            simpleDeleteWithExecuteUpdate(conn);
//            simpleInsertWithExecute(conn);
//            simpleSelectWithExecute(conn);
//            insertMultipleValues(conn);
//            insertWithTooManyParameters(conn);
//            selectWithParameters(conn);
//            insertWithSetObject(conn);
//            useResultSetWithColumnNumber(conn);
//            useResultSetWithColumnName(conn);
//            useResultSetWithIfAndColumnIndex(conn);
//            useResultSetWithIfAndColumnName(conn);
//            useResultSetWithGetObject(conn);
//            insertAct(conn, "BG", "Dettol Row");
//            csGetActsWithRecordLabel(conn);
//            csGigReportWithINParameters(conn);
//            csGetTotalSalesWithOutParameter(conn);
//            csSetNewPrice(conn);
        }
    }

    private static void csSetNewPrice(Connection conn) throws SQLException {
        var sql = "{ call SetNewPrice(?,?,?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
//                cs.setInt(1, 1);
//                cs.setDouble(2, 0.1);
//                cs.setDouble(3, 12);

            cs.setInt(1, 2);
            cs.setDouble(2, 0.2);
            cs.setDouble(3, 12);

            cs.registerOutParameter(3, Types.DOUBLE);

            cs.execute();

            System.out.println("New price is: " + cs.getDouble(3));
        }
    }

    private static void csGetTotalSalesWithOutParameter(Connection conn)
            throws SQLException {
        var sql = "{ call GetTotalSales(?)}";
//        var sql = "call GetTotalSales(?)";
//          var sql = "? = call GetTotalSales()";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.registerOutParameter(1, Types.DOUBLE);

            cs.execute();

            System.out.println("Total sales is: " + cs.getDouble(1));
        }
    }

    private static void csGigReportWithINParameters(Connection conn) throws
            SQLException {
        var sql = "{ CALL GigReport(?,?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setDate("startdate",
                    Date.valueOf(LocalDate.of(2022, 11, 1)));
            cs.setDate("enddate",
                    Date.valueOf(LocalDate.of(2022, 11, 7)));

            var rs = cs.executeQuery();

            while (rs.next()) {
                var date = rs.getDate("date");
                var name = rs.getString("act");
                var recordLabel = rs.getString("recordLabel");
                var venue = rs.getString("venue");
                var ticketsSold = rs.getString("ticketsSold");
                var capacity = rs.getString("capacity");

                System.out.println(date + " " + name + " " + recordLabel + " "
                        + venue + " " + ticketsSold + " " + capacity);
            }

        }
    }

    private static void csGetActsWithRecordLabel(Connection conn) throws SQLException {
        var sql = "{ call GetActs()}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            var rs = cs.executeQuery();

            while (rs.next()) {
                var name = rs.getString("name");
                var recordLabel = rs.getString("recordLabel");

                System.out.println(name + "-" + recordLabel);
            }

        }
    }

    private static void insertAct(Connection conn, String name, String recordLabel) throws SQLException {
        var sql = "insert into acts (name, recordLabel) values (?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);

            if (recordLabel != null)
                ps.setString(2, recordLabel);
            else
                ps.setNull(2, Types.CHAR);

            ps.executeUpdate();


        }
    }

    private static void useResultSetWithGetObject(Connection conn) throws
            SQLException {
        String sql = "select capacity, name from venues where name like ?";

        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%the%");

        ResultSet rs = stmt.executeQuery();

        String name = "";
        int capacity = 0;

        while (rs.next()) {
            Object nameValue = rs.getObject("name");
            if (nameValue instanceof String) name = (String) nameValue;

            Object capacityValue = rs.getObject("capacity");
            if (capacityValue instanceof Integer) capacity = (int) capacityValue;

            System.out.println(name + " has capacity " + capacity);
        }
    }

    private static void useResultSetWithIfAndColumnName(Connection conn)
            throws SQLException {
        String sql = "select count(*) as count from gigs";

        var stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            System.out.println("The number of gigs is " +
                    rs.getInt("count"));
        }
    }

    private static void useResultSetWithIfAndColumnIndex(Connection conn)
            throws SQLException {
        String sql = "select count(*) from gigs";

        var stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            System.out.println("The number of gigs is " + rs.getInt(1));
        }
    }

    private static void useResultSetWithColumnName(Connection conn)
            throws SQLException {
        //            String sql = "select name,capacity from venues
        //            where name like ?";

        String sql = "select capacity, name from venues where name like ?";

        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%the%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            int capacity = rs.getInt("capacity");

            System.out.println(name + " has capacity " + capacity);
        }
    }

    private static void useResultSetWithColumnNumber(Connection conn)
            throws SQLException {
        String sql = "select name,capacity from venues where name like ?";

        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%the%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString(1);
            int capacity = rs.getInt(2);

            System.out.println(name + " has capacity " + capacity);
        }
    }

    private static void insertWithSetObject(Connection conn) throws SQLException {
        String sql = "insert into venues (name,capacity) values(?,?)";

        var stmt = conn.prepareStatement(sql);
        stmt.setObject(1, "Empire State Park");
        stmt.setObject(2, 3000);

        int result = stmt.executeUpdate();

        if (result > 0)
            System.out.println("Update the database");
    }

    private static void selectWithParameters(Connection conn) throws SQLException {
        String sql = "select name,capacity from venues where name like ?";

        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%the%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.print(".");
        }

        System.out.println();
    }

    private static void insertWithTooManyParameters(Connection conn) throws SQLException {
        String sql = "insert into venues (name,capacity) values(?,?)";

        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, "Empire State Park");
        stmt.setInt(2, 3000);
        stmt.setInt(3, 3000);

        int result = stmt.executeUpdate();

        if (result > 0)
            System.out.println("Update the database");
    }

    private static void insertMultipleValues(Connection conn) throws SQLException {
        String sql = "insert into venues (name,capacity) values(?,?)";

        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, "Empire State Park");
        stmt.setInt(2, 3000);

        int result = stmt.executeUpdate();

        if (result > 0)
            System.out.println("Update the database");

        stmt.setString(1, "London Hyde Park");

        result = stmt.executeUpdate();

        if (result > 0)
            System.out.println("Update the database");
    }

    private static void simpleSelectWithExecute(Connection conn) throws SQLException {
        String sql = "select name,capacity from venues";

        var stmt = conn.prepareStatement(sql);
        var result = stmt.execute();

        if (result) {
            var rs = stmt.getResultSet();
            while (rs.next())
                System.out.print(".");
        }

        System.out.println();
    }

    private static void simpleInsertWithExecute(Connection conn)
            throws SQLException {
        String sql = "insert into venues (name,capacity) " +
                "values('The House Next Door',20)";

        var stmt = conn.prepareStatement(sql);

        boolean result = stmt.execute();

        if (!result) {
            if (stmt.getUpdateCount() > 0)
                System.out.println("Update the database");
            else
                System.out.println("No update");

        } else {
            System.out.println("Result was not a count");
        }
    }

    private static void simpleDeleteWithExecuteUpdate(Connection conn) throws SQLException {
        String sql = "delete from  venues where name = 'The House Next Door'";

        var stmt = conn.prepareStatement(sql);

        int result = stmt.executeUpdate();

        if (result > 0)
            System.out.println("Update the database");
        else
            System.out.println("Update failed");
    }

    private static void simpleUpdateWithExecuteUpdate(Connection conn)
            throws SQLException {
        String sql = "update venues set capacity = 30 where" +
                " name = 'The House Next Door'";

        var stmt = conn.prepareStatement(sql);

        int result = stmt.executeUpdate();

        if (result > 0)
            System.out.println("Update the database");
    }

    private static void simpleInsertWithExecuteUpdate(Connection conn)
            throws SQLException {
        String sql = "insert into venues (name,capacity) " +
                "values('The House Next Door',20)";

        var stmt = conn.prepareStatement(sql);

        int result = stmt.executeUpdate();

        if (result > 0)
            System.out.println("Update the database");
    }

    private static void simpleReadWithExecuteQuery(Connection conn) throws SQLException {
        String sql = "select name,capacity from venues";

        var stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(".");
        }

        System.out.println();
    }
}
