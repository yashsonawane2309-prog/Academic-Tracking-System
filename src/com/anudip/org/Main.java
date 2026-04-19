package com.anudip.org;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        MarksDAO marksDAO = new MarksDAO();
        ViewData view = new ViewData();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        NotificationService notify = new NotificationService();
        ExamDAO examDAO = new ExamDAO();
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        DashboardService dashboard = new DashboardService();

        while (true) {
            System.out.println("\n===== Academic Tracking System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Marks");
            System.out.println("3. View Students");
            System.out.println("4. View Marks");
            System.out.println("5. View Full Report");
            System.out.println("6. Show Result with Grade");
            System.out.println("7. Mark Attendance");
            System.out.println("8. View Dashboard");
            System.out.println("9. Check Notifications");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int choice;

            // ✅ Prevent crash if user enters wrong input
            if (!sc.hasNextInt()) {
                System.out.println("❌ Invalid input! Enter number only.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Class: ");
                    String className = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    studentDAO.addStudent(name, className, email);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Subject: ");
                    String subject = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    int marks = sc.nextInt();

                    marksDAO.addMarks(id, subject, marks);
                    break;

                case 3:
                    view.viewStudents();
                    break;

                case 4:
                    view.viewMarks();
                    break;

                case 5:
                    view.viewFullReport();
                    break;

                case 6:
                    view.showResultWithGrade();
                    break;

                case 7:
                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Status (Present/Absent): ");
                    String status = sc.nextLine();

                    attendanceDAO.markAttendance(studentId, status);
                    break;

                case 8:
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();

                    dashboard.showDashboard(sid);
                    break;

                case 9:
                    System.out.print("Enter Student ID: ");
                    int stId = sc.nextInt();

                    double attendance = attendanceDAO.getAttendancePercentage(stId);
                    notify.checkLowAttendance(stId, attendance);

                    int pending = assignmentDAO.getPendingAssignments(stId);
                    notify.assignmentAlert(pending);

                    examDAO.checkUpcomingExams();
                    break;

                case 10:
                    System.out.println("Exiting...");
                    sc.close();   // ✅ good practice
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid Choice!");
            }
        }
    }
}