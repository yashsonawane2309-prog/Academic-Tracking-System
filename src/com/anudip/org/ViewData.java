package com.anudip.org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewData {

    // ✅ View Students
    public void viewStudents() {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) return;

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("class") + " | " +
                        rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ View Marks (FIXED)
    public void viewMarks() {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) return;

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM marks");

            while (rs.next()) {
                System.out.println(
                        "Student ID: " + rs.getInt("student_id") + " | " +
                        rs.getString("subject") + " | " +
                        rs.getInt("marks")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ FULL REPORT (FINAL FIX)
    public void viewFullReport() {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) return;

            String query = "SELECT s.name, s.class, m.subject, m.marks, a.status " +
                           "FROM students s " +
                           "JOIN marks m ON s.id = m.student_id " +
                           "JOIN attendance a ON s.id = a.student_id";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n===== FULL REPORT =====");

            while (rs.next()) {
                System.out.println(
                        rs.getString("name") + " | " +
                        rs.getString("class") + " | " +
                        rs.getString("subject") + " | " +
                        rs.getInt("marks") + " | " +
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ RESULT WITH GRADE (FIXED)
    public void showResultWithGrade() {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) return;

            String query = "SELECT s.name, SUM(m.marks) as total, COUNT(m.subject) as subjects " +
                           "FROM students s " +
                           "JOIN marks m ON s.id = m.student_id " +
                           "GROUP BY s.id";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int total = rs.getInt("total");
                int subjects = rs.getInt("subjects");
                double percentage = total / (double) subjects;

                String grade;
                if (percentage >= 75) grade = "A";
                else if (percentage >= 60) grade = "B";
                else if (percentage >= 40) grade = "C";
                else grade = "Fail";

                System.out.println(
                        rs.getString("name") + " | " +
                        "Percentage: " + percentage + "% | Grade: " + grade
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ TOPPER (FIXED)
    public void showTopper() {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) return;

            String query = "SELECT s.name, SUM(m.marks) as total " +
                           "FROM students s " +
                           "JOIN marks m ON s.id = m.student_id " +
                           "GROUP BY s.id " +
                           "ORDER BY total DESC LIMIT 1";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                System.out.println("🏆 Topper: " + rs.getString("name") +
                                   " | Total Marks: " + rs.getInt("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}