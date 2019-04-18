package com.revature.bank.joshparkerj;

import java.util.Scanner;

public class SplashMenu extends ABCMenu {

	public SplashMenu(MenuTree mt, Scanner s) {
		super(mt, s);
	}

	public void Run() {
		System.out.println("Welcome to the Banking App!");
		System.out.println("Please choose from the following options:");
		System.out.println("1. Create New User");
		System.out.println("2. Login Existing User");
		System.out.println("3. Quit");
		switch (Integer.parseInt(s.nextLine().substring(0,1))) {
		case 1:
			mt.menu("CreateUser");
			break;
		case 2:
			mt.menu("Login");
			break;
		case 3:
			break;
		default:
			System.out.println("Your input was not understood.");
			mt.menu("Splash");
		}
	}

}
