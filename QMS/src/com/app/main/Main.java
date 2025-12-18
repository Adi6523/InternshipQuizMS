package com.app.main;

import java.util.Scanner;

import com.app.menu.Menu;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Menu.menu(scanner);
		scanner.close();
	}

}