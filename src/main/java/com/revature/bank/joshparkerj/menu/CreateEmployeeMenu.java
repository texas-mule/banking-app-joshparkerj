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
		mt.ps.println("Enter username: ");
		name = s.nextLine();
		mt.ps.println("Enter password: ");
		pass = s.nextLine();
		mt.ps.println("Enter employee\'s id number: ");
		id = s.nextLine();
		mt.ps.println("Enter supervisor\'s id number: ");
		supid = s.nextLine();
		if (mt.getDB().employee().employeeExists(supid)) {
			mt.ps.println("Enter salary: ");
			pay = s.nextLine();
			mt.ps.println("Enter first name: ");
			first = s.nextLine();
			mt.ps.println("Enter last name: ");
			last = s.nextLine();
			mt.ps.println("Enter branch name: ");
			loc = s.nextLine();
			mt.getDB().employee().addEmployee(name, pass, id, supid, pay, first, last, loc);
			mt.ps.println("Employee added.");

		} else {
			mt.ps.println("That supervisor is not in the system!");
		}
		mt.queueMenu("Splash");
	}

}
