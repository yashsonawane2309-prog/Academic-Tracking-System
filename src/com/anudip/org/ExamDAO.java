package com.anudip.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExamDAO {

    public void checkUpcomingExams() {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM exams WHERE exam_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("📅 Upcoming Exam: " 
                    + rs.getString("subject") 
                    + " on " 
                    + rs.getDate("exam_date"));
            }

            if (!found) {
                System.out.println("No upcoming exams in next 7 days.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}