package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

public class EmployeeMenu extends ABCMenu {

	public EmployeeMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}
	
	public void Run() {
		System.out.println("Please choose from the following options:");
		System.out.println("1. View All Customers");
		System.out.println("2. View a Customer's Details");
		System.out.println("3. View Account Applications");
		System.out.println("4. Approve an Application");
		System.out.println("5. Deny an application");
		System.out.println("9. Log out");
		System.out.println("0. Quit.");
		switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
		case 1:
			viewCustomers();
			break;
		case 2:
			
		}
	}
	
	private void viewCustomers() {
		
	}

}
