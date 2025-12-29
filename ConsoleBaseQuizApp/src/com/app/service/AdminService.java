package com.app.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.app.dao.AdminDao;
import com.app.dao.QuizDao;
import com.app.dao.QuestionsDao;
import com.app.dao.QuizAttemptsDao;
import com.app.menu.AdminMenu;
import com.app.model.Questions;
import com.app.model.Quizzes;

public class AdminService {

    public void loginAdmin(Scanner sc) {
        System.out.print("Enter the E-mail : ");
        String email = sc.next();

        System.out.print("Enter the Password : ");
        String password = sc.next();

        try (AdminDao ad = new AdminDao()) {
            int adminId = ad.adminLogin(email, password);

            if (adminId != -1) {
                System.out.println("Admin Login Successfully");
                AdminMenu.adminMenu(sc, adminId);
            } else {
                System.out.println("Admin Login Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createQuiz(Scanner sc, int adminId) {

        try {
            sc.nextLine();

            System.out.print("Enter Quiz Title : ");
            String title = sc.nextLine();

            System.out.print("Enter Question File Path : ");
            String filePath = sc.nextLine();

            try (QuizDao quizDao = new QuizDao();
                 QuestionsDao quesDao = new QuestionsDao();
                 BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                Quizzes quiz = new Quizzes();
                quiz.setTitle(title);
                quiz.setCreator_id(adminId);

                int quizId = quizDao.insertQuiz(quiz);

                String line;
                while ((line = br.readLine()) != null) {

                    if (line.isBlank())
                        continue;

                    String question = line;
                    String optA = br.readLine().substring(3);
                    String optB = br.readLine().substring(3);
                    String optC = br.readLine().substring(3);
                    String optD = br.readLine().substring(3);
                    String answer = String.valueOf(
                            br.readLine().split(":")[1].trim().charAt(0)
                    );

                    Questions q = new Questions();
                    q.quiz_id = quizId;
                    q.setText(question);
                    q.setA(optA);
                    q.setB(optB);
                    q.setC(optC);
                    q.setD(optD);
                    q.setCorrect(answer);

                    quesDao.insertQuestion(q);
                }

                System.out.println("Quiz created successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   public void listQuizzes() {

        try (QuizDao quizDao = new QuizDao()) {

            List<Quizzes> quizList = quizDao.selectQuiz();

            if (quizList.isEmpty()) {
                System.out.println("No quizzes available.");
                return;
            }

            System.out.println("\nAvailable Quizzes:");
            int index = 1;
            for (Quizzes quiz : quizList) {
                System.out.println(index++ + ". " + quiz.getTitle());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    
    public void viewScoresForQuiz(Scanner sc) {
        try (QuizDao quizDao = new QuizDao();
             QuizAttemptsDao attemptsDao = new QuizAttemptsDao()) {

            List<Quizzes> quizzes = quizDao.selectQuiz();
            if (quizzes.isEmpty()) {
                System.out.println("No quizzes available.");
                return;
            }

            System.out.println("\nAvailable Quizzes:");
            for (Quizzes q : quizzes) {
                System.out.println(q.getQuiz_id() + ". " + q.getTitle());
            }
            
            
            System.out.print("Enter Quiz ID to view scores: ");
            int quizId = sc.nextInt();

            List<String> scores = attemptsDao.getQuizScores(quizId);
            if (scores.isEmpty()) {
                System.out.println("No attempts found for this quiz.");
                return;
            }

            System.out.println("\nScores for Quiz ID " + quizId + ":");
            for (String s : scores) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}