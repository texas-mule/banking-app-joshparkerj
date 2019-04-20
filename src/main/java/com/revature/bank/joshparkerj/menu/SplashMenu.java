package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

class SplashMenu extends ABCMenu {

	private static boolean newSession = true;

	public SplashMenu(MenuTree mt, Scanner s) {
		super(mt, s);
	}

	public void Run() {
		System.out.println(newSession ? "Welcome to the Banking App!" : "");
		System.out.println("Please choose from the following options:");
		System.out.println("1. Create New User");
		System.out.println("2. Login Existing User");
		System.out.println("3. Quit");
		newSession = false;
		try {
			switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
			case 1:
				mt.queueMenu("CreateUser");
				break;
			case 2:
				mt.queueMenu("Login");
				break;
			case 3:
				mt.quit();
				break;
			default:
				System.out.println("Your input was not understood.");
				mt.queueMenu("Splash");
			}
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("You have to enter a number!");
			mt.queueMenu("Splash");
		}
	}

}
