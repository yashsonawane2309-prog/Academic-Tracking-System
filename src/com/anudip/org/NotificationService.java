package com.anudip.org;

public class NotificationService {

    public void notifyStudent(String message) {
        System.out.println("🔔 NOTIFICATION: " + message);
    }

    public void checkLowAttendance(int studentId, double percentage) {
        if (percentage < 75) {
            notifyStudent("Warning! Low Attendance: " + percentage + "%");
        }
    }

    public void examReminder() {
        System.out.println("📅 Reminder: Upcoming exams this week!");
    }

    public void assignmentAlert(int pending) {
        if (pending > 0) {
            notifyStudent("You have " + pending + " pending assignments!");
        }
    }
}