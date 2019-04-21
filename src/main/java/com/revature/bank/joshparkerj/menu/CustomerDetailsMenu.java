package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

public class CustomerDetailsMenu extends ABCMenu {

	private String id;

	public CustomerDetailsMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Employee");
		System.out.println("Please enter the customer id number:");
		id = s.nextLine();
		if (!mt.getDB().customerExists(id)) {
			System.out.println("That customer is not in the system!");
			return;
		}
		System.out.println(mt.getDB().customerDetails(id));
	}

}
