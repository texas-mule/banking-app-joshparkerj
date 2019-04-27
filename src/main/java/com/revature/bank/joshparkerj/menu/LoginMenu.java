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
		mt.ps.println("Enter your username:");
		name = s.nextLine();
		mt.ps.println("Enter your password:");
		pass = s.nextLine();
		mt.ps.println("Login as: ");
		mt.ps.println("1. Customer");
		mt.ps.println("2. Employee");
		switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
		case 1:
			type = "Customer";
			id = mt.getDB().customer().getCustomerID(name, pass);
			break;
		case 2:
			type = "Employee";
			id = mt.getDB().employee().getEmployeeID(name, pass);
			break;
		default:
			mt.ps.println("Your input was not understood.");
			id = null;
		}
		if (id != null) {
			UserSession.init(id);
			mt.ps.println("Hello, " + name + "! Thank you for logging in!");
			mt.queueMenu(type);
		} else {
			mt.ps.println("Login failed");
			mt.queueMenu("Splash");
		}
	}

}
