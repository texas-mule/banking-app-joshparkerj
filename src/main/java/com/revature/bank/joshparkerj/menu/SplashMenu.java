package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

class SplashMenu extends ABCMenu {

	private static boolean newSession = true;

	public SplashMenu(MenuTree mt, Scanner s) {
		super(mt, s);
	}

	public void Run() {
		mt.ps.println(newSession ? "Welcome to the Banking App!" : "");
		mt.ps.println("Please choose from the following options:");
		mt.ps.println("1. Create New User");
		mt.ps.println("2. Login Existing User");
		mt.ps.println("3. Quit");
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
				mt.ps.println("Your input was not understood.");
			}
		} catch (StringIndexOutOfBoundsException e) {
			mt.ps.println("You have to enter a number!");
		}
	}

}
