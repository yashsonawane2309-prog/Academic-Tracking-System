package com.anudip.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PredictionService {

    public void predictPerformance(int studentId) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT AVG(marks) FROM marks WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double avg = rs.getDouble(1);

                System.out.println("\n===== AI PERFORMANCE PREDICTION =====");
                System.out.println("Average Marks: " + avg);

                if (avg > 75) {
                    System.out.println("Prediction: Excellent Performance");
                } else if (avg > 50) {
                    System.out.println("Prediction: Average Performance");
                } else {
                    System.out.println("Prediction: Needs Improvement");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}