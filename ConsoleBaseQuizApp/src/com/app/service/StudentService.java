package com.app.service;

import java.util.List;
import java.util.Scanner;

import com.app.dao.StudentDao;
import com.app.dao.QuizDao;
import com.app.dao.QuestionsDao;
import com.app.dao.QuizAttemptsDao;
import com.app.menu.StudentMenu;
import com.app.model.Quizzes;
import com.app.model.Questions;
import com.app.model.Users;

public class StudentService {
	private static StudentMenu sdm = new StudentMenu();

    public void loginStudent(Scanner sc) {

        System.out.print("Enter the E-mail : ");
        String email = sc.next();

        System.out.print("Enter the Password : ");
        String password = sc.next();

        try (StudentDao sd = new StudentDao()) {

            int studentId = sd.studentLogin(email, password);

            if (studentId != -1) {
                System.out.println("Student Login Successfully\n");
                sdm.studentMenu(sc, studentId);
            } else {
                System.out.println("Student Login Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void takeQuiz(Scanner sc, int studentId) {

        try (QuizDao quizDao = new QuizDao();
             QuestionsDao qDao = new QuestionsDao();
             QuizAttemptsDao aDao = new QuizAttemptsDao()) {

            System.out.print("Enter Quiz ID : ");
            int quizId = sc.nextInt();

            if (aDao.hasAttempted(studentId, quizId)) {
                System.out.println("You have already attempted this quiz.");
                return;
            }

            List<Questions> questions = qDao.getQuestionsByQuizId(quizId);

            if (questions.isEmpty()) {
                System.out.println("No questions found for this quiz.");
                return;
            }

            int score = 0;

            for (Questions q : questions) {
                System.out.println("\n" + q.getText());
                System.out.println("A) " + q.getA());
                System.out.println("B) " + q.getB());
                System.out.println("C) " + q.getC());
                System.out.println("D) " + q.getD());
                System.out.print("Your answer : ");

                char ans = sc.next().toUpperCase().charAt(0);
                if (String.valueOf(ans).equals(q.getCorrect()))
                    score++;
            }

            aDao.saveAttempt(studentId, quizId, score, questions.size());

            System.out.println("\nQuiz Completed!");
            System.out.println("Score : " + score + "/" + questions.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
