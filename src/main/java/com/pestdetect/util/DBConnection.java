package com.pestdetect.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Central place that opens a JDBC connection to MySQL.
 * Every DAO calls DBConnection.getConnection().
 */
public class DBConnection {

    // ====== EDIT THESE THREE LINES to match your own MySQL setup ======
    private static final String URL =
        "jdbc:mysql://localhost:3306/pestdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Hema@sql07";   // <-- put your MySQL password here
    // ==================================================================

    // Load the driver once when the class is first used.
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found on classpath", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
