package com.app.menu;
 	
import java.util.Scanner;
import com.app.service.AdminService;
public class AdminMenu {
	
	private static int adminMenuOptions(Scanner sc) {
		System.out.println();
		System.out.println();
		System.out.println("************************Admin Menu************************");
		System.out.println("1. Create Quiz");
		System.out.println("2. List Quizzes");
		System.out.println("3. Delete Quiz");
		System.out.println("4. View Scores for Quiz");
		System.out.println("5. Logout");

		return sc.nextInt();

	}

	public static void adminMenu(Scanner sc,int adminId) {
		int choice;
		
		AdminService service = new AdminService();
		
		while ((choice = adminMenuOptions(sc)) != 5) {
			switch (choice) {

			case 1:
			    service.createQuiz(sc, adminId);
			    break;

			case 2:
			    service.listQuizzes();
			    break;

			case 3:
			    service.deleteQuiz(sc);  
			    break;
			    
			case 4:
				service.viewScoresForQuiz(sc);
				break;

			case 5:
			    System.out.println("Admin Logged Out");
			    break;
			    
	            
			default:
			    System.out.println("Enter correct option");
			}
		}
		System.out.println("Admin Logged Out");
	}

}
