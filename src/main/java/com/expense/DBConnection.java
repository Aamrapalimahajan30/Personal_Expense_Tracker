package com.expense;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Automatically create database and table
    public static Connection getConnection()
            throws SQLException {

                try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        throw new SQLException("MySQL JDBC driver not found.", e);
    }

        try (Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(
                    "CREATE DATABASE IF NOT EXISTS expense_tracker");
        }

        Connection con = DriverManager.getConnection(
                URL + "expense_tracker", USER, PASSWORD);

        try (Statement stmt = con.createStatement()) {

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS expense (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "exp_date DATE NOT NULL, " +
                "category VARCHAR(100) NOT NULL, " +
                "amount DECIMAL(10,2) NOT NULL, " +
                "note VARCHAR(255))"
            );
        }

        return con;
    }
}
