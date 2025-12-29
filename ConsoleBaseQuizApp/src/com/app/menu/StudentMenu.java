package com.app.menu;

import java.util.Scanner;
import com.app.service.StudentService;

public class StudentMenu {

    private static int studentMenuOptions(Scanner sc) {
        System.out.println("\n*************** Student Menu ***************");
        System.out.println("1. View Available Quizzes");
        System.out.println("2. Take Quiz");
        System.out.println("3. View Scores");
        System.out.println("4. Logout");
        System.out.print("Enter choice : ");
        return sc.nextInt();
    }

    public static void studentMenu(Scanner sc, int studentId) {

        StudentService service = new StudentService();
        int choice;

        while ((choice = studentMenuOptions(sc)) != 4) {
            switch (choice) {
            case 1:
                service.viewAvailableQuizzes(studentId);
                break;
            case 2:
                service.takeQuiz(sc, studentId);
                break;
            case 3:
                service.viewScores(studentId);
                break;
            default:
                System.out.println("Invalid option");
            }
        }
        System.out.println("Student Logged Out");
    }
}