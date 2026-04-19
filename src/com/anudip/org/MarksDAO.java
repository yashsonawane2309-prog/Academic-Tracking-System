package com.anudip.org;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MarksDAO {

    public void addMarks(int studentId, String subject, int marks) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO marks(student_id, subject, marks) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setString(2, subject);
            ps.setInt(3, marks);

            ps.executeUpdate();
            System.out.println("✅ Marks Added");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}