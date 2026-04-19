package com.anudip.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AttendanceDAO {

    // ✅ Mark Attendance
    public void markAttendance(int studentId, String status) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO attendance (student_id, date, status) VALUES (?, CURDATE(), ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setString(2, status);

            ps.executeUpdate();

            System.out.println("Attendance Marked Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ ADD THIS METHOD (THIS FIXES YOUR ERROR)
    public double getAttendancePercentage(int studentId) {

        double percentage = 0;

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT COUNT(CASE WHEN status='Present' THEN 1 END) * 100.0 / COUNT(*) AS percentage FROM attendance WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                percentage = rs.getDouble("percentage");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return percentage;
    }
}