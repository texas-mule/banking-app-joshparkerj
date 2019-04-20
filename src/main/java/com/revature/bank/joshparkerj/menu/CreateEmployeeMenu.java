package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

class CreateEmployeeMenu extends ABCMenu {

	private String name;
	private String pass;
	private String id;
	private String supid;
	private String pay;
	private String first;
	private String last;
	private String loc;

	public CreateEmployeeMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Enter username: ");
		name = s.nextLine();
		System.out.println("Enter password: ");
		pass = s.nextLine();
		System.out.println("Enter employee\'s id number: ");
		id = s.nextLine();
		System.out.println("Enter supervisor\'s id number: ");
		supid = s.nextLine();
		if (mt.getDB().employeeExists(supid)) {
			System.out.println("Enter salary: ");
			pay = s.nextLine();
			System.out.println("Enter first name: ");
			first = s.nextLine();
			System.out.println("Enter last name: ");
			last = s.nextLine();
			System.out.println("Enter branch name: ");
			loc = s.nextLine();
			mt.getDB().addEmployee(name, pass, id, supid, pay, first, last, loc);
			System.out.println("Employee added.");

		} else {
			System.out.println("That supervisor is not in the system!");
		}
		mt.queueMenu("Splash");
	}

}
