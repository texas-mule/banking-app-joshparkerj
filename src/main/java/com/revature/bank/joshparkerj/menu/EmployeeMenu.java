package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class EmployeeMenu extends ABCMenu {

	public EmployeeMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.ps.println("Please choose from the following options:");
		mt.ps.println("1. View All Customers");
		mt.ps.println("2. View a Customer's Details");
		mt.ps.println("3. View Account Applications");
		mt.ps.println("4. Approve an Application");
		mt.ps.println("5. Deny an application");
		if (mt.getDB().isAdmin(UserSession.getID())) mt.ps.println("8. See admin options...");
		mt.ps.println("9. Log out");
		mt.ps.println("0. Quit.");
		try {
			switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
			case 1:
				viewCustomers();
				break;
			case 2:
				mt.queueMenu("CustomerDetails");
				break;
			case 3:
				viewApps();
				break;
			case 4:
				mt.queueMenu("ApproveApp");
				break;
			case 5:
				mt.queueMenu("DenyApp");
				break;
			case 8:
				if (mt.getDB().isAdmin(UserSession.getID())) mt.queueMenu("Admin");
				else mt.ps.println("Your input was not understood");
				break;
			case 9:
				UserSession.end();
				mt.queueMenu("Splash");
				break;
			case 0:
				UserSession.end();
				mt.quit();
				break;
			default:
				mt.ps.println("Your input was not understood");
			}
		} catch (NumberFormatException e) {
			mt.ps.println("You have to enter a number!");
		}
	}

	private void viewCustomers() {
		mt.ps.print(mt.getDB().getCustomers());
	}

	private void viewApps() {
		mt.ps.println(mt.getDB().getPendingApps());
	}

}
