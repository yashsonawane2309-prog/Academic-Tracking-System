package com.anudip.org;

public class DashboardService {

    public void showDashboard(int studentId) {

        AttendanceDAO attendanceDAO = new AttendanceDAO();
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        PredictionService prediction = new PredictionService();

        double attendance = attendanceDAO.getAttendancePercentage(studentId);
        int pending = assignmentDAO.getPendingAssignments(studentId);

        System.out.println("\n===== DASHBOARD =====");
        System.out.println("Attendance: " + attendance + "%");
        System.out.println("Pending Assignments: " + pending);

        prediction.predictPerformance(studentId);
    }
}