package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

class LoginMenu extends ABCMenu {

	private String type;
	private String name;
	private String pass;
	private String id;

	public LoginMenu(MenuTree mt, Scanner s) {
		super(mt, s);
	}

	public void Run() {
		System.out.println("Enter your username:");
		name = s.nextLine();
		System.out.println("Enter your password:");
		pass = s.nextLine();
		System.out.println("Login as: ");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		switch (Integer.parseInt(s.nextLine().substring(0,1))) {
		case 1:
			type = "Customer";
			id = mt.getDB().getCustomerID(name, pass);
			break;
		case 2:
			type = "Employee";
			id = mt.getDB().getEmployeeID(name, pass);
			break;
		default:
			System.out.println("Your input was not understood.");
			id = null;
		}
		if (id != null) {
			UserSession.init(id);
			System.out.println("Hello, " + name + "! Thank you for logging in!");
			mt.menu(type);
		} else {
			System.out.println("Login failed");
			mt.menu("Splash");
		}
	}

}
