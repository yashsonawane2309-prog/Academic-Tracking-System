package com.anudip.org;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/academic_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "1234"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}