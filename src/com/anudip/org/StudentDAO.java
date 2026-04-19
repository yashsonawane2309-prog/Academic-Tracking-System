package com.anudip.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    public void addStudent(String name, String className, String email) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO students(name, class, email) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, className);
            ps.setString(3, email);

            ps.executeUpdate();

            System.out.println("✅ Student Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchStudent(String name) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT * FROM students WHERE name LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("class")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}